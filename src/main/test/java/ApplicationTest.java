import org.junit.jupiter.api.*;
import java.util.regex.Pattern;

@DisplayName( "AnagramTest" )
public class ApplicationTest {

    @BeforeAll
    static void beforeAll() {

        System.out.println( "[ INFO ] Testing is about to start.");

    }

    @BeforeEach
    void beforeEach() {

        System.out.println( "[ INFO ] tests starting.");

    }

    @Test
    @DisplayName( "test value of string constants" )
    void testValueOfStringConstants() {

        Assertions.assertEquals( "########## Codekata: Anagram 101 ##########".toUpperCase(), AppConstants.headline);
        Assertions.assertEquals( "Welcome to Anagram 101!", AppConstants.welcomeMessage);
        Assertions.assertEquals( "This program tells you to type in any word. Please type in any word then. The application validates your input, searches for anagrams and prints any results to the console.\n", AppConstants.aboutAnagram);
        Assertions.assertEquals( "Could not validate user input. You probably typed in non-alphabetical characters.", AppConstants.errorMSGInputNotValidated);
        Assertions.assertEquals( "There is no anagram for this word.", AppConstants.noAnagramToBeFound);
        Assertions.assertEquals( "src/main/resources/english_words_alpha.txt", AppConstants.pathToWordlist);

    }

    @Test
    @DisplayName( "test input validation: expected behavior" )
    void testInputValidationGoodCase() {

        String userInput = "Listen";
        Assertions.assertTrue( Pattern.compile("^[A-Za-z]+$").matcher(userInput).find() );
        Assertions.assertTrue( Validator.isValidUserInput( userInput ) );

    }

    @Test
    @DisplayName( "test input validation: bad user input" )
    void testInputValidationBadInput() {

        String badUserInput = ".jsp\\0.html";
        Assertions.assertFalse( Pattern.compile("^[A-Za-z]+$").matcher(badUserInput).find() );
        Assertions.assertFalse( Validator.isValidUserInput( badUserInput ) );

    }

    @Test
    @DisplayName( "word that has no anagram")
    void testWordThatHasNoAnagram() {

        Assertions.assertFalse( Validator.hasAnagrams( "hello" ));

        }

    @Test
    @DisplayName( "word that hast at least one anagram")
    void testWordThatHasAtLeastOneAnagram() {

        Assertions.assertTrue( Validator.hasAnagrams( "bowl" ));

    }

    @AfterEach
    public void afterEach() {

        System.out.println( "[ INFO ] test completed." );

    }

    @AfterAll
    public static void afterAll() {

        System.out.println( "[ INFO ] Testing completed.");

    }

}