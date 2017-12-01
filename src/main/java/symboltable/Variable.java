package symboltable;

import enums.Scope;

/**
 * @created: 11/30/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class Variable<Type> implements Symbol {

    private String name;
    private Type type;
    private Scope scope = Scope.LOCAL;

    public Variable(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public Variable(String name, Type type, Scope scope) {
        this.name = name;
        this.type = type;
        this.scope = scope;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Scope getScope() {
        return this.scope;
    }
}
