package ir.frame;

import ir.memory.Register;

/**
 * @created: 12/8/17
 * @since: 0.1
 * @project: minicomplier
 */
public abstract class InRegister extends Access {

    protected Register register;

    public InRegister(Register temp) {
        this.register = temp;
    }
}
