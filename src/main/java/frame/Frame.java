package frame;

import java.util.List;

import ir.interpreter.Interpreter;
import ir.temp.Label;

/**
 * @created: 12/1/17
 * @since: 0.1
 * @project: MiniComplier
 */
public abstract class Frame {

    private Label label;
    private List<Access> formals;

    private Interpreter interpreter;

    public abstract Frame newFrame(Label name, List<Boolean> formalsEscape);

    protected Frame(Label label, List<Access> formals) {
        this.label = label;
        this.formals = formals;
    }

    public Interpreter getInterpreter() {
        return this.interpreter;
    }
}
