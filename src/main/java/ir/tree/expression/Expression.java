package ir.tree.expression;

import java.util.List;

import ir.simulate.frames.FrameSimulate;
import ir.simulate.word.Word;
import ir.tree.IR;
import ir.tree.IRNode;

/**
 * @created: 12/7/17
 * @since: 0.1
 * @project: minicomplier
 */
public abstract class Expression extends IR implements IRNode {

    // The book code provides these methods on each Exp node, these are used to aid
    // in implementing the conversion to canonical trees.

    /**
     * Retrieve a list of the direct subexpression of this node.
     */
    public abstract List<Expression> getSubExpressions();

    /**
     * Create a new Exp node by copying this node and replacing it's
     * direct subexpressions.
     * <p>
     * It is assumed that the number and ordering of "getSubexpressions" is the same as the
     * that returned by the getSubexpressions() method. Any non-expression items are kept as
     * is.
     */
    public abstract Expression build(List<Expression> subexpressions);

    /**
     * To simulate IR execution. This method assumes that the IR is
     * in almost canonical form. In particular, it is assumed that the
     * their are no ISeq expressions in the IRcode (the main reason for
     * this assumption is that it is next to impossible to simulate JUMP's
     * into and out of expressions.
     */
    public abstract Word simulate(FrameSimulate env);

    public abstract Expression getExpression();

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
    public void set(Word value, FrameSimulate env) {
        throw new Error("This IR " + this + " is not legal as the LHS of a MOVE.");
    }

    public boolean isConstant(int i) {
        return false;
    }

}
