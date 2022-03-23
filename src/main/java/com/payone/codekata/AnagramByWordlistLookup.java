package com.payone.codekata;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AnagramByWordlistLookup implements AnagramFinder {

    private final File textFile;

    public AnagramByWordlistLookup( File textFile ) {

        this.textFile = textFile;
    }

    static Logger logger;

    /**
     *
     * @param word should consists of one or mor ASCII character signs
     * @return an empty ArrayList<>() if the given word could not have or does not have any anagrams
     *     could not have: single characters, fantasy words or random input like apoigfnwui
     *     does not have: existing words like 'hello' or 'extinguisher'
     */
    public List<String> findAnagrams( final String word ) throws FileNotFoundException {

        String anagramCandidate = validateAnagramCandidate( word );

        // there are no anagrams for a single character
        // in that case a new empty list is return as it is not a critical error
        if( anagramCandidate.length() == 1 ) {

            return new ArrayList<>();
        }

        // anagrams are found by looking up entries of a wordlist. There has to be a wordlist accessible then
        List<String> wordList = getWordlistContent();
        // or something like: askForWordlistFile()?
        validateWordlistContent( wordList );

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
        if( wordList.contains( anagramCandidate ) ) {
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

        if( !Pattern.compile( "^[A-Za-z ]+$" ).matcher( word ).find() ) {

            throw new IllegalArgumentException( "A non-alphabetical character was received." );
        }

        return word.trim();
    }

    List<String> getWordlistContent() throws FileNotFoundException {

        List<String> wordList;

        if( this.textFile.exists() && !this.textFile.isDirectory() ) {

            if( this.textFile.length() == 0 ) {

                throw new IllegalArgumentException( "The file received is empty." );
            }

            try ( BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( new FileInputStream( this.textFile.getPath() ) ) ) ) {

                wordList = bufferedReader.lines().collect( Collectors.toList() );

                return wordList;

            } catch( IOException exception ) {

                logger.severe( "File could not be read." );
            }

        } else {

            throw new FileNotFoundException( "File could not be found." );
        }

        return new ArrayList<>();
    }

    static void validateWordlistContent( final List<String> list ) {

        for (String singleListEntry : list) {

            if (!Pattern.compile("^[A-Za-z ]+$").matcher(singleListEntry).find()) {

                throw new IllegalArgumentException("A non-alphabetical character was found in this file.");
            }
        }
    }
}