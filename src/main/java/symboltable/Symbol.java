package symboltable;

import enums.Scope;

/**
 * @created: 11/30/17
 * @since: 0.1
 * @project: MiniComplier
 */
public interface Symbol {
    String getName();

    Scope getScope();
}
