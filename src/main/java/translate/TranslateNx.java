package translate;

import ir.memory.Memory;
import ir.tree.expression.Expression;
import ir.tree.statement.Statement;

/**
 * @created: 12/14/17
 * @since: 0.1
 * @project: minicomplier
 */
public class TranslateNx implements TranslateExpression {

    private Statement statement;

    public TranslateNx(Statement statement) {
        this.statement = statement;
    }

    @Override
    public Expression unEx() {
        return null;
    }

    @Override
    public Statement unNx() {
        return this.statement;
    }

    @Override
    public Statement unCx(Memory trueBranch, Memory falseBranch) {
        return null;
    }
}
