import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

@DisplayName( "AnagramTest" )
public class ApplicationTest {
    @Test
    @DisplayName( "test createFile(): file exists" )
    void testMethodCreateFile() {
        File log = Application.createLogfile("logfile.txt" );
        Assertions.assertTrue( log.exists() );
    }
    @Test
    @DisplayName( "test input validation: good case, testing valid input" )
    void testInputValidationGoodCase() {
        String userInput = "lowercase";
        Assertions.assertTrue( Application.isValid( userInput ) );
        userInput = "UPPERCASE";
        Assertions.assertTrue( Application.isValid( userInput ) );
        userInput = "lowerCamelCaseIsGoodCase";
        Assertions.assertTrue( Application.isValid( userInput ) );
        userInput = "PascalCaseIsBestestCaseVongNamenHer";
        Assertions.assertTrue( Application.isValid( userInput ) );
    }
    @Test
    @DisplayName( "test input validation: bad case, testing special characters and numbers" )
    void testInputValidationBadInput() {
        String badUserInput = ".jsp\\0.html";
        Assertions.assertFalse( Application.isValid( badUserInput ) );
        badUserInput = "Hello!";
        Assertions.assertFalse( Application.isValid( badUserInput ) );
        badUserInput = "iC4nC0untFr0m1To99";
        Assertions.assertFalse( Application.isValid( badUserInput ) );
        badUserInput = "PascalCaseIsBestestCase,VongNamenHer";
        Assertions.assertFalse( Application.isValid( badUserInput ) );
    }
    @Test
    @DisplayName( "test buildWords(): good case" )
    void testMethodBuildWords() {
        List<String> result = Application.buildWords( "bowl" );
        Assertions.assertFalse( result.isEmpty() );
        Assertions.assertEquals( 24, result.size() );
        Assertions.assertTrue( result.contains( "bowl" ) );
        Assertions.assertTrue( result.contains( "bolw" ) );
        Assertions.assertTrue( result.contains( "bwol" ) );
        Assertions.assertTrue( result.contains( "bwlo" ) );
        Assertions.assertTrue( result.contains( "blow" ) );
        Assertions.assertTrue( result.contains( "blwo" ) );
        Assertions.assertTrue( result.contains( "obwl" ) );
        Assertions.assertTrue( result.contains( "oblw" ) );
        Assertions.assertTrue( result.contains( "owbl" ) );
        Assertions.assertTrue( result.contains( "owlb" ) );
        Assertions.assertTrue( result.contains( "olbw" ) );
        Assertions.assertTrue( result.contains( "olwb" ) );
        Assertions.assertTrue( result.contains( "wbol" ) );
        Assertions.assertTrue( result.contains( "wblo" ) );
        Assertions.assertTrue( result.contains( "wobl" ) );
        Assertions.assertTrue( result.contains( "wolb" ) );
        Assertions.assertTrue( result.contains( "wlbo" ) );
        Assertions.assertTrue( result.contains( "wlob" ) );
        Assertions.assertTrue( result.contains( "lbow" ) );
        Assertions.assertTrue( result.contains( "lbwo" ) );
        Assertions.assertTrue( result.contains( "lobw" ) );
        Assertions.assertTrue( result.contains( "lowb" ) );
        Assertions.assertTrue( result.contains( "lwbo" ) );
        Assertions.assertTrue( result.contains( "lwob" ) );
        Assertions.assertFalse( result.contains( "anythingOtherThanBowlStuff" ) );
    }
    @Test
    @DisplayName( "test buildWords(): illegal argument" )
    void testMethodBuildWordsWithIllegalArgument() {
        Assertions.assertThrows( IllegalArgumentException.class, ()-> Application.buildWords( "i4m!ll3gal$"));
    }
    @Test
    @DisplayName( "test findAnagrams(): good case" )
    void testMethodFindAnagrams() throws IOException {
        List<String> result = Application.findAnagrams( "left" );

        Assertions.assertFalse( result.isEmpty() );
        Assertions.assertEquals( 2, result.size() );
        Assertions.assertTrue( result.contains( "flet" ) );
        Assertions.assertTrue( result.contains( "felt" ) );
        Assertions.assertFalse( result.contains( "anythingOtherThanLeftStuff" ) );
    }
    @Test
    @DisplayName( "test findAnagrams(): no anagram found for existing word" )
    void testMethodFindAnagramsWithHello() throws IOException {
        List<String> result = Application.findAnagrams( "hello" );
        Assertions.assertTrue( result.isEmpty() );
    }
    @Test
    @DisplayName( "test findAnagrams(): no anagram found for not existing word" )
    void testMethodFindAnagramsWithNotEvenAWord() throws IOException {
        List<String> result = Application.findAnagrams( "yadayada" );
        Assertions.assertTrue( result.isEmpty() );
    }
    @Test
    @DisplayName( "test findAnagrams(): illegal argument" )
    void testMethodFindAnagramsWithIllegalArgument() throws IOException {
        List<String> result = Application.findAnagrams( "a" );
        Assertions.assertTrue( result.isEmpty() );
    }
    @Test
    @DisplayName( "test findAnagrams(): illegal argument with special characters" )
    void testMethodFindAnagramsWithIllegalArgumentSpecialChars() {
        Assertions.assertThrows( IllegalArgumentException.class, ()-> Application.findAnagrams( "i4m!ll3gal$" ) );
    }
}