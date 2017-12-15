package ir.tree.expression;

import java.util.ArrayList;
import java.util.List;

import enums.IROpCodes;
import ir.simulate.Callable;
import ir.simulate.frames.FrameSimulate;
import ir.simulate.word.Word;

/**
 * @created: 12/7/17
 * @since: 0.1
 * @project: minicomplier
 */
public class Call extends Expression {
    private Expression method;
    private List<Expression> args = new ArrayList<>();

    public Call(Expression method, List<Expression> args) {
        this.method = method;
        this.args = args;
    }

    public Call(Expression method, Expression arg) {
        this.method = method;
        this.args.add(arg);
    }

    /**
     * Retrieve a list of the direct subexpression of this node.
     */
    @Override
    public List<Expression> getSubExpressions() {
        List<Expression> list = new ArrayList<>();
        list.add(method);
        list.addAll(args);
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
        return new Call(subexpressions.get(0), subexpressions.subList(1, subexpressions.size()));
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
        Callable procVal = (Callable) this.method.simulate(env);
        List<Word> argVals = new ArrayList<>();
        for (Expression arg : args) {
            argVals.add(arg.simulate(env));
        }
        return procVal.call(env.getSimulate(), argVals);
    }

    @Override
    public Expression getExpression() {
        return this.method;
    }

    @Override
    public IROpCodes getIROpCode() {
        return IROpCodes.CALL;
    }

    public Expression getMethod() {
        return this.method;
    }

    public List<Expression> getArgs() {
        return this.args;
    }

    @Override
    public String getIRName() {
        return "Call";
    }
}
