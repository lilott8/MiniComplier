package ir.tree.statement;

import java.util.ArrayList;
import java.util.List;

import enums.IROpCodes;
import ir.memory.Memory;
import ir.simulate.frames.FrameSimulate;
import ir.tree.expression.Expression;
import ir.tree.expression.Fetch;


/**
 * @created: 12/7/17
 * @since: 0.1
 * @project: minicomplier
 */

/**
 * This uses a store if a memory is in the source.
 */
public class Move extends Statement {

    private Expression source;
    private Expression destination;

    public Move(Expression src, Expression dst) {
        this.source = src;
        this.destination = dst;
    }

    public Expression getSource() {
        return source;
    }

    public Expression getDestination() {
        return destination;
    }

    @Override
    public List<Expression> expressions() {
        List<Expression> list = new ArrayList<>();
        if (this.destination.getIROpCode() == IROpCodes.MEMORY) {
            list.add(this.destination.getExpression());
            list.add(this.source);
        } else {
            list.add(this.source);
        }
        return list;
    }

    @Override
    public Statement build(List<Expression> expressions) {
        if (this.destination.getIROpCode() == IROpCodes.MEMORY) {
            return new Move(new Fetch(expressions.get(0)), expressions.get(1));
        } else {
            return new Move(this.destination, expressions.get(0));
        }
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
        return null;
    }

    /**
     * Simulator the execution of an atomic IRStm.
     * The statement may return a Fetch
     * if its execution causes an explicit transfer of control, or it may return null
     * for an implicit transfer of control to the next statement.
     */
    @Override
    public Memory simulate(FrameSimulate frame) {
        this.destination.set(this.source.simulate(frame), frame);
        return null;
    }

    @Override
    public String getIRName() {
        return "Move";
    }

    @Override
    public IROpCodes getIROpCode() {
        return IROpCodes.MOVE;
    }
}
