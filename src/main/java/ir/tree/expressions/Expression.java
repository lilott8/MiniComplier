package ir.tree.expressions;

import java.util.List;

import frame.Frame;
import frame.Word;
import ir.tree.Node;

/**
 * @created: 12/5/17
 * @since: 0.1
 * @project: MiniComplier
 */
public abstract class Expression implements Node {
    // The book code provides these methods on each Exp node, these are used to aid
    // in implementing the conversion to canonical trees.

    /**
     * Retrieve a list of the direct subexpression of this node.
     */
    abstract public List<Expression> kids();

    /**
     * Create a new Exp node by copying this node and replacing it's
     * direct subexpressions.
     * <p>
     * It is assumed that the number and ordering of "kids" is the same as the
     * that returned by the kids() method. Any non-expression items are kept as
     * is.
     */
    abstract public Expression build(List<Expression> kids);

    /**
     * To simulate IR execution. This method assumes that the IR is
     * in almost canonical form. In particular, it is assumed that the
     * their are no ISeq expressions in the IRcode (the main reason for
     * this assumption is that it is next to impossible to simulate JUMP's
     * into and out of expressions.
     */
    abstract public Word interpret(Frame env);

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
    public void set(Word value, Frame env) {
        throw new Error("This IR " + this + " is not legal as the LHS of a MOVE.");
    }

    public boolean isCONST(int i) {
        return false;
    }
}
