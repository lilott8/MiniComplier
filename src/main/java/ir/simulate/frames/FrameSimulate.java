package ir.simulate.frames;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ir.frame.Frame;
import ir.memory.Memory;
import ir.memory.Register;
import ir.simulate.Simulator;
import ir.simulate.word.ArrayWord;
import ir.simulate.word.Pointer;
import ir.simulate.word.Word;
import ir.tree.expression.Expression;

/**
 * @created: 12/8/17
 * @since: 0.1
 * @project: minicomplier
 */
public abstract class FrameSimulate {

    protected final Frame frame;
    protected Map<Register, Word> registers = new HashMap<>();
    protected ArrayWord frameBytes;

    protected Simulator simulate;

    protected FrameSimulate(Simulator simulate, Frame frame, List<Word> args) {
        this.simulate = simulate;
        this.frame = frame;
        this.frameBytes = new ArrayWord(args.size() + 2 + frame.getLocalCount(), this.frame.wordSize());
    }

    public abstract Word getRegister(Register name);

    public abstract void setRegister(Register name, Word value);

    public abstract Expression getFramePointer();

    public abstract Word getReturnValue();

    public abstract Simulator getSimulate();

    public abstract Pointer getFrameBasePointer();

    public abstract Word getLabel(Memory label);

    public int getWordSize() {
        return this.frame.wordSize();
    }

}
