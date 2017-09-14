package complier.lexicon.subphases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

import datastructures.tokenizers.SpaceTokenizer;
import datastructures.tokenizers.Tokenizer;
import io.MCReader;

/**
 * @created: 9/11/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MCScanner implements Scanner {

    public static final Logger logger = LogManager.getLogger(MCScanner.class);
    private Map<Integer, Tokenizer> tokens = new LinkedHashMap<>();
    public static final String NEWLINE = "(nl)";
    private int numberOfChars = 0;

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
        while ((line = reader.getNextLine()) != null) {
            Tokenizer token = new SpaceTokenizer(line, lineNumber);
            numberOfChars += token.getNumberOfChars();
            this.tokens.put(lineNumber, token);
            lineNumber++;
        }
        reader.closeFile();
    }

    @Override
    public int getNumberOfChars() {
        return this.numberOfChars;
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
    public Map<Integer, Tokenizer> getTokens() {
        return this.tokens;
    }

    public String toString() {
        return this.tokens.toString();
    }
}
