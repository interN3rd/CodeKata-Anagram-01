package com.payone.codekata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName( "AnagramTest" )
class AnagramByWordlistLookupTest {

    @ParameterizedTest
    @CsvSource(value = {
            ",A value of 'null' was received.", // null
            "'',No character was received.", // empty
            "' ',No character was received.", // blank
            "r0me,A non-alphabetical character was received.",
            "le'baguette,A non-alphabetical character was received."
    })
    @DisplayName( "test method findAnagrams() with invalid anagram candidates" )
    void testValidationOfInvalidAnagramCandidates( String invalidAnagramCandidate, final String expectedErrorMsg ) throws IOException {

        AnagramByWordlistLookup anagramByWordlistLookup = AnagramByWordlistLookup.create( Files.readAllLines( Paths.get("src/main/test/resources/english_words_alpha.txt" ) ) );

        Throwable exception = assertThrows( IllegalArgumentException.class, ()-> anagramByWordlistLookup.findAnagrams( invalidAnagramCandidate ) );

        assertEquals( expectedErrorMsg, exception.getMessage() );
    }

    @Test
    @DisplayName( "test findAnagrams(): good case with given word 'left'" )
    void testMethodFindAnagramsOfLeft() throws IOException {

        AnagramByWordlistLookup anagramByWordlistLookup = AnagramByWordlistLookup.create( Files.readAllLines( Paths.get("src/main/test/resources/english_words_alpha.txt" ) ) );
        List<String> result = anagramByWordlistLookup.findAnagrams( "left" );

        Assertions.assertFalse( result.isEmpty() );
        assertEquals( 2, result.size() );
        Assertions.assertTrue( result.contains( "flet" ) );
        Assertions.assertTrue( result.contains( "felt" ) );
        Assertions.assertFalse( result.contains( "anythingOtherThanLeftStuff" ) );
    }

    @ParameterizedTest
    @ValueSource( strings = { "a", "hello", "yadayada" } )
    void testMethodFindAnagramsShouldReturnEmptyResult( final String anagramCandidate ) throws IOException {

        AnagramByWordlistLookup anagramByWordlistLookup = AnagramByWordlistLookup.create( Files.readAllLines( Paths.get("src/main/test/resources/english_words_alpha.txt" ) ) );
        List<String> result = anagramByWordlistLookup.findAnagrams( anagramCandidate );
        Assertions.assertTrue( result.isEmpty() );
    }

    @Test
    @DisplayName( "test findAnagrams(): good case with given word 'rome' and leading empty spaces" )
    void testMethodFindAnagramsOfRomeWithLeadingSpaces() throws IOException {

        AnagramByWordlistLookup anagramByWordlistLookup = AnagramByWordlistLookup.create( Files.readAllLines( Paths.get("src/main/test/resources/english_words_alpha.txt" ) ) );
        List<String> result = anagramByWordlistLookup.findAnagrams( "   rome" );

        Assertions.assertFalse( result.isEmpty() );
        assertEquals( 3, result.size() );
        Assertions.assertTrue( result.contains( "omer" ) );
        Assertions.assertTrue( result.contains( "more" ) );
        Assertions.assertTrue( result.contains( "mero" ) );
        Assertions.assertFalse( result.contains( "anythingOtherThanRomeStuff" ) );
    }

    @Test
    @DisplayName( "test findAnagrams(): good case with given word 'rome' and trailing empty spaces" )
    void testMethodFindAnagramsOfRomeWithTrailingSpaces() throws IOException {

        AnagramByWordlistLookup anagramByWordlistLookup = AnagramByWordlistLookup.create( Files.readAllLines( Paths.get("src/main/test/resources/english_words_alpha.txt" ) ) );
        List<String> result = anagramByWordlistLookup.findAnagrams( "rome  " );

        Assertions.assertFalse( result.isEmpty() );
        assertEquals( 3, result.size() );
        Assertions.assertTrue( result.contains( "omer" ) );
        Assertions.assertTrue( result.contains( "more" ) );
        Assertions.assertTrue( result.contains( "mero" ) );
        Assertions.assertFalse( result.contains( "anythingOtherThanRomeStuff" ) );
    }

    @Test
    @DisplayName( "test findAnagrams(): good case with given word 'rome' written in camel case" )
    void testMethodFindAnagramsOfRomeInCamelCase() throws IOException {

        AnagramByWordlistLookup anagramByWordlistLookup = AnagramByWordlistLookup.create( Files.readAllLines( Paths.get("src/main/test/resources/english_words_alpha.txt" ) ) );
        List<String> result = anagramByWordlistLookup.findAnagrams( "RoMe" );

        Assertions.assertFalse( result.isEmpty() );
        assertEquals( 3, result.size() );
        Assertions.assertTrue( result.contains( "omer" ) );
        Assertions.assertTrue( result.contains( "more" ) );
        Assertions.assertTrue( result.contains( "mero" ) );
        Assertions.assertFalse( result.contains( "anythingOtherThanRomeStuff" ) );
    }

    @Test
    @DisplayName( "test buildWords(): good case with given word 'bowl'" )
    void testMethodBuildWords() throws IOException {

        AnagramByWordlistLookup anagramByWordlistLookup = AnagramByWordlistLookup.create( Files.readAllLines( Paths.get("src/main/test/resources/english_words_alpha.txt" ) ) );
        List<String> result = anagramByWordlistLookup.buildWords( "bowl" );
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
}