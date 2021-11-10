import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main( String[] args ) throws IOException {
        File log = new File( "logfile.txt" );
        try {
            if( !log.exists() ) {
                log.createNewFile();
            }
        } catch( IOException e ) {
            e.printStackTrace();
        }

        long start = System.currentTimeMillis();
        String userInput = "";
        int errorCount = 0;
        int inputAttempt = 0;

        // the user is told about the application, how to use it and what to expect from it
        System.out.println( AppConstants.headline );
        System.out.println( AppConstants.welcomeMessage );
        System.out.println( AppConstants.aboutAnagram );

        // the user interacts with the application: the user is told to type in any word
        Scanner scanner = new Scanner( System.in );
        while( errorCount <= 3 ) {
            inputAttempt += 1;
            if( errorCount == 3 ) {
                System.out.println( "Could still not validate your input after three attempts. Please try again later.");
                try {
                    Thread.sleep( 1000 * 60 * 60 );
                    System.exit( 1 );
                } catch( InterruptedException exception ) {
                    exception.printStackTrace();
                }
            }
            System.out.println( "Please type in any word: " );
            userInput = scanner.nextLine();
            if( !Validator.isValidUserInput( userInput ) ) {
                System.out.println("Warning: You entered non-alphabetical characters. Please try again. Attempt " + inputAttempt + " / 3.");
                errorCount += 1;
                continue;
            }
            scanner.close();
            break;
        }
        // do not waste resources when it's obvious that all coming methods don't need to process user input
        if( userInput.length() == 1 ) {
            System.out.println( "There are no anagrams for " + userInput + "." );
            System.exit( 1 );
        }

        // at this point the user input is validated so it's safer to work with
        Anagram anagram = new Anagram( userInput );
        List<String> possibleAnagrams = Anagram.buildWords( anagram.getOriginalWord() );
        List<String> anagrams = Anagram.findAnagrams( possibleAnagrams );
        if( anagrams.isEmpty() ) {
            System.out.println( "There are no anagrams for " + userInput + "." );
            long executionTime = System.currentTimeMillis() - start;
            System.out.println( "This program ran for " + executionTime + "ms.");
            try {
                PrintWriter logWriter = new PrintWriter( new FileWriter( "logfile.txt", true ) );
                logWriter.append("\nIt took ").append(String.valueOf(executionTime)).append("ms to run the anagram program for the word ").append(userInput).append(".\n");
                logWriter.close();
            } catch( IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println( "Anagrams of " + userInput + ": " + anagrams );
            long executionTime = System.currentTimeMillis() - start;
            System.out.println( "This program ran for " + executionTime + "ms.");
            try {
                PrintWriter logWriter = new PrintWriter( new FileWriter( "logfile.txt", true ) );
                logWriter.append("\nIt took ").append(String.valueOf(executionTime)).append("ms to run the anagram program for the word ").append(userInput).append(".\n");
                logWriter.close();
            } catch( IOException e) {
                e.printStackTrace();
            }
        }
    }
}