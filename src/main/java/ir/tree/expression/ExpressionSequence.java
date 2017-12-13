package ir.tree.expression;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import enums.IROpCodes;
import ir.simulate.frames.FrameSimulate;
import ir.simulate.word.Word;
import ir.tree.statement.Statement;

/**
 * @created: 12/7/17
 * @since: 0.1
 * @project: minicomplier
 */
public class ExpressionSequence extends Expression {

    public static final Logger logger = LogManager.getLogger(ExpressionSequence.class);

    public final Statement statement;
    public final Expression expression;

    public ExpressionSequence(Statement statement, Expression expression) {
        this.statement = statement;
        this.expression = expression;
    }

    /**
     * Retrieve a list of the direct subexpression of this node.
     */
    @Override
    public List<Expression> getSubExpressions() {
        logger.warn("getSubExpression is not implemented.");
        return new ArrayList<>();
    }

    /**
     * Create a new Exp node by copying this node and replacing it's
     * direct subexpressions.
     * <p>
     * It is assumed that the number and ordering of "getSubexpressions" is the same as the
     * that returned by the getSubexpressions() method. Any non-expression items are kept as
     * is.
     */
    @Override
    public Expression build(List<Expression> subexpressions) {
        logger.warn("build does not apply to Expression Sequences.");
        return null;
    }

    /**
     * To simulate IR execution. This method assumes that the IR is
     * in almost canonical form. In particular, it is assumed that the
     * their are no ISeq expressions in the IRcode (the main reason for
     * this assumption is that it is next to impossible to simulate JUMP's
     * into and out of expressions.
     */
    @Override
    public Word simulate(FrameSimulate env) {
        logger.error("Expression Sequences are not atomic.  " +
                "We can only simulate atomic statements " +
                "(linearlized IR shouldn't have these statements.");
        return null;
    }

    @Override
    public String getIRName() {
        return "Expression Sequence";
    }

    @Override
    public Expression getExpression() {
        return expression;
    }

    @Override
    public IROpCodes getIROpCode() {
        return IROpCodes.EXPRESSION_SEQUENCE;
    }
}
