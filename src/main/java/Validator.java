import java.util.Objects;
import java.util.regex.Pattern;

public class Validator {

    // taken from: https://www.manythings.org/anagrams/anagrams4.html
    static final String[] knownToHaveAnagrams = {

            "ah", "am", "eh", "ho", "no", "act", "add", "aft", "amp", "ant", "ape", "apt", "arc", "are", "arm", "art",
            "ate", "bad", "bag", "bar", "bat", "but", "cab", "car", "cat", "dab", "dad", "dam", "dew", "doc", "dog",
            "don", "ear", "eat", "gab", "gas", "gel", "had", "had", "has", "how", "hug", "ins", "its", "kin", "lee",
            "leg", "mad", "map", "mug", "nap", "nay", "net", "nip", "not", "now", "opt", "pal", "pan", "pea", "pin",
            "pit", "pot", "rat", "rep", "saw", "sit", "sue", "tab", "tan", "tar", "tea", "ten", "tip", "tis", "ton",
            "top", "tub", "two", "ugh", "urn", "use", "war", "was", "way", "wed", "who", "won", "yah", "yap", "yaw",
            "yea", "aide", "amps", "bake", "bear", "best", "bets", "bets", "blot", "blow", "boss", "bowl", "brag",
            "bury", "cafe", "calm", "cans", "care", "carp", "cars", "case", "cask", "cast", "chin", "chit", "chum",
            "clam", "clay", "cloud", "cola", "cone", "cool", "cork", "crap", "dads", "deaf", "dear", "dial", "dice",
            "diet", "disk", "doom", "east", "eats", "edit", "egos", "evil", "face", "fade", "fast", "fats", "fear",
            "feat", "feel", "felt", "fist", "flee", "flog", "form", "fowl", "from", "fuel", "gabs", "gasp", "gels",
            "gods", "grab", "gulp", "hate"

    };

    static boolean isValidUserInput( String userInput ) {

        return Pattern.compile("(?<=\\\\s|^)[a-zA-Z]*(?=[.,;:]?\\\\s|$)").matcher(userInput).find();

    }

    static boolean hasAnagrams( String word ) {

        boolean hasAnagrams = false;

        for (int i = 0; i < knownToHaveAnagrams.length - 1; i++) {

            if (Objects.equals(word, knownToHaveAnagrams[i])) {

                hasAnagrams = true;
                break;

            }
        }

        return hasAnagrams;

    }

}