package ir.tree.statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import enums.IRCJumps;
import enums.IROpCodes;
import ir.memory.Memory;
import ir.simulate.frames.FrameSimulate;
import ir.simulate.word.Word;
import ir.tree.expression.Expression;

/**
 * @created: 12/7/17
 * @since: 0.1
 * @project: minicomplier
 */
public class ConditionalJump extends Statement {

    public static final Logger logger = LogManager.getLogger(ConditionalJump.class);

    private IRCJumps condition;
    private Expression left;
    private Expression right;

    // Where to condition on a true/false
    private Memory trueLabel;
    private Memory falseLabel;

    public ConditionalJump(IRCJumps op, Expression left, Expression right, Memory trueLabel, Memory falseLabel) {
        this.condition = op;
        this.left = left;
        this.right = right;
        this.trueLabel = trueLabel;
        this.falseLabel = falseLabel;
    }

    @Override
    public List<Expression> expressions() {
        return null;
    }

    @Override
    public Statement build(List<Expression> expressions) {
        return new ConditionalJump(this.condition, expressions.get(0), expressions.get(1), trueLabel, falseLabel);
    }

    /**
     * If this statement is a kind of JUMP, then this method should be
     * implemented and it should return a List of possible condition targets.
     * <p>
     * This method is used by the TraceScheduling algorithm. The order
     * in which the jumptargets are retur
     */
    @Override
    public List<Memory> getJumpTargets() {
        logger.error("getJumpTargets not implemented!");
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
        Word left = this.left.simulate(frame);
        Word right = this.right.simulate(frame);
        switch (this.condition) {
            case LESS_THAN_S:
            case LESS_THAN_U:
                return left.isLessThan(right) ? this.trueLabel : this.falseLabel;
            case LESS_THAN_EQUAL_S:
            case LESS_THAN_EQUAL_U:
                return null;
            case NOT_EQUAL:
                return null;
            case EQUAL:
                return null;
            case GREATER_THAN_S:
            case GREATER_THAN_U:
                return null;
            case GREATER_THAN_EQUAL_S:
            case GREATER_THAN_EQUAL_U:
                return null;
            default:
                logger.fatal("Missing case: " + this.condition);
                return null;
        }
    }

    public Statement changeFalseLabel(Memory falseLabel) {
        return new ConditionalJump(this.condition, this.left, this.right, this.trueLabel, falseLabel);
    }

    @Override
    public String getIRName() {
        return "Conditional Jump";
    }

    @Override
    public IROpCodes getIROpCode() {
        return IROpCodes.CONDITIONAL_JUMP;
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

    public IRCJumps getCondition() {
        return this.condition;
    }

    public Expression getLeft() {
        return this.left;
    }

    public Expression getRight() {
        return this.right;
    }

    public Memory getTrueLabel() {
        return this.trueLabel;
    }

    public Memory getFalseLabel() {
        return this.falseLabel;
    }
}
