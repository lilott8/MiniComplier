package ir.frame.x86;

import enums.IRBinOps;
import ir.frame.InFrame;
import ir.tree.expression.Expression;
import ir.tree.expression.Fetch;

/**
 * @created: 12/8/17
 * @since: 0.1
 * @project: minicomplier
 */
public class X86InFrame extends InFrame {

    public X86InFrame(int address) {
        super(address);
    }

    @Override
    public Expression getExpression(Expression exp) {
        return new Fetch(IRBinOps.addition(exp, this.address));
    }
}
