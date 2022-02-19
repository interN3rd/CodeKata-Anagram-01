package com.payone.codekata;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface AnagramFinder {

    List<String> findAnagrams( final String userInput ) throws IOException;
}
