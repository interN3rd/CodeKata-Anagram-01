package com.payone.codekata;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AnagramByWordlistLookup implements AnagramFinder {

    private static final Pattern PATTERN = Pattern.compile( "^[A-Za-z ]+$" );

    private final List<String> wordlist;

    private AnagramByWordlistLookup( List<String> filteredWordlist ) {

        this.wordlist = filteredWordlist;
    }

    public static AnagramByWordlistLookup create( List<String> wordlist ) {

        return new AnagramByWordlistLookup( wordlist.stream()
                .filter( line -> PATTERN.matcher( line ).matches() )
                .map( String::toLowerCase )
                .collect( Collectors.toList() ) );
    }

    /**
     *
     * @param word should consist of one or mor ASCII character signs
     * @return an empty ArrayList<>() if the given word could not have or does not have any anagrams
     *     could not have: single characters, fantasy words or random input like apoigfnwui
     *     does not have: existing words like 'hello' or 'extinguisher'
     */
    public List<String> findAnagrams( final String word ) {

        String anagramCandidate = validateAnagramCandidate( word );

        // there are no anagrams for a single character
        // in that case a new empty list is return as it is not a critical error
        if( anagramCandidate.length() == 1 ) {

            return new ArrayList<>();
        }

        // create every possible letter combination of the word that the user typed in
        List<String> possibleAnagrams = buildWords( anagramCandidate );
        if( possibleAnagrams.isEmpty() || possibleAnagrams.size() == 1 ) {
            return possibleAnagrams;
        }

        // compare every letter combination with a list of english words. If a match is found, it's an anagram
        List<String> anagrams = new ArrayList<>();

        // by definition an anagram consists of the same amount of letters like the original word
        // example: if the user types in "owl" an anagram would be "low"
        // both words should be in the wordlist
        // therefore: if the word doesn't even come up in the wordlist, there will be no anagram
        if( wordlist.contains( anagramCandidate ) ) {

            for ( String anagram : possibleAnagrams ) {

                if ( wordlist.contains( anagram ) ) {

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

    private String validateAnagramCandidate( final String word ) {

        if( word == null ) {

            throw new IllegalArgumentException( "A value of 'null' was received." );
        }

        if( word.trim().isEmpty() ) {

            throw new IllegalArgumentException( "No character was received." );
        }

        if( word.length() > 27 ) {

            throw new IllegalArgumentException( "Received an unexpected value. The longest known word to have anagrams consists of 27 characters." );
        }

        if( !PATTERN.matcher( word ).find() ) {

            throw new IllegalArgumentException( "A non-alphabetical character was received." );
        }

        return word.trim().toLowerCase();
    }

    List<String> buildWords( final String word ) {

        String anagramCandidate = validateAnagramCandidate( word );

        List<String> possibleAnagrams = new ArrayList<>();
        
        if( anagramCandidate.length() == 1 ) {
            possibleAnagrams.add( anagramCandidate );
            return possibleAnagrams;
        }

        for ( int i = 0; i < anagramCandidate.length(); i++ ) {
            String pre = anagramCandidate.substring(0, i );
            String post = anagramCandidate.substring( i + 1 );
            String remaining = pre + post;
            for ( String permutation : buildWords( remaining ) ) {
                possibleAnagrams.add( anagramCandidate.charAt( i ) + permutation );
                }
            }

        return possibleAnagrams;
    }
}