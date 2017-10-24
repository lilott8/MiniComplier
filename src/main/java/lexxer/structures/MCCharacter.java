package lexxer.structures;

import org.apache.commons.lang3.StringUtils;

/**
 * @created: 10/23/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MCCharacter {

    private int lineNumber;
    private int columnNumber;
    private String character;

    public static final String NEW_LINE = "NL";
    public static final String SPACE = "SP";

    public MCCharacter(int line, int column, String character) {
        this.lineNumber = line;
        this.columnNumber = column;
        this.character = checkForWhiteSpace(character);
    }

    public MCCharacter(int line, int column, char character) {
        this.lineNumber = line;
        this.columnNumber = column;
        this.character = checkForWhiteSpace(Character.toString(character));
    }

    private String checkForWhiteSpace(String space) {
        if (StringUtils.equals(space, StringUtils.LF) || StringUtils.equals(space, StringUtils.CR)) {
            return NEW_LINE;
        }
        if (StringUtils.equals(space, StringUtils.SPACE)) {
            return SPACE;
        }
        return space;
    }

    public String toString() {
        return String.format("%s <%d, %d>", this.character, this.lineNumber, this.columnNumber);
    }
}
