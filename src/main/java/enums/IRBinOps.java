package enums;

import ir.tree.expression.BinaryOp;
import ir.tree.expression.Constant;
import ir.tree.expression.Expression;

/**
 * @created: 11/30/17
 * @since: 0.1
 * @project: MiniComplier
 */
public enum IRBinOps {
    PLUS, MINUS, MULTIPLY, DIVIDE, AND, OR, LEFTSHIFT, RIGHTSHIFT, XOR, ARRAYSHIFT;

    public static Expression addition(Expression v, int offset) {
        return new BinaryOp(PLUS, v, new Constant(offset));
    }
}
