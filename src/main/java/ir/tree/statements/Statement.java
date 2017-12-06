package ir.tree.statements;


import java.util.List;

import frame.Frame;
import ir.temp.Label;
import ir.tree.Node;
import ir.tree.expressions.Expression;

/**
 * @created: 12/5/17
 * @since: 0.1
 * @project: MiniComplier
 */
public abstract class Statement implements Node {
    //The book implements these methods on most statements
    abstract public List<Expression> kids();

    abstract public Statement build(List<Expression> kids);

    /**
     * Simulate the execution of an atomic IRStm.
     * The statement may return a Label
     * if its execution causes an explicit transfer of control, or it may return null
     * for an implicit transfer of control to the next statement.
     */
    public abstract Label interp(Frame frame);

    /**
     * Is this particular statement a kind of jump (to be more precise,
     * is it a statement that transfers control explicitly rather than
     * "fall" into the next statement implicitly.
     */
    public boolean isJump() {
        return false;
        // Most statements aren't jumps. We must not forget to override
        // this method for those that are!
    }

    /**
     * If this statement is a kind of JUMP, then this method should be
     * implemented and it should return a List of possible jump targets.
     * <p>
     * This method is used by the TraceScheduling algorithm. The order
     * in which the jumptargets are retur
     */
    public List<Label> getJumpTargets() {
        throw new Error("Not implemented (not a JUMP statement)");
    }
}
