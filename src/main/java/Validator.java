import java.util.regex.Pattern;

public class Validator {

    static boolean isValidUserInput( String userInput ) {
        return Pattern.compile("^[A-Za-z]+$").matcher(userInput).find();
    }
}