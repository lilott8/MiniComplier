package complier.scanner;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashSet;

import complier.scanner.datastructures.Character;
import config.CommonConfig;
import config.ConfigFactory;
import io.MCReader;

/**
 * @created: 9/11/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MCScanner implements Scanner {

    public static final Logger logger = LogManager.getLogger(MCScanner.class);
    private LinkedHashSet<Character> scanningTable = new LinkedHashSet<>();
    private int numberOfLines = 0;

    public MCScanner() {
    }

    @Override
    public void scanFile(String input) {
        MCReader reader = new MCReader(input);
        String line;
        int lineNumber = 0;
        while (!StringUtils.isEmpty(line = reader.getNextLine())) {
            for (int x = 0; x < line.length() - 1; x++) {
                if (java.lang.Character.isWhitespace(line.charAt(x))) {
                    this.scanningTable.add(new Character(lineNumber, x, "(space)"));
                } else {
                    this.scanningTable.add(new Character(lineNumber, x, String.valueOf(line.charAt(x))));
                }
            }
            lineNumber++;
            this.numberOfLines = lineNumber;
        }
        reader.closeFile();
    }

    @Override
    public int getNumberOfChars() {
        return this.scanningTable.size();
    }

    @Override
    public int getNumberOfLines() {
        return this.numberOfLines;
    }

    @Override
    public LinkedHashSet<Character> getCharacters() {
        return this.scanningTable;
    }

    public String toString() {
        return this.scanningTable.toString();
    }
}
