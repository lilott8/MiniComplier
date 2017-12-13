package ir.frame.x86;

import ir.frame.InRegister;
import ir.memory.Register;
import ir.tree.expression.Expression;
import ir.tree.expression.Temp;

/**
 * @created: 12/8/17
 * @since: 0.1
 * @project: minicomplier
 */
public class X86InRegister extends InRegister {

    public X86InRegister(Register temp) {
        super(temp);
    }

    @Override
    public Expression getExpression(Expression exp) {
        return new Temp(this.register);
    }
}
