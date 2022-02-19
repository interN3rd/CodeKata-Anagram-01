package com.payone.codekata;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AnagramByWordlistLookup implements AnagramFinder {

    private String word;

    public AnagramByWordlistLookup( final String word ) {
        sortOutInvalidAnagramCandidate( word );
        this.setWord( word );
    }

    public String getWord() {
        return word;
    }

    public void setWord( final String word ) {
        this.word = word;
    }

    public static void sortOutInvalidAnagramCandidate(final String word ) {

        if( word == null ) {

            throw new IllegalArgumentException( "A value of 'null' was received." );
        }

        if( word.length() < 2 || word.length() > 27 ) {

            throw new IllegalArgumentException( "A word must consists of at least two characters to have anagrams. The longest known word to have anagrams consists of 27 characters." );
        }

        if( Pattern.compile( "^ +[A-Za-z]+$" ).matcher( word ).find() ) {

            throw new IllegalArgumentException( "Value must not begin with empty spaces." );
        }

        if( Pattern.compile( "^+[A-Za-z]+ +$" ).matcher( word ).find() ) {

            throw new IllegalArgumentException( "Value must not end with empty spaces." );
        }

        if( !Pattern.compile( "^[A-Za-z]+$" ).matcher( word ).find() ) {

            throw new IllegalArgumentException( "A non-alphabetical character was received." );
        }
    }

    public static void sortOutInvalidArguments(final String word ) {

        if( word == null ) {

            throw new IllegalArgumentException( "A value of 'null' was received." );
        }

        if( word.length() < 1 || word.length() > 27 ) {

            throw new IllegalArgumentException( "A word must consists of at least two characters to have anagrams. The longest known word to have anagrams consists of 27 characters." );
        }

        if( Pattern.compile( "^ +[A-Za-z]+$" ).matcher( word ).find() ) {

            throw new IllegalArgumentException( "Value must not begin with empty spaces." );
        }

        if( Pattern.compile( "^+[A-Za-z]+ +$" ).matcher( word ).find() ) {

            throw new IllegalArgumentException( "Value must not end with empty spaces." );
        }

        if( !Pattern.compile( "^[A-Za-z]+$" ).matcher( word ).find() ) {

            throw new IllegalArgumentException( "A non-alphabetical character was received." );
        }
    }


    public static List<String> buildWords( final String word ) {

        sortOutInvalidArguments( word );

        List<String> possibleAnagrams = new ArrayList<>();
        
        if( word.length() == 1 ) {
            possibleAnagrams.add( word );
            return possibleAnagrams;
        }
        for ( int i = 0; i < word.length(); i++ ) {
            String pre = word.substring(0, i );
            String post = word.substring( i + 1 );
            String remaining = pre + post;
            for ( String permutation : buildWords( remaining ) ) {
                possibleAnagrams.add( word.charAt( i ) + permutation );
                }
            }

        return possibleAnagrams;
    }

    public List<String> findAnagrams( final String word ) throws IOException {

        sortOutInvalidArguments( word );

        if( word.length() == 1 ) {
            return new ArrayList<>();
        }

        // create every possible letter combination of the word that the user typed in
        List<String> possibleAnagrams = buildWords( word );
        if( possibleAnagrams.isEmpty() || possibleAnagrams.size() == 1 ) {
            return possibleAnagrams;
        }

        // compare every letter combination with a list of english words. If a match is found, it's an anagram
        List<String> anagrams = new ArrayList<>();
        List<String> wordList;
        try (InputStream stream = AnagramByWordlistLookup.class.getResourceAsStream("/english_words_alpha.txt")) {
            assert stream != null;
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( stream ) );
            wordList = bufferedReader.lines().collect(Collectors.toList());
        }

        // by definition an anagram consists of the same amount of letters like the original word
        // example: if the user types in "owl" an anagram would be "low"
        // both words should be in the wordlist
        // therefore: if the word doesn't even come up in the wordlist, there will be no anagram
        if( wordList.contains( word) ) {
            for ( String anagram : possibleAnagrams ) {
                if ( wordList.contains( anagram ) ) {
                    anagrams.add( anagram );
                }
            }
        } else {
            return new ArrayList<>();
        }

        // "yadayada" is a word that is fully processed but won't produce any anagrams
        if ( anagrams.isEmpty() ) {
            return new ArrayList<>();
        }
        // "hello" has no anagrams, but at this point anagrams consists of [hello, hello]
        if ( anagrams.get( 0 ).equals( anagrams.get( 1 ) ) ) {
            return new ArrayList<>();
        }
        // the first entry of anagrams is always the original word which is no anagram
        return anagrams.subList(1, anagrams.size());
    }
}