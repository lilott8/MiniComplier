package datastructures.tokenizers;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

import datastructures.MCCharacter;
import datastructures.MCToken;

/**
 * @created: 9/12/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class SpaceTokenizer implements Tokenizer {

    /**
     * Mapping start character to token i.e. 0 -> Hello, 7 -> world! 13 -> goodbye
     */
    private Map<Integer, MCToken> tokens = new HashMap<>();
    private int numberOfLines;
    private int numberOfCharacters = 0;
    public final String DELIMITER = StringUtils.SPACE;

    public SpaceTokenizer(String input, int lineNumber) {
        this.numberOfLines = lineNumber;
        // The individual tokens.
        for (String s : StringUtils.split(input)) {
            // The characters in the string.
            this.tokens.put(this.numberOfCharacters, new MCToken(s, this.numberOfCharacters, lineNumber));
            this.numberOfCharacters += s.length();
        }
    }

    @Override
    public MCCharacter getAllTokens() {
        return null;
    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public Tokenizer tokenize() {
        return this;
    }

    @Override
    public int getNumberOfLines() {
        return this.numberOfLines;
    }

    @Override
    public int getNumberOfChars() {
        return this.numberOfCharacters;
    }

    @Override
    public Map<Integer, MCToken> getCharacterMap() {
        return this.tokens;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, MCToken> entry : this.tokens.entrySet()) {
            sb.append(entry.getValue().toString());
        }
        return sb.toString();
    }
}
