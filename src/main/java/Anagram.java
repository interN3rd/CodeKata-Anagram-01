import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Anagram {

    // attributes
    private final String originalWord;

    // getter & setter
    public String getOriginalWord() {
        return originalWord;
    }

    // constructor is being called after input validation so it's safer to work with
    public Anagram( String originalWord ) {
        this.originalWord = originalWord;
    }

    /**
     *
     * @param originalWord validated user input
     * @return String array that contains every possible "word" from the given char array; goal is to compare created
     * words with a wordlist to identify existing anagrams
     */
    static List<String> buildWords(String originalWord) {
        List<String> possibleAnagrams = new ArrayList<>();
        if( originalWord.length() == 1 ) {
            possibleAnagrams.add( originalWord );
        } else {
            for( int i = 0; i < originalWord.length(); i++ ) {
                String pre = originalWord.substring( 0, i ) ;
                String post = originalWord.substring( i + 1 );
                String remaining = pre + post;
                for( String permutation : buildWords( remaining ) ) {
                    possibleAnagrams.add( originalWord.charAt(i) + permutation );
                }
            }
        }
        return possibleAnagrams;
    }

    static List<String> findAnagrams( List<String> possibleAnagrams ) throws IOException {
        List<String> anagrams = new ArrayList<>();
        List<String> emptyResult = new ArrayList<>();
        List<String> wordList = Files.readAllLines( Paths.get( AppConstants.pathToWordlist ), StandardCharsets.UTF_8 );
        for (String anagram : possibleAnagrams) {
            if (wordList.contains(anagram)) {
                anagrams.add(anagram);
            }
        }
        if( anagrams.get( 0 ).equals( anagrams.get( 1 ) ) ) {
            return emptyResult;
        }
        return anagrams;
    }
}
