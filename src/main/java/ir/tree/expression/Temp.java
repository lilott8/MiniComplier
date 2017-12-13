package ir.tree.expression;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import enums.IROpCodes;
import ir.memory.Register;
import ir.simulate.frames.FrameSimulate;
import ir.simulate.word.Word;

/**
 * @created: 12/7/17
 * @since: 0.1
 * @project: minicomplier
 */
public class Temp extends Expression {

    public static final Logger logger = LogManager.getLogger(Temp.class);

    public final Register temp;

    public Temp(Register temp) {
        this.temp = temp;
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
        return env.getRegister(temp);
    }

    @Override
    public Expression getExpression() {
        logger.warn("getExpression is not implemented");
        return null;
    }

    @Override
    public String getIRName() {
        return "Register";
    }

    @Override
    public IROpCodes getIROpCode() {
        return IROpCodes.TEMP;
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
        env.setRegister(this.temp, value);
    }
}
