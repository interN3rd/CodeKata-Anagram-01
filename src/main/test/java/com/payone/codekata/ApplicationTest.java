package com.payone.codekata;

import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName( "AnagramTest" )
class ApplicationTest {

    @Test
    @DisplayName( "test invalid argument: null value" )
    void testSortingOutIllegalArgumentNullValue() {
        Throwable exception = assertThrows( IllegalArgumentException.class, ()-> new AnagramByWordlistLookup( null ) );
        assertEquals("A value of 'null' was received.", exception.getMessage() );
    }

    @Test
    @DisplayName( "test invalid argument: space character meant as 'empty' input" )
    void testSortingOutIllegalArgumentSpaceChar() {
        Throwable exception = assertThrows( IllegalArgumentException.class, ()-> new AnagramByWordlistLookup( " " ) );
        assertEquals("No character was received.", exception.getMessage() );
    }

    @Test
    @DisplayName( "test invalid argument: numeric character" )
    void testSortingOutIllegalArgumentNumericChar() {
        Throwable exception = assertThrows( IllegalArgumentException.class, ()-> new AnagramByWordlistLookup( "r0me" ) );
        assertEquals("A non-alphabetical character was received.", exception.getMessage() );
    }

    @Test
    @DisplayName( "test invalid argument: special character" )
    void testSortingOutIllegalArgumentSpecialChar() {

        Throwable exception = assertThrows( IllegalArgumentException.class, ()-> new AnagramByWordlistLookup( "le'baguette" ) );
        assertEquals("A non-alphabetical character was received.", exception.getMessage() );
    }

    @Test
    @DisplayName( "test findAnagrams(): good case with given word 'left'" )
    void testMethodFindAnagramsOfLeft() throws FileNotFoundException {
        AnagramByWordlistLookup anagramByWordlistLookup = new AnagramByWordlistLookup( "left" );
        String userInput = anagramByWordlistLookup.getWord();
        List<String> result = anagramByWordlistLookup.findAnagrams( userInput );

        Assertions.assertFalse( result.isEmpty() );
        assertEquals( 2, result.size() );
        Assertions.assertTrue( result.contains( "flet" ) );
        Assertions.assertTrue( result.contains( "felt" ) );
        Assertions.assertFalse( result.contains( "anythingOtherThanLeftStuff" ) );
    }

    @Test
    @DisplayName( "test findAnagrams(): good case with a single character" )
    void testMethodFindAnagramsOfSingleCharacter() throws FileNotFoundException {
        AnagramByWordlistLookup anagramByWordlistLookup = new AnagramByWordlistLookup( "a" );
        String userInput = anagramByWordlistLookup.getWord();
        List<String> result = anagramByWordlistLookup.findAnagrams( userInput );

        Assertions.assertTrue( result.isEmpty() );
    }

    @Test
    @DisplayName( "test findAnagrams(): good case with given word 'rome' and leading empty spaces" )
    void testMethodFindAnagramsOfRomeWithLeadingSpaces() throws FileNotFoundException {
        AnagramByWordlistLookup anagramByWordlistLookup = new AnagramByWordlistLookup( "   rome" );
        String userInput = anagramByWordlistLookup.getWord();
        List<String> result = anagramByWordlistLookup.findAnagrams( userInput );

        Assertions.assertFalse( result.isEmpty() );
        assertEquals( 3, result.size() );
        Assertions.assertTrue( result.contains( "omer" ) );
        Assertions.assertTrue( result.contains( "more" ) );
        Assertions.assertTrue( result.contains( "mero" ) );
        Assertions.assertFalse( result.contains( "anythingOtherThanRomeStuff" ) );
    }

    @Test
    @DisplayName( "test findAnagrams(): good case with given word 'rome' and leading empty spaces" )
    void testMethodFindAnagramsOfRomeWithTrailingSpaces() throws FileNotFoundException {
        AnagramByWordlistLookup anagramByWordlistLookup = new AnagramByWordlistLookup( "rome  " );
        String userInput = anagramByWordlistLookup.getWord();
        List<String> result = anagramByWordlistLookup.findAnagrams(userInput );

        Assertions.assertFalse( result.isEmpty() );
        assertEquals( 3, result.size() );
        Assertions.assertTrue( result.contains( "omer" ) );
        Assertions.assertTrue( result.contains( "more" ) );
        Assertions.assertTrue( result.contains( "mero" ) );
        Assertions.assertFalse( result.contains( "anythingOtherThanRomeStuff" ) );
    }

    @Test
    @DisplayName( "test findAnagrams(): no anagram found for existing word" )
    void testMethodFindAnagramsWithHello() throws FileNotFoundException {
        AnagramByWordlistLookup anagramByWordlistLookup = new AnagramByWordlistLookup( "hello" );
        String userInput = anagramByWordlistLookup.getWord();
        List<String> result = anagramByWordlistLookup.findAnagrams( userInput);
        Assertions.assertTrue( result.isEmpty() );
    }

    @Test
    @DisplayName( "test findAnagrams(): no anagram found for NOT existing word" )
    void testMethodFindAnagramsWithNotEvenAWord() throws FileNotFoundException {
        AnagramByWordlistLookup anagramByWordlistLookup = new AnagramByWordlistLookup( "yadayada" );
        String userInput = anagramByWordlistLookup.getWord();
        List<String> result = anagramByWordlistLookup.findAnagrams( userInput );
        Assertions.assertTrue( result.isEmpty() );
    }

    @Test
    @DisplayName( "test buildWords(): good case with given word 'bowl'" )
    void testMethodBuildWords() {

        AnagramByWordlistLookup anagramByWordListLookup = new AnagramByWordlistLookup( "bowl" );
        List<String> result = anagramByWordListLookup.buildWords( anagramByWordListLookup.getWord() );
        Assertions.assertFalse( result.isEmpty() );
        assertEquals( 24, result.size() );
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
    @DisplayName( "test getWordlistContent(): invalid file path")
    void testGetWordlistContentWithInvalidPath() {

        Throwable exception = assertThrows( FileNotFoundException.class, ()-> AnagramByWordlistLookup.getWordlistContent("a/b/c/d") );
        assertEquals("File could not be found.", exception.getMessage() );
    }

    @Test
    @DisplayName( "test getWordlistContent(): empty file")
    void testGetWordlistContentWithEmptyFile() {

        Throwable exception = assertThrows( IllegalArgumentException.class, ()-> AnagramByWordlistLookup.getWordlistContent("src/main/test/resources/empty.txt") );
        assertEquals("The file received is empty.", exception.getMessage() );
    }

    @Test
    @DisplayName( "test getWordlistContent(): wordlist with special characters")
    void testGetWordlistContentWithSpecialCharacters() {

        Throwable exception = assertThrows( IllegalArgumentException.class, ()-> AnagramByWordlistLookup.getWordlistContent("src/main/test/resources/special_chars.txt") );
        assertEquals("A non-alphabetical character was found in this file.", exception.getMessage() );
    }
}