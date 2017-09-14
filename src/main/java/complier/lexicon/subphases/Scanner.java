package complier.lexicon.subphases;

import java.util.List;
import java.util.Map;

import datastructures.Token;

/**
 * @created: 9/11/17
 * @since: 0.1
 * @project: MiniComplier
 */
public interface Scanner {
    void scanFile(String input);

    int getNumberOfChars();

    int getNumberOfLines();

    int getNumberOfTokens();

    Map<Integer, List<Token>> getCharacters();
}
