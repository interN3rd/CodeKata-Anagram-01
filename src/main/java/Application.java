import java.io.IOException;
import java.util.Scanner;

public class Application {

    public static void main( String[] args ) throws IOException {

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

        // at this point the user input is validated so it's safer to work with
        if( Validator.isNullOrEmpty( Anagram.findAnagrams( Anagram.buildWords( userInput ) ) ) ) {
            System.out.println( "There are no anagrams for " + userInput + "." );
        } else {
            System.out.println( "Anagrams of " + userInput + ": " + Anagram.findAnagrams( Anagram.buildWords( userInput ) ) );
        }
    }
}