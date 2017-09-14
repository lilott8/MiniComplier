package complier.lexicon.subphases;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import datastructures.MCCharacter;
import datastructures.Token;
import io.MCReader;

/**
 * @created: 9/11/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MCScanner implements Scanner {

    public static final Logger logger = LogManager.getLogger(MCScanner.class);
    private Map<Integer, List<Token>> tokens = new LinkedHashMap<>();
    public static final String NEWLINE = "(nl)";
    private int numChars = 0;
    private int numTokens = 0;

    public MCScanner() {
    }

    /**
     * Scans the file, breaking it up into discrete tokens.
     *
     * @param input input file to be scanned.
     */
    @Override
    public void scanFile(String input) {
        MCReader reader = new MCReader(input);
        String line;
        int lineNumber = 0;
        this.tokens.put(lineNumber, new ArrayList<>());
        while ((line = reader.getNextLine()) != null) {
            Token token = new Token();
            for (int charAt = 0; charAt < line.length(); charAt++) {
                this.numChars++;
                String chars = Character.toString(line.charAt(charAt));
                if (!StringUtils.equalsAny(chars, StringUtils.CR, StringUtils.LF, StringUtils.SPACE)) {
                    token.addToToken(new MCCharacter(lineNumber, charAt, chars));
                } else {
                    this.tokens.get(lineNumber).add(token);
                    this.numTokens++;
                    token = new Token();
                }
            }
            this.tokens.get(lineNumber).add(token);
            this.numTokens++;
            lineNumber++;
            this.tokens.put(lineNumber, new ArrayList<>());
        }
        reader.closeFile();
    }

    /**
     * Returns the number of characters in the corpus
     *
     * @return int
     */
    @Override
    public int getNumberOfChars() {
        return this.numChars;
    }

    public int getNumberOfTokens() {
        return this.numTokens;
    }

    /**
     * Returns the number of lines in the corpus
     *
     * @return int
     */
    @Override
    public int getNumberOfLines() {
        return this.tokens.size();
    }

    /**
     * Returns the scanned characters in their data structure.
     *
     * @return LinkedHashSet data structure
     */
    @Override
    public Map<Integer, List<Token>> getCharacters() {
        return this.tokens;
    }

    public String toString() {
        return this.tokens.toString();
    }
}
