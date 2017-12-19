package translate;

import enums.IRCJumps;
import ir.memory.Memory;
import ir.tree.expression.Expression;
import ir.tree.statement.ConditionalJump;
import ir.tree.statement.Label;
import ir.tree.statement.Sequence;
import ir.tree.statement.Statement;

/**
 * @created: 12/18/17
 * @since: 0.1
 * @project: minicomplier
 */
public class AndExpression implements TranslateExpression {

    private TranslateExpression expression1;
    private TranslateExpression expression2;

    public AndExpression(TranslateExpression expression1, TranslateExpression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public Expression unEx() {
        return null;
    }

    @Override
    public Statement unNx() {
        return null;
    }

    @Override
    public Statement unCx(Memory trueBranch, Memory falseBranch) {
        Label label = new Label(new Memory("test?"));

        return Sequence.buildSequence(
                new ConditionalJump(IRCJumps.EQUAL, expression1.unEx(), expression2.unEx(), trueBranch, falseBranch),
                label,
                expression2.unCx(trueBranch, falseBranch)
        );
    }
}
