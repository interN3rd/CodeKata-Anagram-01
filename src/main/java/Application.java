import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class Application {

    // information that is displayed to the user upon program start
    private static final String HEADLINE = "########## Codekata: Anagram 101 ##########".toUpperCase();
    private static final String WELCOMEMESSAGE = "Welcome!\n";
    private static final String ABOUTANAGRAM = "This program tells you to type in any word. Please type in any word then. The application validates your input, searches for anagrams and prints any results to the console.";
    private static final String ABOUTVALIDINPUT = "Your input counts as valid if the following requirements are met: Your input consists of lowercase or uppercase letters and is at least two characters long. Any other input will be rejected.\n";
    // messages that respond to different states of a program run
    private static final String INPUTPROMPT = "Please type in any word: ";
    private static final String INVALIDINPUT = "Warning: You entered a single character or non-alphabetical characters. Please try again.";
    // misc
    private static final String pathToWordlist = "src/main/resources/english_words_alpha.txt";

    public static void main( String[] args ) throws IOException {
        File log = createLogfile("logfile.txt" );
        // the user is told about the application, how to use it and what to expect from it
        showInstructionsToUser();
        // the user interacts with the application: the user is told to type in any word
        String userInput = getUserInput();
        // measure time of execution without period of user input
        long start = System.currentTimeMillis();
        long executionTime;
        // at this point the user input is validated so it's safer to work with
        List<String> anagrams = findAnagrams( userInput );
        if ( anagrams.isEmpty( ) ) {
            System.out.println( "There are no anagrams for " + userInput + "." );
            executionTime = executionTime(start, System.currentTimeMillis() );
            System.out.println( "This program ran for " + executionTime + "ms." );
            try {
                PrintWriter logWriter = new PrintWriter( new FileWriter( log, true ) );
                logWriter.append( "\nIt took " ).append( String.valueOf(TimeUnit.MILLISECONDS.toSeconds( executionTime ) ) ).append( "ms to run the anagram program for the word " ).append( userInput ).append( ".\n" );
                logWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Anagrams of " + userInput + ": " + anagrams.toString().replace("[", "").replace("]", ""));
            executionTime = executionTime(start, System.currentTimeMillis() );
            System.out.println("This program ran for " + executionTime + "ms.");
            try {
                PrintWriter logWriter = new PrintWriter(new FileWriter(log, true));
                logWriter.append("\nIt took ").append(String.valueOf( TimeUnit.MILLISECONDS.toSeconds( executionTime ) ) ).append("ms to run the anagram program for the word ").append(userInput).append(".\n");
                logWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void showInstructionsToUser() {
        System.out.println( HEADLINE );
        System.out.println( WELCOMEMESSAGE );
        System.out.println( ABOUTANAGRAM );
        System.out.println( ABOUTVALIDINPUT );
    }

    private static String getUserInput() {
        System.out.println( INPUTPROMPT );
        Scanner scanner = new Scanner( System.in );
        String userInput = scanner.nextLine();
        if ( isValid( userInput ) ) {
            scanner.close();
            return userInput;
        }
        System.out.println( INVALIDINPUT );
        return getUserInput();
    }

    static boolean isValid( final String userInput ) {
        return Pattern.compile( "^[A-Za-z]+$" ).matcher( userInput ).find();
    }

    static List<String> buildWords( final String userInput ) {
        if( !isValid( userInput ) ) {
            throw new IllegalArgumentException( "An illegal argument was passed to method findAnagrams().");
        }
        List<String> possibleAnagrams = new ArrayList<>();
        if( userInput.length() == 1 ) {
            possibleAnagrams.add( userInput );
            return possibleAnagrams;
        }
        for ( int i = 0; i < userInput.length(); i++ ) {
            String pre = userInput.substring(0, i );
            String post = userInput.substring( i + 1 );
            String remaining = pre + post;
            for ( String permutation : buildWords( remaining ) ) {
                possibleAnagrams.add( userInput.charAt( i ) + permutation );
                }
            }
        return possibleAnagrams;
    }

    static List<String> findAnagrams( final String userInput ) throws IOException {
        if( !isValid( userInput ) ) {
            throw new IllegalArgumentException( "An illegal argument was passed to method findAnagrams().");
        }
        if( userInput.length() == 1 ) {
            return new ArrayList<>();
        }
        // create every possible letter combination of the word that the user typed in
        List<String> possibleAnagrams = buildWords( userInput );
        if( possibleAnagrams.isEmpty() || possibleAnagrams.size() == 1 ) {
            return possibleAnagrams;
        }
        // compare every letter combination with a list of english words. If a match is found, it's an anagram
        List<String> anagrams = new ArrayList<>();
        List<String> wordList = Files.readAllLines( Paths.get( pathToWordlist ), StandardCharsets.UTF_8 );
        for ( String anagram : possibleAnagrams ) {
            if ( wordList.contains( anagram ) ) {
                anagrams.add( anagram );
            }
        }
        // "yadayada" is a word that is fully processed but won't produce any anagrams
        if ( anagrams.isEmpty() ) {
            return new ArrayList<>();
        }
        // "hello" has no anagrams, but at this point anagrams consists of [hello, hello]
        if ( anagrams.get( 0 ).equals( anagrams.get( 1 ) ) ) {
            return new ArrayList<>();
        }
        // the first entry of anagrams is always the original word which is no anagram
        return anagrams.subList(1, anagrams.size());
    }

    private static long executionTime( final long start, final long end ) {
        return end - start;
    }

    static File createLogfile(final String filepath) {
        File log = new File( filepath );
        try {
            if( !log.exists() ) {
                log.createNewFile();
            }
        } catch( IOException e ) {
            e.printStackTrace();
        }
        return log;
    }
}