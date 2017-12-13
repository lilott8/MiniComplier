package ir.simulate.frames;

/**
 * @created: 12/8/17
 * @since: 0.1
 * @project: minicomplier
 */

import java.util.List;

import ir.frame.Frame;
import ir.memory.Memory;
import ir.memory.Register;
import ir.simulate.Callable;
import ir.simulate.Simulator;
import ir.simulate.word.EmptyWord;
import ir.simulate.word.JumpPointer;
import ir.simulate.word.Pointer;
import ir.simulate.word.Word;
import ir.tree.expression.Expression;


/**
 * Represents a simulated stack frame on the X86 architecture.
 * <p>
 * This serves as a kind of "environment" for the interpretation
 * if IR code in the IR interpreter.
 * <p>
 * This class contains much code that is probably reusable for
 * different architectures. It may be worthwhile to refactor it
 * so the interpreter code use an abstract class SimFrame and the
 * X86Frame (or Frame implementations for other architectures) returns
 * a concrete instance of X86SimFrame which inherits much of the
 * reusable code from the abstract superclass.
 * <p>
 * We haven't done this yet because the cs411 project only calls
 * for an X86 implementation.
 */
public class X86Simulate extends FrameSimulate {

    public X86Simulate(Simulator simulate, Frame frame, List<Word> args) {
        super(simulate, frame, args);

        Pointer currentFormal = this.getFrameBasePointer().add(this.frame.getFirstFormalIncrement());
        for (Word word : args) {
            currentFormal.set(word);
            currentFormal = currentFormal.add(this.frame.getFormalIncrement());
        }
        this.getFrameBasePointer().set(this.frame.framePointer().simulate(this));
        this.getFrameBasePointer().add(this.frame.wordSize()).set(new EmptyWord(this.frame.wordSize()));
    }

    public Word getRegister(Register name) {
        Word result = registers.get(name);
        return result == null ? new EmptyWord("?" + name, this.frame.wordSize()) : result;
    }

    public void setRegister(Register name, Word value) {
        registers.put(name, value);
    }

    public Expression getFramePointer() {
        return this.frame.framePointer();
    }

    public Word getReturnValue() {
        return this.frame.returnValue().simulate(this);
    }

    public Simulator getSimulate() {
        return this.simulate;
    }

    public Pointer getFrameBasePointer() {
        return frameBytes.add(this.frame.getLocalCount() * this.frame.wordSize());
    }

    public Word getLabel(Memory label) {
        Callable procedure = this.simulate.getProcLabel(label);
        return procedure == null ? new JumpPointer(label, this.frame.wordSize()) : procedure;
    }
}
