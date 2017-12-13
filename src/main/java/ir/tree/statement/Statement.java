package ir.tree.statement;

import java.util.List;

import ir.memory.Memory;
import ir.simulate.frames.FrameSimulate;
import ir.tree.IR;
import ir.tree.IRNode;
import ir.tree.expression.Expression;

/**
 * @created: 12/7/17
 * @since: 0.1
 * @project: minicomplier
 */
public abstract class Statement extends IR implements IRNode {

    public abstract List<Expression> expressions();

    public abstract Statement build(List<Expression> expressions);

    /**
     * If this statement is a kind of JUMP, then this method should be
     * implemented and it should return a List of possible jump targets.
     * <p>
     * This method is used by the TraceScheduling algorithm. The order
     * in which the jumptargets are retur
     */
    public abstract List<Memory> getJumpTargets();

    /**
     * Simulator the execution of an atomic IRStm.
     * The statement may return a Fetch
     * if its execution causes an explicit transfer of control, or it may return null
     * for an implicit transfer of control to the next statement.
     */
    public abstract Memory simulate(FrameSimulate frame);

    /**
     * Is this particular statement a kind of jump (to be more precise,
     * is it a statement that transfers control explicitly rather than
     * "fall" into the next statement implicitly.
     */
    public boolean isJump() {
        return false;
    }

    public boolean isLabel() {
        return false;
    }

    public Memory getLabel() {
        return null;
    }
}
