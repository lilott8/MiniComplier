package translate;

import ir.memory.Memory;
import ir.memory.Register;
import ir.tree.expression.Expression;
import ir.tree.expression.ExpressionSequence;
import ir.tree.expression.Temp;
import ir.tree.statement.Jump;
import ir.tree.statement.Label;
import ir.tree.statement.Move;
import ir.tree.statement.Sequence;
import ir.tree.statement.Statement;

/**
 * @created: 12/14/17
 * @since: 0.1
 * @project: minicomplier
 */
public class BranchExpression implements TranslateExpression {

    Memory TRUE = new Memory("t", true);
    Memory FALSE = new Memory("f", true);
    Memory JOIN = new Memory("join", true);
    private TranslateExpression testCondition;
    private TranslateExpression trueBranch;
    private TranslateExpression falseBranch;

    @Override
    public Expression unEx() {
        Register register = new Register();
        return new ExpressionSequence(Sequence.buildSequence(this.testCondition.unCx(TRUE, FALSE),
                Sequence.buildSequence(new Label(TRUE), new Move(new Temp(register), this.trueBranch.unEx())),
                Sequence.buildSequence(new Label(FALSE), new Move(new Temp(register), this.falseBranch.unEx()))),
                new Temp(register));
    }

    @Override
    public Statement unNx() {
        return Sequence.buildSequence(this.testCondition.unCx(this.TRUE, this.FALSE),
                Sequence.buildSequence(new Label(this.TRUE), this.trueBranch.unNx(), new Jump(JOIN)),
                Sequence.buildSequence(new Label(this.FALSE), this.falseBranch.unNx(), new Jump(JOIN)));
    }

    @Override
    public Statement unCx(Memory trueBranch, Memory falseBranch) {
        return null;
    }
}
