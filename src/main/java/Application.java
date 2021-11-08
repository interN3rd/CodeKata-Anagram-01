import java.io.IOException;
import java.util.Scanner;

public class Application {

    public static void main( String[] args ) throws IOException {

        // the user is told about the application, how to use it and what to expect from it
        System.out.println( AppConstants.headline );
        System.out.println( AppConstants.welcomeMessage );
        System.out.println( AppConstants.aboutAnagram );

        // the user interacts with the application: the user is told to type in any word
        Scanner scanner = new Scanner( System.in );
        System.out.println( "Please type in any word: " );
        String userInput = scanner.nextLine();

        // user input has to be validated
        if( !Validator.isValidUserInput( userInput ) ) {

            System.out.println( AppConstants.errorMSGInputNotValidated );
            System.exit( 1 );

        } else {

            Anagram anagram = new Anagram( userInput );
            anagram.start();
            // Do this:
            // System.out.println( "Anagrams of "  + "\"" + anagram.getOriginalWord() + "\": " + anagram.findAnagrams( anagram.buildWords( anagram.getOriginalWord().toLowerCase( Locale.ROOT ) ) ) );
            // and input something like "pqoaieghqaoifajowfjq" and you have enough time to get coffee. And by "get coffee" I mean fly to Columbia, plant a coffee plant, fight the drug cartel, harvest one coffee bean,
            // take it back to your place, ask grandma whether it smells okay, fly back to Columbia, harvest the other coffee beans, fly back to your place, build your own coffee machine from scratch and finally make
            // that damn coffee ...

        }
    }
}