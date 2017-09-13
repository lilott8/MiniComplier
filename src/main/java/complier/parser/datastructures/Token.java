package complier.parser.datastructures;

import java.util.ArrayList;
import java.util.List;

import complier.scanner.datastructures.Character;

/**
 * @created: 9/12/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class Token {

    private List<Character> token = new ArrayList<>();
    private int start = -1;

    public Token() {
    }

    public void addToToken(Character token) {
        if (this.start == -1) {
            this.start = token.getAt();
        }
        this.token.add(token);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Character c : this.token) {
            sb.append(c.getCharacter());
        }
        return sb.append(": ").append(this.start).toString();
    }
}
