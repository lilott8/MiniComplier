package ir.tree.statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

/**
 * Maps to the EXP instruction.
 */
public class Evaluate extends Statement {

    public static final Logger logger = LogManager.getLogger(Evaluate.class);

    public final Expression expression;

    public Evaluate(Expression expression) {
        this.expression = expression;
    }

    public List<Expression> expressions() {
        List<Expression> list = new ArrayList<>();
        list.add(this.expression);
        return list;
    }

    @Override
    public Statement build(List<Expression> expressions) {
        return new Evaluate(expressions.get(0));
    }

    /**
     * If this statement is a kind of JUMP, then this method should be
     * implemented and it should return a List of possible jump targets.
     * <p>
     * This method is used by the TraceScheduling algorithm. The order
     * in which the jumptargets are retur
     */
    public List<Memory> getJumpTargets() {
        logger.warn("expressions is not implemented");
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
        this.expression.simulate(frame);
        return null;
    }

    @Override
    public String getIRName() {
        return "Evaluate";
    }

    @Override
    public IROpCodes getIROpCode() {
        return IROpCodes.EVALUATE;
    }
}
