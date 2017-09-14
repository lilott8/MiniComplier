package complier.lexicon.subphases;

import java.util.Map;

import datastructures.tokenizers.Tokenizer;

/**
 * @created: 9/11/17
 * @since: 0.1
 * @project: MiniComplier
 */
public interface Scanner {
    void scanFile(String input);

    int getNumberOfChars();

    int getNumberOfLines();

    Map<Integer, Tokenizer> getTokens();
}
