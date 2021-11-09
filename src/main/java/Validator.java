import java.util.List;
import java.util.regex.Pattern;

public class Validator {

    static boolean isValidUserInput( String userInput ) {
        return Pattern.compile("^[A-Za-z]+$").matcher(userInput).find();
    }

    static boolean isNullOrEmpty( final List<String> list ) {
        return list == null || list.isEmpty();
    }
}