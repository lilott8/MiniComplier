package complier.scanner.datastructures;

import org.apache.commons.lang3.StringUtils;

/**
 * @created: 9/11/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MCCharacter {

    private int line;
    private int at;
    private String character;

    public MCCharacter(int line, int at, String character) {
        this.line = line;
        this.at = at;
        this.character = character;
    }

    public int getLine() {
        return line;
    }

    public int getAt() {
        return at;
    }

    public String getCharacter() {
        return character;
    }

    public String toString() {
        String output = this.character;
        if (StringUtils.equals(character, StringUtils.CR)) {
            output = "(CR)";
        } else if (StringUtils.equals(character, StringUtils.SPACE)) {
            output = "(SPACE)";
        }
        return String.format("%s: (%d, %d)", output, line, at);
    }
}
