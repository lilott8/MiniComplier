package datastructures.tokenizers;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datastructures.MCCharacter;

/**
 * @created: 9/12/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class SpaceTokenizer implements Tokenizer {

    /**
     * Mapping start character to token i.e. 0 -> Hello, 7 -> world! 13 -> goodbye
     */
    private Map<Integer, List<MCCharacter>> tokens = new HashMap<>();
    private int numberOfLines;
    private int numberOfCharacters = 0;
    public final String DELIMITER = StringUtils.SPACE;

    public SpaceTokenizer(String input, int LineNumber) {
        this.numberOfLines = numberOfLines;
        int startingChar = 0;
        // The individual tokens.
        for (String s : StringUtils.split(input)) {
            this.tokens.put(startingChar, new ArrayList<>());
            // The characters in the string.
            for (int x = 0; x < s.length(); x++) {
                this.tokens.get(startingChar).add(
                        new MCCharacter(this.numberOfLines, this.numberOfCharacters, Character.toString(s.charAt(x))));
                this.numberOfCharacters++;
            }
            startingChar = this.numberOfCharacters;
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
    public Map<Integer, List<MCCharacter>> getCharacterMap() {
        return this.tokens;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, List<MCCharacter>> entry : this.tokens.entrySet()) {
            for (MCCharacter chars : entry.getValue()) {
                sb.append(chars.toString());
            }
        }
        return sb.toString();
    }
}
