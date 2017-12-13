package ir.tree.statement;

import java.util.ArrayList;
import java.util.List;

import enums.IROpCodes;
import ir.memory.Memory;
import ir.simulate.frames.FrameSimulate;
import ir.tree.expression.Expression;

/**
 * @created: 12/7/17
 * @since: 0.1
 * @project: minicomplier
 */
public class Label extends Statement {

    private Memory label;

    public Label(Memory label) {
        this.label = label;
    }

    @Override
    public List<Expression> expressions() {
        return new ArrayList<>();
    }

    @Override
    public Statement build(List<Expression> expressions) {
        return this;
    }

    /**
     * If this statement is a kind of JUMP, then this method should be
     * implemented and it should return a List of possible jump targets.
     * <p>
     * This method is used by the TraceScheduling algorithm. The order
     * in which the jumptargets are return
     */
    @Override
    public List<Memory> getJumpTargets() {
        return new ArrayList<>();
    }

    /**
     * Simulator the execution of an atomic IRStm.
     * The statement may return a Fetch
     * if its execution causes an explicit transfer of control, or it may return null
     * for an implicit transfer of control to the next statement.
     */
    @Override
    public Memory simulate(FrameSimulate frame) {
        // Labels don't do anything.
        return null;
    }

    public Memory getAddress() {
        return this.label;
    }

    @Override
    public String getIRName() {
        return "Label";
    }

    @Override
    public IROpCodes getIROpCode() {
        return IROpCodes.LABEL;
    }

    @Override
    public boolean isLabel() {
        return true;
    }

    @Override
    public Memory getLabel() {
        return this.getAddress();
    }
}
