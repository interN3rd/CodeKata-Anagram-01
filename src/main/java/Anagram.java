import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

public class Anagram {

    private final String originalWord;

    public String getOriginalWord() {
        return originalWord;
    }

    // constructor is being called after input validation so it's safer to work with
    public Anagram( String originalWord ) {

        if( !Validator.isValidUserInput( originalWord ) ) {
            throw new IllegalArgumentException( "An illegal argument was passed to constructor of class Anagram.");
        }

        this.originalWord = originalWord;
    }

    /**
     *
     * @param originalWord validated user input
     * @return String array that contains every possible "word" from the given char array; goal is to compare created
     * words with a wordlist to identify existing anagrams
     */
    static List<String> buildWords( final String originalWord ) {
        if( !Validator.isValidUserInput( originalWord ) ) {
            throw new IllegalArgumentException( "An illegal argument was passed to parameter list of method buildWords().");
        }
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

    static List<String> findAnagrams( final List<String> possibleAnagrams ) throws IOException {
        if( possibleAnagrams.get( 0 ).length() == 1 ) {
            throw new IllegalArgumentException( "An illegal argument was passed to parameter list of method findAnagrams()." );
        }
        if( Pattern.compile( "[0-9!\"§$%&/()=?´²³{}@€~+*#'<>|,.-;:_^°*-+]+" ).matcher( possibleAnagrams.get( 0 ) ).find() ) {
            throw new IllegalArgumentException( "An illegal argument was passed to parameter list of method findAnagrams()." );
        }

        List<String> emptyResult = new ArrayList<>();
        List<String> anagrams = new ArrayList<>();
        List<String> wordList = Files.readAllLines( Paths.get( AppConstants.pathToWordlist ), StandardCharsets.UTF_8 );
        for (String anagram : possibleAnagrams) {
            if (wordList.contains(anagram)) {
                anagrams.add(anagram);
            }
        }

        // "yadayada" is a word that is fully processed but won't produce any anagrams
        if( anagrams.isEmpty() ) {
            return emptyResult;
        }

        // "hello" has no anagrams, but at this point anagrams consists of [hello, hello]
        if( anagrams.get( 0 ).equals( anagrams.get( 1 ) ) ) {
            return emptyResult;
        }

        // "bowl" has exactly one anagram: blow. At this point anagrams consists of [bowl, blow], whereas bowl is no anagram
        if( anagrams.size() == 2 ) {
            List<String> result = new ArrayList<>();
            result.add( anagrams.get(1) );
            return result;
        }

        // the first entry of anagrams is always the original word which is no anagram
        return anagrams.subList( 1, anagrams.size() );
    }
}
