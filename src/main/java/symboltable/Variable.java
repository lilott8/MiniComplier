package symboltable;

import org.apache.commons.lang3.StringUtils;

import enums.Scope;
import enums.Types;

/**
 * @created: 11/30/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class Variable implements Symbol {

    private String name;
    private Type type;
    private Scope scope = Scope.LOCAL;
    private String typeName = "";

    public Variable(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public Variable(String name, Type type, Scope scope) {
        this.name = name;
        this.type = type;
        this.scope = scope;
    }

    public Variable setTypeName(String typeName) {
        this.typeName = typeName;
        return this;
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

    public String toString() {
        String temp = StringUtils.isEmpty(this.typeName) ? this.type.toString() : this.typeName;
        return String.format("%s %s %s", this.scope, temp, this.name);
    }
}
