package ir.memory;

/**
 * @created: 12/7/17
 * @since: 0.1
 * @project: minicomplier
 */

import java.util.HashMap;
import java.util.Map;

/**
 * This represents an address in assembly language.
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
public class Memory {

    /**
     * A map for use in the fromString method (to return the same memory object if an
     * address with this name already exists).
     */
    private static Map<String, Memory> labels = new HashMap<String, Memory>();
    private static int count = 0;
    private String name;

    public Memory(String name) {
        this.name = name;
    }

    public Memory() {
        this.name = "L" + count;
        count += 1;
    }
}
