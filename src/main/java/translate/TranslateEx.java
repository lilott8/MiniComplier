package translate;

import enums.IRCJumps;
import ir.memory.Memory;
import ir.tree.IR;
import ir.tree.expression.Expression;
import ir.tree.statement.ConditionalJump;
import ir.tree.statement.Evaluate;
import ir.tree.statement.Jump;
import ir.tree.statement.Statement;

/**
 * @created: 12/14/17
 * @since: 0.1
 * @project: minicomplier
 */
public class TranslateEx implements TranslateExpression {
    private Expression expression;

    public TranslateEx(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Expression unEx() {
        return this.expression;
    }

    @Override
    public Statement unNx() {
        return new Evaluate(this.expression);
    }

    @Override
    public Statement unCx(Memory trueBranch, Memory falseBranch) {
        Expression e = this.unEx();
        if (e.isConstant(1)) {
            return new Jump(trueBranch);
        } else if (e.isConstant(0)) {
            return new Jump(falseBranch);
        }

        return new ConditionalJump(IRCJumps.EQUAL, e, IR.TRUE, trueBranch, falseBranch);
    }
}
