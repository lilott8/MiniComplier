package ir.frame;

/**
 * @created: 12/8/17
 * @since: 0.1
 * @project: minicomplier
 */
public abstract class InFrame extends Access {

    protected int address;

    public InFrame(int address) {
        this.address = address;
    }
}
