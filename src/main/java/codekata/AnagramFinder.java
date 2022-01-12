package codekata;

import java.io.IOException;
import java.util.List;

public interface AnagramFinder {

    List<String> findAnagrams(final String userInput) throws IOException;
}
