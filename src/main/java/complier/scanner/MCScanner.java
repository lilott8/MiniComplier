package complier.scanner;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashSet;

import complier.scanner.datastructures.MCCharacter;
import io.MCReader;

/**
 * @created: 9/11/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MCScanner implements Scanner {

    public static final Logger logger = LogManager.getLogger(MCScanner.class);
    private LinkedHashSet<MCCharacter> scanningTable = new LinkedHashSet<>();
    private int numberOfLines = 1;
    public static final String NEWLINE = "(nl)";

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
        int character = 0;
        int charAt = 1;
        while ((character = reader.read()) >= 0) {
            String chars = Character.toString((char) character);
            /*
             * Handles our three cases:
             * 1) We have a line break,
             * 2) We have a space,
             * 3) We have a non-whitespace character.
             */
            if (StringUtils.equals(chars, StringUtils.CR) || StringUtils.equals(chars, StringUtils.LF)) {
                this.scanningTable.add(new MCCharacter(this.numberOfLines, charAt, NEWLINE));
                // Reset our character
                charAt = 1;
                this.numberOfLines++;
            } else if (StringUtils.equals(chars, StringUtils.SPACE)) {
                this.scanningTable.add(new MCCharacter(this.numberOfLines, charAt, StringUtils.SPACE));
                charAt++;
            } else {
                this.scanningTable.add(new MCCharacter(this.numberOfLines, charAt, chars));
                charAt++;
            }
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
        return this.scanningTable.size();
    }

    /**
     * Returns the number of lines in the corpus
     *
     * @return int
     */
    @Override
    public int getNumberOfLines() {
        return this.numberOfLines;
    }

    /**
     * Returns the scanned characters in their data structure.
     *
     * @return LinkedHashSet data structure
     */
    @Override
    public LinkedHashSet<MCCharacter> getCharacters() {
        return this.scanningTable;
    }

    public String toString() {
        return this.scanningTable.toString();
    }
}
