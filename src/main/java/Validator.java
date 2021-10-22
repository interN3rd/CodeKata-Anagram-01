import java.util.Objects;
import java.util.regex.Pattern;

public class Validator {

    static final String[] knownToHaveAnagrams = {

            "ah", "am", "eh", "ho", "no", "act", "add", "aft", "amp", "ant", "ape", "apt", "arc", "are", "arm", "art",
            "ate", "bad", "bag", "bar", "bat", "but", "cab", "car", "cat", "dab", "dad", "dam", "dew", "doc", "dog",
            "don", "ear", "eat", "gab", "gas", "gel", "had", "had", "has", "how", "hug", "ins", "its", "kin", "lee",
            "leg", "mad", "map", "mug", "nap", "nay", "net", "nip", "not", "now", "opt", "pal", "pan", "pea", "pin",
            "pit", "pot", "rat", "rep", "saw", "sit", "sue", "tab", "tan", "tar", "tea", "ten", "tip",
            "tis", "ton", "top", "tub", "two", "ugh"

    };

    static boolean isValidUserInput( String userInput ) {

        if( Pattern.compile("[A-Za-z]").matcher(userInput).find() ) {

            return true;

        } else {

            return false;
        }
    }

    static boolean hasAnagrams( String word ) {

        for (int i = 0; i < knownToHaveAnagrams.length - 1; i++) {

            if ( Objects.equals(word, knownToHaveAnagrams[i] ) ) {

                return true;

            } else {

                return false;
            }

        }

        return false;

    }

}