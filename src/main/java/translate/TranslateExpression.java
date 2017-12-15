package translate;

import ir.memory.Memory;
import ir.tree.expression.Expression;
import ir.tree.statement.Statement;

/**
 * @created: 12/12/17
 * @since: 0.1
 * @project: minicomplier
 */
public interface TranslateExpression {

    Memory PRINT = new Memory("mj_println");
    Memory MAIN = new Memory("mj_main");

    Expression unEx();

    Statement unNx();

    Statement unCx(Memory trueBranch, Memory falseBranch);
}
