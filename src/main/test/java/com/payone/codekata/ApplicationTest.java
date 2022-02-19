package com.payone.codekata;

import org.junit.jupiter.api.*;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName( "AnagramTest" )
class ApplicationTest {

    @Test
    @DisplayName( "test buildWords(): good case" )
    void testMethodBuildWords() {
        List<String> result = AnagramByWordlistLookup.buildWords( "bowl" );
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
    @DisplayName( "test findAnagrams(): good case" )
    void testMethodFindAnagrams() throws IOException {
        AnagramByWordlistLookup anagramByWordlistLookup = new AnagramByWordlistLookup("left");
        String userInput = anagramByWordlistLookup.getWord();
        List<String> result = anagramByWordlistLookup.findAnagrams(userInput);

        Assertions.assertFalse( result.isEmpty() );
        Assertions.assertEquals( 2, result.size() );
        Assertions.assertTrue( result.contains( "flet" ) );
        Assertions.assertTrue( result.contains( "felt" ) );
        Assertions.assertFalse( result.contains( "anythingOtherThanLeftStuff" ) );
    }

    @Test
    @DisplayName( "test findAnagrams(): no anagram found for existing word" )
    void testMethodFindAnagramsWithHello() throws IOException {
        AnagramByWordlistLookup anagramByWordlistLookup = new AnagramByWordlistLookup("hello");
        String userInput = anagramByWordlistLookup.getWord();
        List<String> result = anagramByWordlistLookup.findAnagrams( userInput );
        Assertions.assertTrue( result.isEmpty() );
    }

    @Test
    @DisplayName( "test findAnagrams(): no anagram found for not existing word" )
    void testMethodFindAnagramsWithNotEvenAWord() throws IOException {
        AnagramByWordlistLookup anagramByWordlistLookup = new AnagramByWordlistLookup("yadayada");
        String userInput = anagramByWordlistLookup.getWord();
        List<String> result = anagramByWordlistLookup.findAnagrams( userInput );
        Assertions.assertTrue( result.isEmpty() );
    }

    @Test
    @DisplayName( "test invalid anagram candidate: null value" )
    void testSortingOutInvalidAnagramCandidateNullValue() {
        assertThrows( IllegalArgumentException.class, ()-> AnagramByWordlistLookup.sortOutInvalidAnagramCandidate(null ) );
    }

    @Test
    @DisplayName( "test invalid anagram candidate: space character meant as 'empty' input" )
    void testSortingOutInvalidAnagramCandidateSpaceChar() {
        assertThrows( IllegalArgumentException.class, ()-> AnagramByWordlistLookup.sortOutInvalidAnagramCandidate(" ") );
    }

    @Test
    @DisplayName( "test invalid anagram candidate: single character" )
    void testSortingOutInvalidAnagramCandidateSingleChar() {
        assertThrows( IllegalArgumentException.class, ()-> AnagramByWordlistLookup.sortOutInvalidAnagramCandidate("a") );
    }

    @Test
    @DisplayName( "test invalid anagram candidate: numeric character" )
    void testSortingOutInvalidAnagramCandidateNumericChar() {
        assertThrows( IllegalArgumentException.class, ()-> AnagramByWordlistLookup.sortOutInvalidAnagramCandidate("r0me") );
    }

    @Test
    @DisplayName( "test invalid anagram candidate: special character" )
    void testSortingOutInvalidAnagramCandidateSpecialChar() {
        assertThrows( IllegalArgumentException.class, ()-> AnagramByWordlistLookup.sortOutInvalidAnagramCandidate("le'baguette") );
    }

    @Test
    @DisplayName( "test invalid anagram candidate: empty spaces before word" )
    void testSortingOutInvalidAnagramCandidateEmptySpaceBeforeWord() {
        assertThrows( IllegalArgumentException.class, ()-> AnagramByWordlistLookup.sortOutInvalidAnagramCandidate("  rome") );
    }

    @Test
    @DisplayName( "test invalid anagram candidate: empty spaces after word" )
    void testSortingOutInvalidAnagramCandidateEmptySpaceAfterWord() {
        assertThrows( IllegalArgumentException.class, ()-> AnagramByWordlistLookup.sortOutInvalidAnagramCandidate("rome  ") );
    }

    @Test
    @DisplayName( "test invalid argument: null value" )
    void testSortingOutInvalidArgumentNullValue() {
        assertThrows( IllegalArgumentException.class, ()-> AnagramByWordlistLookup.sortOutInvalidArguments(null ) );
    }

    @Test
    @DisplayName( "test invalid argument: space character meant as 'empty' input" )
    void testSortingOutInvalidArgumentSpaceChar() {
        assertThrows( IllegalArgumentException.class, ()-> AnagramByWordlistLookup.sortOutInvalidArguments(" ") );
    }

    @Test
    @DisplayName( "test invalid argument: numeric character" )
    void testSortingOutInvalidArgumentNumericChar() {
        assertThrows( IllegalArgumentException.class, ()-> AnagramByWordlistLookup.sortOutInvalidArguments("r0me") );
    }

    @Test
    @DisplayName( "test invalid argument: special character" )
    void testSortingOutInvalidArgumentSpecialChar() {
        assertThrows( IllegalArgumentException.class, ()-> AnagramByWordlistLookup.sortOutInvalidArguments("le'baguette") );
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