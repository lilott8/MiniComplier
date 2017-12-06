package ir.tree.expressions;

import java.util.ArrayList;
import java.util.List;

import frame.Frame;
import frame.Word;
import ir.interpreter.Callable;

/**
 * @created: 12/5/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class Call extends Expression {

    private Expression function;
    private List<Expression> args;

    public Call(Expression function, List<Expression> args) {
        this.function = function;
        this.args = args;
    }

    /**
     * Retrieve a list of the direct subexpression of this node.
     */
    @Override
    public List<Expression> kids() {
        // return new Call();
        return null;
    }

    /**
     * Create a new Exp node by copying this node and replacing it's
     * direct subexpressions.
     * <p>
     * It is assumed that the number and ordering of "kids" is the same as the
     * that returned by the kids() method. Any non-expression items are kept as
     * is.
     */
    @Override
    public Expression build(List<Expression> kids) {
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
    public Word interpret(Frame env) {
        Callable procVal = (Callable) this.function.interpret(env);
        List<Word> argVals = new ArrayList<>();
        for (Expression arg : args) {
            argVals.add(arg.interpret(env));
        }
        return procVal.call(env.getInterpreter(), argVals);
    }
}
