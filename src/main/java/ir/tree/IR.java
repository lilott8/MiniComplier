package ir.tree;

import enums.IROpCodes;

/**
 * @created: 12/7/17
 * @since: 0.1
 * @project: minicomplier
 */
public abstract class IR {

    public abstract String getIRName();

    public abstract IROpCodes getIROpCode();
}
