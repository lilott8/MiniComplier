package ir.frame.x86;

import java.util.List;

import ir.frame.Access;
import ir.frame.Frame;
import ir.memory.Memory;
import ir.memory.Register;
import ir.simulate.Simulator;
import ir.simulate.frames.FrameSimulate;
import ir.simulate.word.Word;
import ir.tree.expression.Expression;
import ir.tree.expression.Temp;
import ir.tree.statement.Label;
import ir.tree.statement.Sequence;
import ir.tree.statement.Statement;

/**
 * @created: 12/8/17
 * @since: 0.1
 * @project: minicomplier
 */
public class X86Frame extends Frame {

    public static final int FIRST_FORMAL_OFFSET = 8;
    public static final int FORMAL_INCREMENT = 4;
    public static final int WORD_SIZE = FORMAL_INCREMENT;

    private Register framePointer = new Register("ebp");
    private Register returnValue = new Register("eax");

    private int localCount = 0;

    public X86Frame(Memory label, List<Access> formals) {
        super(label, formals);
    }

    public int getLocalCount() {
        return this.localCount;
    }

    /**
     * Allocate space for a local variable in this frame.
     */
    @Override
    public Access allocateLocal(boolean escapes) {
        return escapes ? new X86InFrame(-(this.wordSize() * this.localCount++)) : new X86InRegister(new Register());
    }

    /**
     * Helper function to create an access instance
     */
    @Override
    public Access allocate(int offset) {
        return new X86InFrame(offset);
    }

    @Override
    public int getFirstFormalIncrement() {
        return FIRST_FORMAL_OFFSET;
    }

    @Override
    public int getFormalIncrement() {
        return FORMAL_INCREMENT;
    }

    @Override
    public Expression framePointer() {
        return new Temp(this.framePointer);
    }

    @Override
    public Expression returnValue() {
        return new Temp(this.returnValue);
    }

    @Override
    public int wordSize() {
        return FORMAL_INCREMENT;
    }

    @Override
    public Statement procedureEntry(Statement body) {
        return new Sequence(new Label(this.getLabel()), body);
    }

    @Override
    public FrameSimulate newFrameSimulation(Simulator simulate, List<Word> args) {
        return null;
    }
}
