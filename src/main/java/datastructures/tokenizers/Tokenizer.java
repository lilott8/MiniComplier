package datastructures.tokenizers;

import java.util.List;
import java.util.Map;

import datastructures.MCCharacter;

/**
 * @created: 9/14/17
 * @since: 0.1
 * @project: MiniComplier
 */
public interface Tokenizer {

    MCCharacter getAllTokens();

    String getToken();

    //void tokenize(String input);
    Tokenizer tokenize();

    int getNumberOfLines();

    int getNumberOfChars();

    Map<Integer, List<MCCharacter>> getCharacterMap();
}
