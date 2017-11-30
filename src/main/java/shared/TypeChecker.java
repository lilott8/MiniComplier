package shared;

import config.ConfigFactory;
import config.ParseConfig;

/**
 * @created: 11/30/17
 * @since: 0.1
 * @project: MiniComplier
 */
public abstract class TypeChecker {

    protected ParseConfig config = ConfigFactory.getConfig();

    public boolean runTypeChecker() {
        switch (config.getTypeCheckLevel()) {
            case DISABLED:
                return false;
            case STATIC:
            default:
                return this.runStaticTypeCheck();
            case INFERENCE:
                return this.runTypeInference();
        }
    }

    protected abstract boolean runTypeInference();

    protected abstract boolean runStaticTypeCheck();
}
