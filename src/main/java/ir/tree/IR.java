package ir.tree;

import enums.IROpCodes;
import ir.tree.expression.Constant;
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

    public static Statement NOP() {
        return new Evaluate(new Constant(0));
    }
}
