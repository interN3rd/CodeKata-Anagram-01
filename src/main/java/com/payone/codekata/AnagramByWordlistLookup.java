package com.payone.codekata;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AnagramByWordlistLookup implements AnagramFinder {

    Logger logger;
    private String word;

    public AnagramByWordlistLookup( final String word ) {

        sortOutInvalidArguments( word );
        this.setWord( word );
    }

    public String getWord() {

        return word;
    }

    public void setWord( final String word ) {

        this.word = word;
    }

    public List<String> findAnagrams( final String word ) {

        // there are no anagrams for a single character
        // in that case a new empty list is return as it is not a critical error
        if( word.length() == 1 ) {
            return new ArrayList<>();
        }

        // in case this method receives a string that is at least two characters long, malicious input needs to be handled
        sortOutInvalidArguments( word );

        // anagrams are found by looking up entries of a wordlist. There has to be a wordlist accessible then
        List<String> wordList = getWordlistContent("src/main/test/resources/english_words_alpha.txt");

        // create every possible letter combination of the word that the user typed in
        List<String> possibleAnagrams = buildWords( word );
        if( possibleAnagrams.isEmpty() || possibleAnagrams.size() == 1 ) {
            return possibleAnagrams;
        }

        // compare every letter combination with a list of english words. If a match is found, it's an anagram
        List<String> anagrams = new ArrayList<>();

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

    public List<String> getWordlistContent( final String pathToWordlist )  {
        List<String> wordList;

        try ( BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( new FileInputStream( pathToWordlist ) ) ) ) {
            wordList = bufferedReader.lines().collect( Collectors.toList() );
            return wordList;
        } catch( IOException exception ) {
            logger.severe( "File could not be found." );
        }

        return new ArrayList<>();
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
}