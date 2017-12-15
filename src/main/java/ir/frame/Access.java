package ir.frame;

import ir.tree.expression.Expression;

/**
 * @created: 12/7/17
 * @since: 0.1
 * @project: minicomplier
 */
public abstract class Access {

    public abstract Expression getExpression(Expression exp);

}
