package ir.frame;

import java.util.List;

import ir.memory.Memory;
import ir.simulate.Simulator;
import ir.simulate.frames.FrameSimulate;
import ir.simulate.word.Word;
import ir.tree.expression.Expression;
import ir.tree.statement.Statement;

/**
 * @created: 12/7/17
 * @since: 0.1
 * @project: minicomplier
 */
public abstract class Frame {

    private Memory label;
    private List<Access> formals;

    protected Frame(Memory label, List<Access> formals) {
        this.label = label;
        this.formals = formals;
    }

    public final Memory getLabel() {
        return this.label;
    }

    public final List<Access> getFormals() {
        return this.formals;
    }

    public Access getFormal(int i) {
        return this.formals.get(i);
    }

    /**
     * Allocate space for a local variable in this frame.
     */
    public abstract Access allocateLocal(boolean escapes);

    /**
     * Helper function to create an access instance
     */
    public abstract Access allocate(int offset);

    public abstract int getLocalCount();

    public abstract int getFirstFormalIncrement();

    public abstract int getFormalIncrement();

    public abstract Expression framePointer();

    public abstract Expression returnValue();

    public abstract int wordSize();

    public abstract Statement procedureEntry(Statement body);

    public abstract FrameSimulate newFrameSimulation(Simulator simulate, List<Word> args);

}
