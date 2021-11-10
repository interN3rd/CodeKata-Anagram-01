import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
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
        Assertions.assertEquals( "bowl", anagram.getOriginalWord() );
        List<String> result = Anagram.buildWords( anagram.getOriginalWord() );
        Assertions.assertFalse( result.isEmpty() );
        Assertions.assertEquals( 24, result.size() );

        Assertions.assertEquals( "bowl", result.get(0) );
        Assertions.assertEquals( "bolw", result.get(1) );
        Assertions.assertEquals( "bwol", result.get(2) );
        Assertions.assertEquals( "bwlo", result.get(3) );
        Assertions.assertEquals( "blow", result.get(4) );
        Assertions.assertEquals( "blwo", result.get(5) );

        Assertions.assertEquals( "obwl", result.get(6) );
        Assertions.assertEquals( "oblw", result.get(7) );
        Assertions.assertEquals( "owbl", result.get(8) );
        Assertions.assertEquals( "owlb", result.get(9) );
        Assertions.assertEquals( "olbw", result.get(10) );
        Assertions.assertEquals( "olwb", result.get(11) );

        Assertions.assertEquals( "wbol", result.get(12) );
        Assertions.assertEquals( "wblo", result.get(13) );
        Assertions.assertEquals( "wobl", result.get(14) );
        Assertions.assertEquals( "wolb", result.get(15) );
        Assertions.assertEquals( "wlbo", result.get(16) );
        Assertions.assertEquals( "wlob", result.get(17) );

        Assertions.assertEquals( "lbow", result.get(18) );
        Assertions.assertEquals( "lbwo", result.get(19) );
        Assertions.assertEquals( "lobw", result.get(20) );
        Assertions.assertEquals( "lowb", result.get(21) );
        Assertions.assertEquals( "lwbo", result.get(22) );
        Assertions.assertEquals( "lwob", result.get(23) );

    }

    @Test
    @DisplayName( "test buildWords(): illegal argument" )
    void testMethodBuildWordsWithIllegalArgument() {
        Assertions.assertThrows( IllegalArgumentException.class, ()-> Anagram.buildWords( "i4m!ll3gal$"));
    }

    @Test
    @DisplayName( "test findAnagrams(): good case" )
    void testMethodFindAnagrams() throws IOException {
        Anagram anagram = new Anagram( "left");
        Assertions.assertEquals( "left", anagram.getOriginalWord() );
        List<String> possibleAnagrams = Anagram.buildWords( anagram.getOriginalWord() );
        List<String> result = Anagram.findAnagrams( possibleAnagrams );

        Assertions.assertFalse( result.isEmpty() );
        Assertions.assertEquals( 2, result.size() );
        Assertions.assertEquals( "flet", result.get( 0 ) );
        Assertions.assertEquals( "felt", result.get( 1 ) );
    }

    @Test
    @DisplayName( "test findAnagrams(): no anagram found for existing word" )
    void testMethodFindAnagramsWithHello() throws IOException {
        List<String> possibleAnagrams = Anagram.buildWords( "hello");
        List<String> result = Anagram.findAnagrams( possibleAnagrams );
        Assertions.assertTrue( result.isEmpty() );
    }

    @Test
    @DisplayName( "test findAnagrams(): no anagram found for not existing word" )
    void testMethodFindAnagramsWithNotEvenAWord() throws IOException {
        List<String> possibleAnagrams = Anagram.buildWords( "yadayada");
        List<String> result = Anagram.findAnagrams( possibleAnagrams );
        Assertions.assertTrue( result.isEmpty() );
    }

    @Test
    @DisplayName( "test findAnagrams(): illegal argument" )
    void testMethodFindAnagramsWithIllegalArgument() {
        List<String> input = new ArrayList<>();
        input.add( "a" );
        Assertions.assertThrows( IllegalArgumentException.class, ()-> Anagram.findAnagrams( input ) );
    }

    @Test
    @DisplayName( "test findAnagrams(): illegal argument with special characters" )
    void testMethodFindAnagramsWithIllegalArgumentSpecialChars() {
        List<String> input = new ArrayList<>();
        input.add( "i4m!ll3gal$" );
        Assertions.assertThrows( IllegalArgumentException.class, ()-> Anagram.findAnagrams( input ) );
    }

    @Test
    @DisplayName( "test whether logfile exists" )
    void testFileExistence() {
        File log = new File( "logfile.txt" );
        Assertions.assertTrue( log.exists() );
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