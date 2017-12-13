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
public class Sequence extends Statement {

    public static final Logger logger = LogManager.getLogger(Sequence.class);

    public final Statement left;
    public final Statement right;

    public Sequence(Statement left, Statement right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public List<Expression> expressions() {
        logger.warn("expressions is not implemented");
        return new ArrayList<>();
    }

    @Override
    public Statement build(List<Expression> expressions) {
        logger.warn("build is not implemented");
        return null;
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
        logger.warn("getJumpTargets is not implemented");
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
        logger.warn("expressions is not implemented");
        return null;
    }

    @Override
    public String getIRName() {
        return "Seqeuence";
    }

    @Override
    public IROpCodes getIROpCode() {
        return IROpCodes.SEQUENCE;
    }
}
