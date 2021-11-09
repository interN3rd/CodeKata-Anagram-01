import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
    @DisplayName( "test whether logfile exists" )
    void testFileExistence() {
        File log = new File( "logfile.txt" );
        Assertions.assertTrue( log.exists() );
    }

    @Test
    @DisplayName( "test input validation: expected behavior" )
    void testInputValidationGoodCase() {
        String userInput = "lowercase";
        Assertions.assertTrue( Validator.isValidUserInput( userInput ) );
        userInput = "UPPERCASE";
        Assertions.assertTrue( Validator.isValidUserInput( userInput ) );
        userInput = "lowerCamelCaseIsGoodCase";
        Assertions.assertTrue( Validator.isValidUserInput( userInput ) );
        userInput = "PascalCaseIsBestestCaseVongNamenHer";
        Assertions.assertTrue( Validator.isValidUserInput( userInput ) );
    }

    @Test
    @DisplayName( "test input validation: bad user input" )
    void testInputValidationBadInput() {
        String badUserInput = ".jsp\\0.html";
        Assertions.assertFalse( Validator.isValidUserInput( badUserInput ) );
        badUserInput = "Hello!";
        Assertions.assertFalse( Validator.isValidUserInput( badUserInput ) );
        badUserInput = "iC4nC0untFr0m1To99";
        Assertions.assertFalse( Validator.isValidUserInput( badUserInput ) );
        badUserInput = "PascalCaseIsBestestCase,VongNamenHer";
        Assertions.assertFalse( Validator.isValidUserInput( badUserInput ) );
    }

    @Test
    @DisplayName( "test constructor" )
    void testConstructorValidInput() {
        Anagram anagram = new Anagram( "testingConstructorArgument" );
        Assertions.assertTrue( Validator.isValidUserInput( "testingConstructorArgument" ) );
        Assertions.assertEquals( "testingConstructorArgument", anagram.getOriginalWord() );
    }

    @Test
    @DisplayName( "test constructor with illegal argument" )
    void testConstructorIllegalArgument() {
        Assertions.assertFalse( Validator.isValidUserInput( "i4m!ll3gal$" ) );
        Assertions.assertThrows( IllegalArgumentException.class, ()-> new Anagram( "i4m!ll3gal$" ));
    }

    @Test
    @DisplayName( "test buildWords(): good case" )
    void testMethodBuildWords() {
        Anagram anagram = new Anagram( "bowl");
        Anagram.buildWords(anagram.getOriginalWord() );
        Assertions.assertFalse( Validator.isNullOrEmpty(Anagram.buildWords(anagram.getOriginalWord() )));
    }

    @Test
    @DisplayName( "test buildWords(): illegal argument" )
    void testMethodBuildWordsWithIllegalArgument() {
        Assertions.assertThrows( IllegalArgumentException.class, ()-> Anagram.buildWords( "i4m!ll3gal$"));
    }

    @Test
    @DisplayName( "test findAnagrams(): good case" )
    void testMethodFindAnagrams() throws IOException {
        Assertions.assertFalse( Validator.isNullOrEmpty( Anagram.findAnagrams(Anagram.buildWords( "bowl") ) ) );
    }

    @Test
    @DisplayName( "test findAnagrams(): no anagram found" )
    void testMethodFindAnagramsWithHello() throws IOException {
        List<String> possibleAnagrams = Anagram.buildWords( "hello");
        List<String> anagrams = new ArrayList<>();
        List<String> wordList = Files.readAllLines( Paths.get( AppConstants.pathToWordlist ), StandardCharsets.UTF_8 );
        for (String anagram : possibleAnagrams) {
            if (wordList.contains(anagram)) {
                anagrams.add(anagram);
            }
        }
        Assertions.assertEquals( anagrams.get( 0 ), anagrams.get( 1 ) );
    }

    @Test
    @DisplayName( "test findAnagrams(): not even a word" )
    void testMethodFindAnagramsWithNotEvenAWord() throws IOException {
        Assertions.assertTrue( Validator.isNullOrEmpty( Anagram.findAnagrams(Anagram.buildWords( "yadayada") ) ) );
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