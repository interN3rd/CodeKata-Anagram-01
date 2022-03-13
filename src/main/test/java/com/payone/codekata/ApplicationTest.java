package com.payone.codekata;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName( "AnagramTest" )
class ApplicationTest {

    @Test
    @DisplayName( "test buildWords(): good case with given word 'bowl'" )
    void testMethodBuildWords() {
        List<String> result = AnagramByWordlistLookup.buildWords( "bowl" );
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
    @DisplayName( "test findAnagrams(): good case with given word 'left'" )
    void testMethodFindAnagrams() {
        AnagramByWordlistLookup anagramByWordlistLookup = new AnagramByWordlistLookup( "left" );
        String userInput = anagramByWordlistLookup.getWord();
        List<String> result = anagramByWordlistLookup.findAnagrams(userInput );

        Assertions.assertFalse( result.isEmpty() );
        assertEquals( 2, result.size() );
        Assertions.assertTrue( result.contains( "flet" ) );
        Assertions.assertTrue( result.contains( "felt" ) );
        Assertions.assertFalse( result.contains( "anythingOtherThanLeftStuff" ) );
    }

    @Test
    @DisplayName( "test findAnagrams(): no anagram found for existing word" )
    void testMethodFindAnagramsWithHello() {
        AnagramByWordlistLookup anagramByWordlistLookup = new AnagramByWordlistLookup( "hello" );
        String userInput = anagramByWordlistLookup.getWord();
        List<String> result = anagramByWordlistLookup.findAnagrams( userInput);
        Assertions.assertTrue( result.isEmpty() );
    }

    @Test
    @DisplayName( "test findAnagrams(): no anagram found for NOT existing word" )
    void testMethodFindAnagramsWithNotEvenAWord() {
        AnagramByWordlistLookup anagramByWordlistLookup = new AnagramByWordlistLookup( "yadayada" );
        String userInput = anagramByWordlistLookup.getWord();
        List<String> result = anagramByWordlistLookup.findAnagrams( userInput );
        Assertions.assertTrue( result.isEmpty() );
    }

    @Test
    @DisplayName( "test invalid argument: null value" )
    void testSortingOutInvalidArgumentNullValue() {
        Throwable exception = assertThrows( IllegalArgumentException.class, ()-> AnagramByWordlistLookup.sortOutInvalidArguments(null ) );
        assertEquals("A value of 'null' was received.", exception.getMessage() );
    }

    @Test
    @DisplayName( "test invalid argument: space character meant as 'empty' input" )
    void testSortingOutInvalidArgumentSpaceChar() {
        Throwable exception = assertThrows( IllegalArgumentException.class, ()-> AnagramByWordlistLookup.sortOutInvalidArguments(" ") );
        assertEquals("A non-alphabetical character was received.", exception.getMessage() );
    }

    @Test
    @DisplayName( "test invalid argument: numeric character" )
    void testSortingOutInvalidArgumentNumericChar() {
        Throwable exception = assertThrows( IllegalArgumentException.class, ()-> AnagramByWordlistLookup.sortOutInvalidArguments("r0me") );
        assertEquals("A non-alphabetical character was received.", exception.getMessage() );
    }

    @Test
    @DisplayName( "test invalid argument: special character" )
    void testSortingOutInvalidArgumentSpecialChar() {

        Throwable exception = assertThrows( IllegalArgumentException.class, ()-> AnagramByWordlistLookup.sortOutInvalidArguments("le'baguette") );
        assertEquals("A non-alphabetical character was received.", exception.getMessage() );
    }

    @Test
    @DisplayName( "test invalid argument: empty spaces before word" )
    void testSortingOutInvalidArgumentEmptySpaceBeforeWord() {
        assertThrows( IllegalArgumentException.class, ()-> AnagramByWordlistLookup.sortOutInvalidArguments("  rome") );
    }

    @Test
    @DisplayName( "test invalid argument: empty spaces after word" )
    void testSortingOutInvalidArgumentEmptySpaceAfterWord() {
        assertThrows( IllegalArgumentException.class, ()-> AnagramByWordlistLookup.sortOutInvalidArguments("rome  ") );
    }
}