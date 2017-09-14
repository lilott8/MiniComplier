package datastructures;

import java.util.ArrayList;
import java.util.List;

/**
 * @created: 9/14/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MCToken {

    private List<MCCharacter> characters = new ArrayList<>();
    int length = 0;
    int start = 0;

    public MCToken(String token, int start, int line) {
        this.start = start;
        this.length = token.length();
        for (int x = 0; x < this.length; x++) {
            characters.add(new MCCharacter(line, start + x, Character.toString(token.charAt(x))));
        }
    }

    public String toString() {
        return this.characters.toString();
    }


}
