package ir.tree.statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import enums.IROpCodes;
import ir.memory.Memory;
import ir.simulate.frames.FrameSimulate;
import ir.tree.IR;
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

    public static Statement buildSequence(Statement... statements) {
        Statement temp = IR.NOP;
        // Bottom up iteration.
        for (int x = statements.length - 1; x >= 0; x--) {
            if (temp == NOP) {
                temp = statements[x];
            } else if (statements[x] == NOP) {
                continue;
            } else {
                temp = new Sequence(statements[x], temp);
            }
        }
        return temp;
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
