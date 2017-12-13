package ir.memory;

/**
 * @created: 12/7/17
 * @since: 0.1
 * @project: minicomplier
 */
public class Register {

    private static int count = 0;

    private String name;

    public Register() {
        this.name = "T_" + count;
        count++;
    }

    public Register(String name) {
        this.name = name;
    }
}
