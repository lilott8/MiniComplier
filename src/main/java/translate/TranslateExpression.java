package translate;

import ir.tree.expression.Expression;
import ir.tree.statement.Label;
import ir.tree.statement.Statement;

/**
 * @created: 12/12/17
 * @since: 0.1
 * @project: minicomplier
 */
public interface TranslateExpression {
    Expression unEx();

    Statement unNx();

    Statement unCx(Label trueBranch, Label falseBranch);
}
