package ir.temp;

/**
 * @created: 12/5/17
 * @since: 0.1
 * @project: MiniComplier
 */

/**
 * A Label represents an address in assembly language.
 * <p>
 * This implementation of labels is a little more sophisticated than the
 * one in the book.
 * <p>
 * The constructors have been made private. You should call the static
 * methods of this class to create labels. (This makes it easier to evolve
 * the Label class without having to change all the code that uses it.
 * Also, it is important to distinguish the "get" from the "generate" method,
 * which would be hard with constructors since they have the same parameter name.
 */
public class Label {

    private static int count = 0;
    private String name;
    private int id;

    public Label() {
        this.name = "L";
        this.id = count;
        count++;
    }

    public Label(String name) {
        this.name = name;
        this.id = count;
        count++;
    }

    public String toString() {
        return this.name + "_" + this.id;
    }


}
