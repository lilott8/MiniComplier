package ir.tree.expression;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import enums.IRBinOps;
import enums.IROpCodes;
import ir.simulate.frames.FrameSimulate;
import ir.simulate.word.Word;

/**
 * @created: 12/7/17
 * @since: 0.1
 * @project: minicomplier
 */
public class BinaryOp extends Expression {

    public static final Logger logger = LogManager.getLogger(BinaryOp.class);

    private IRBinOps binOp;
    private Expression left;
    private Expression right;

    public BinaryOp(IRBinOps op, Expression left, Expression right) {
        this.binOp = op;
        this.left = left;
        this.right = right;
    }

    public Expression build(List<Expression> subexpressions) {
        return new BinaryOp(this.binOp, subexpressions.get(0), subexpressions.get(1));
    }

    /**
     * Retrieve a list of the direct subexpression of this node.
     */
    @Override
    public List<Expression> getSubExpressions() {
        List<Expression> list = new ArrayList<>();
        list.add(this.left);
        list.add(this.right);
        return list;
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
        Word l = this.left.simulate(env);
        Word r = this.right.simulate(env);
        switch (this.binOp) {
            case PLUS:
                return l.add(r);
            case MINUS:
                return l.minus(r);
            case MULTIPLY:
                return l.multiply(r);
            default:
                logger.error("Binary operator: " + this.binOp + " not defined.");
                return null;
        }
    }

    @Override
    public Expression getExpression() {
        return null;
    }

    @Override
    public String getIRName() {
        return "BinaryOperation";
    }

    @Override
    public IROpCodes getIROpCode() {
        return IROpCodes.BINARY_OP;
    }
}
