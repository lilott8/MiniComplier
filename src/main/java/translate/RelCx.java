package translate;

import enums.IRCJumps;
import ir.memory.Memory;
import ir.memory.Register;
import ir.tree.expression.Constant;
import ir.tree.expression.Expression;
import ir.tree.expression.ExpressionSequence;
import ir.tree.expression.Temp;
import ir.tree.statement.ConditionalJump;
import ir.tree.statement.Label;
import ir.tree.statement.Move;
import ir.tree.statement.Sequence;
import ir.tree.statement.Statement;

/**
 * @created: 12/18/17
 * @since: 0.1
 * @project: minicomplier
 */
public class RelCx implements TranslateExpression {

    private IRCJumps op;
    private Expression left;
    private Expression right;

    public RelCx(IRCJumps op, Expression left, Expression right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public Expression unEx() {
        Register register = new Register();
        Label t = new Label(new Memory());
        Label f = new Label(new Memory());

        return new ExpressionSequence(
                new Sequence(
                        new Move(new Temp(register), new Constant(1)),
                        new Sequence(this.unCx(t.getAddress(), f.getAddress()), new Sequence(f, new Sequence(new Move(new Temp(register), new Constant(0)), t))
                        )), new Temp(register));
    }

    @Override
    public Statement unNx() {
        Memory t = new Memory();
        Memory f = new Memory();

        return new Sequence(this.unCx(t, f), new Sequence(new Label(t), new Label(f)));
    }

    @Override
    public Statement unCx(Memory trueBranch, Memory falseBranch) {
        return new ConditionalJump(this.op, this.left, this.right, trueBranch, falseBranch);
    }
}
