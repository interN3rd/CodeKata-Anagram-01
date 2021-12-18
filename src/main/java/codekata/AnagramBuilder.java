package codekata;

import java.io.IOException;
import java.util.List;

public interface AnagramBuilder {

    List<String> findAnagrams(final String userInput) throws IOException;
}
