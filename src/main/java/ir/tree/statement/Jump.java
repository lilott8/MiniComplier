package ir.tree.statement;

import java.util.ArrayList;
import java.util.List;

import enums.IROpCodes;
import ir.memory.Memory;
import ir.simulate.frames.FrameSimulate;
import ir.simulate.word.JumpPointer;
import ir.tree.expression.Expression;
import ir.tree.expression.Name;

/**
 * @created: 12/7/17
 * @since: 0.1
 * @project: minicomplier
 */
public class Jump extends Statement {

    private Expression expression;
    private List<Memory> targets;

    public Jump(Expression e, List<Memory> labels) {
        this.expression = e;
        this.targets = labels;
    }

    public Jump(Memory target) {
        List<Memory> list = new ArrayList<>();
        list.add(target);
        new Jump(new Name(target), list);
    }

    @Override
    public List<Expression> expressions() {
        List<Expression> list = new ArrayList<>();
        list.add(this.expression);
        return list;
    }

    @Override
    public Statement build(List<Expression> expressions) {
        return new Jump(expressions.get(0), this.targets);
    }

    /**
     * If this statement is a kind of JUMP, then this method should be
     * implemented and it should return a List of possible jump targets.
     * <p>
     * This method is used by the TraceScheduling algorithm. The order
     * in which the jumptargets are retur
     */
    @Override
    public List<Memory> getJumpTargets() {
        return targets;
    }

    /**
     * Is this particular statement a kind of jump (to be more precise,
     * is it a statement that transfers control explicitly rather than
     * "fall" into the next statement implicitly.
     */
    @Override
    public boolean isJump() {
        return true;
    }

    /**
     * Simulator the execution of an atomic IRStm.
     * The statement may return a Fetch
     * if its execution causes an explicit transfer of control, or it may return null
     * for an implicit transfer of control to the next statement.
     */
    @Override
    public Memory simulate(FrameSimulate frame) {
        JumpPointer label = (JumpPointer) this.expression.simulate(frame);
        return label.getAddress();
    }

    @Override
    public String getIRName() {
        return "Jump";
    }

    @Override
    public IROpCodes getIROpCode() {
        return IROpCodes.JUMP;
    }

    @Override
    public Memory getLabel() {
        return null;
    }
}
