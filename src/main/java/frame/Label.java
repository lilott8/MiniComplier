package frame;

import symboltable.Symbol;

/**
 * @created: 12/1/17
 * @since: 0.1
 * @project: MiniComplier
 *
 * This denotes a static address in memory.
 */
public class Label<Type> {

    private static int labelCounter = 0;
    public static final String LABEL_NAME = "L";

    private String labelName;

    public Label() {
        this.labelName = LABEL_NAME + "_" + labelCounter;
        labelCounter += 1;
    }

    public Label(String name) {
        this.labelName = name;
    }

    public Label(Symbol symbol) {
        this.labelName = symbol.getName();
    }
}
