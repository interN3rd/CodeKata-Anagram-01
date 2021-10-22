import java.util.Scanner;
import java.util.regex.Pattern;

public class Application {

    public static void main( String args[] ) {

        /*
        0. String-Methoden ansehen: index(), substring(), ... in der Doku nachschlagen: https://docs.oracle.com/javase/7/docs/api/java/lang/String.html
        1. Der Benutzer gibt ein Wort ein
        2. jeder Buchstabe wird als ein Element eines Arrays gespeichert
            möglicher Test: Die Länge des Arrays so lang ist wie der UserInput
        3. Das Array (die einzelnen Buchstaben des übergebenen Wortes) wird so oft geshuffled (so oft shufflen = aus den
        elementen die möglichen kombinationen ausrechnen) bis alle Anagramme gefunden sind
        5. eine Wordlist laden (Anagramme als textdatei; textdatei einlesen); Wordlist hat eine flache Hierarchie (Ein Wort pro Zeile)
         */

        System.out.println( "Welcome to anagram 101!\n\n");

        Scanner scanner = new Scanner( System.in );
        System.out.println( "Please type in any word: ");
        String userInput = scanner.nextLine();
        userInput.index
        // input validation: true if user input only consists of alphabetical characters, false in every other case
        if( Pattern.compile("[A-Za-z]").matcher(userInput).find() ) {

            System.out.println( "You typed \"" + userInput + "\".");

        } else {

            System.out.println( "Could not validate user input. You probably typed in non-alphabetical characters.");

        }

    }

}