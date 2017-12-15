package ir.tree;

import enums.IROpCodes;
import ir.tree.expression.Constant;
import ir.tree.expression.Expression;
import ir.tree.statement.Evaluate;
import ir.tree.statement.Statement;

/**
 * @created: 12/7/17
 * @since: 0.1
 * @project: minicomplier
 */
public abstract class IR {

    public abstract String getIRName();

    public abstract IROpCodes getIROpCode();

    public static final Statement NOP;
    public static final Expression FALSE;
    public static final Expression TRUE;

    static {
        NOP = new Evaluate(new Constant(0));
        FALSE = new Constant(0);
        TRUE = new Constant(1);
    }

    public static Statement NOP() {
        return new Evaluate(new Constant(0));
    }

    public static Statement TRUE() {
        return new Evaluate(new Constant(1));
    }
}
