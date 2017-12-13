package ir.tree.expression;

import java.util.ArrayList;
import java.util.List;

import enums.IROpCodes;
import ir.simulate.frames.FrameSimulate;
import ir.simulate.word.Pointer;
import ir.simulate.word.Word;

/**
 * @created: 12/7/17
 * @since: 0.1
 * @project: minicomplier
 */
public class Fetch extends Expression {

    public final Expression expression;

    public Fetch(Expression expression) {
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
        return new Fetch(subexpressions.get(0));
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

    @Override
    public Expression getExpression() {
        return this.expression;
    }

    @Override
    public IROpCodes getIROpCode() {
        return IROpCodes.FETCH;
    }

    /**
     * To simulate IR execution. This method is implemented by IR tree's
     * that can be used as target (left hand side) of a move instruction.
     * <p>
     * Only MEM and TEMP nodes (at present) should be used as such so most
     * classes don't need to implement this.
     * <p>
     * It assigns the value to the location represented by the receiver
     * IRExp. The env parameter is provided because the reciever IRExp
     * may contain subtrees that need to be interpreted.
     */
    @Override
    public void set(Word value, FrameSimulate env) {
        Pointer p = (Pointer) this.expression.simulate(env);
        p.set(value);
    }

    @Override
    public String getIRName() {
        return "Fetch";
    }
}
