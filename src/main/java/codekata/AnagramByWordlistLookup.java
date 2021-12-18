package codekata;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AnagramByWordlistLookup implements AnagramBuilder {

    public static boolean isValid( final String userInput ) {
        return Pattern.compile( "^[A-Za-z]+$" ).matcher( userInput ).find();
    }

    public static List<String> buildWords( final String userInput ) {
        if( !isValid( userInput ) ) {
            throw new IllegalArgumentException( "An illegal argument was passed to method findAnagrams().");
        }
        List<String> possibleAnagrams = new ArrayList<>();
        if( userInput.length() == 1 ) {
            possibleAnagrams.add( userInput );
            return possibleAnagrams;
        }
        for ( int i = 0; i < userInput.length(); i++ ) {
            String pre = userInput.substring(0, i );
            String post = userInput.substring( i + 1 );
            String remaining = pre + post;
            for ( String permutation : buildWords( remaining ) ) {
                possibleAnagrams.add( userInput.charAt( i ) + permutation );
                }
            }
        return possibleAnagrams;
    }

    public List<String> findAnagrams(final String userInput) throws IOException {

        if( !isValid( userInput ) ) {
            throw new IllegalArgumentException( "An illegal argument was passed to method findAnagrams().");
        }

        if( userInput.length() == 1 ) {
            return new ArrayList<>();
        }

        // create every possible letter combination of the word that the user typed in
        List<String> possibleAnagrams = buildWords( userInput );
        if( possibleAnagrams.isEmpty() || possibleAnagrams.size() == 1 ) {
            return possibleAnagrams;
        }

        // compare every letter combination with a list of english words. If a match is found, it's an anagram
        List<String> anagrams = new ArrayList<>();
        List<String> wordList;
        try (InputStream stream = AnagramByWordlistLookup.class.getResourceAsStream("/english_words_alpha.txt")) {
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( stream ) );
            wordList = bufferedReader.lines().collect(Collectors.toList());
        }

        for ( String anagram : possibleAnagrams ) {
            if ( wordList.contains( anagram ) ) {
                anagrams.add( anagram );
            }
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