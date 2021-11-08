import java.util.Scanner;

public class Application {

    public static void main( String[] args ) {

        // the user is told about the application, how to use it and what to expect from it
        System.out.println( AppConstants.headline );
        System.out.println( AppConstants.welcomeMessage );
        System.out.println( AppConstants.aboutAnagram );

        // the user interacts with the application: the user is told to type in any word
        Scanner scanner = new Scanner( System.in );
        System.out.println( "Please type in any word: " );
        String userInput = scanner.nextLine();

        // user input has to be validated
        if( Validator.isValidUserInput( userInput ) ) {

            Anagram anagram = new Anagram( userInput );

            anagram.start();

        } else {

            System.out.println( AppConstants.errorMSGInputNotValidated );
            System.exit( 1 );
        }
    }
}