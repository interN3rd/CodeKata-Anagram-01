import org.junit.jupiter.api.*;

import java.util.regex.Pattern;

@DisplayName( "AnagramTest" )
public class ApplicationTest {

    @BeforeAll
    static void beforeAll() {

        System.out.println( "[ INFO ] Testing is about to start.");

    }

    @BeforeEach
    void beforeEach() {

        System.out.println( "[ INFO ] tests starting.");

    }

    @Test
    @DisplayName( "test input validation: expected behavior" )
    void testInputValidationGoodCase() {

        String userInput = "Listen";
        Assertions.assertTrue( Pattern.compile("^[A-Za-z]+$").matcher(userInput).find() );

    }

    @Test
    @DisplayName( "test input validation: bad user input" )
    void testInputValidationBadInput() {

        String badUserInput = ".jsp\\0.html";
        Assertions.assertFalse( Pattern.compile("^[A-Za-z]+$").matcher(badUserInput).find() );

    }

    @AfterEach
    public void afterEach() {

        System.out.println( "[ INFO ] test completed." );

    }

    @AfterAll
    public static void afterAll() {

        System.out.println( "[ INFO ] Testing completed.");

    }

}