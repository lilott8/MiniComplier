package datastructures;

import java.util.ArrayList;
import java.util.List;

/**
 * @created: 9/12/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class Token {

    private List<MCCharacter> token = new ArrayList<>();
    private int start = -1;

    public Token() {
    }

    public void addToToken(MCCharacter token) {
        if (this.start == -1) {
            this.start = token.getAt();
        }
        this.token.add(token);
    }

    public String getToken() {
        StringBuilder sb = new StringBuilder();
        for (MCCharacter c : this.token) {
            sb.append(c.getCharacter());
        }
        return sb.toString();
    }

    public MCCharacter getCharacterAt(int at) {
        if (at <= this.token.size()) {
            return this.token.get(at);
        } else {
            return null;
        }
    }

    public int getTokenSize() {
        return this.token.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (MCCharacter c : this.token) {
            sb.append(c.getCharacter());
        }
        return sb.append(": ").append(this.start).toString();
    }
}
