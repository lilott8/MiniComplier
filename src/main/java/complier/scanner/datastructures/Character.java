package complier.scanner.datastructures;

/**
 * @created: 9/11/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class Character {

    private int line;
    private int at;
    private String character;

    public Character(int line, int at, String character) {
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
}
