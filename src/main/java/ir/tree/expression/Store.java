package ir.tree.expression;

import java.util.ArrayList;
import java.util.List;

import enums.IROpCodes;
import ir.simulate.frames.FrameSimulate;
import ir.simulate.word.Pointer;
import ir.simulate.word.Word;

/**
 * @created: 12/8/17
 * @since: 0.1
 * @project: minicomplier
 */

/**
 * This models splits from the memory command.
 * When a memory is used as a left child of move,
 * this is the store command.
 */
public class Store extends Expression {

    private final Expression expression;

    public Store(Expression expression) {
        this.expression = expression;
    }

    /**
     * Retrieve a list of the direct subexpression of this node.
     */
    @Override
    public List<Expression> getSubExpressions() {
        List<Expression> list = new ArrayList<>();
        list.add(this.expression);
        return list;
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
        return new Store(subexpressions.get(0));
    }

    @Override
    public Expression getExpression() {
        return this.expression;
    }

    @Override
    public String getIRName() {
        return "Store";
    }

    @Override
    public IROpCodes getIROpCode() {
        return IROpCodes.STORE;
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
        Pointer p = (Pointer) this.expression.simulate(env);
        return p.get();
    }
}
