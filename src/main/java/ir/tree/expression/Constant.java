package ir.tree.expression;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import enums.IROpCodes;
import ir.simulate.frames.FrameSimulate;
import ir.simulate.word.IntWord;
import ir.simulate.word.Word;

/**
 * @created: 12/7/17
 * @since: 0.1
 * @project: minicomplier
 */
public class Constant extends Expression {

    public static final Logger logger = LogManager.getLogger(Constant.class);

    private int value;

    public Constant(int value) {
        this.value = value;
    }

    /**
     * Retrieve a list of the direct subexpression of this node.
     */
    @Override
    public List<Expression> getSubExpressions() {
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
        return this;
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
        return new IntWord(value, env.getWordSize());
    }

    @Override
    public Expression getExpression() {
        logger.warn("getExpression is not implemented");
        return null;
    }

    @Override
    public String getIRName() {
        return "Constant";
    }

    @Override
    public IROpCodes getIROpCode() {
        return IROpCodes.CONSTANT;
    }

    @Override
    public boolean isConstant(int i) {
        return this.value == i;
    }
}
