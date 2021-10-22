import java.util.Scanner;

public class Application {

    public static void main( String[] args ) {

        // re-usable constants
        final String headline = "########## Codekata: Anagram 101 ##########".toUpperCase();
        final String welcomeMessage = "Welcome to Anagram 101!";
        final String aboutAnagram = "This program tells you to type in any word. Please type in any word then. The application validates your input, searches for anagrams and prints any results to the console.\n";
        final String errorMSGInputNotValidated = "Could not validate user input. You probably typed in non-alphabetical characters.";

        // the user is told about the application, how to use it and what to expect from it
        System.out.println( headline );
        System.out.println( welcomeMessage );
        System.out.println( aboutAnagram );

        // the user interacts with the application: the user is told to type in any word
        Scanner scanner = new Scanner( System.in );
        System.out.println( "Please type in any word: " );
        String userInput = scanner.nextLine();

        // user input has to be validated
        if( Validator.isValidUserInput( userInput ) ) {

            Anagram anagram = new Anagram( userInput );

            anagram.start();

        } else {

            System.out.println( errorMSGInputNotValidated );
            System.exit( 1 );

        }

    }

}