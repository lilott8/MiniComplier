package symboltable;

import org.apache.commons.lang3.StringUtils;

import enums.Scope;
import ir.frame.Access;

/**
 * @created: 11/30/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class Variable implements Symbol {

    private final String name;
    private final Type type;
    private final Scope scope;
    private final String typeName;
    private final Access access;

    Variable(String name, Type type, Scope scope, String typeName, Access access) {
        this.name = name;
        this.type = type;
        this.scope = scope;
        this.typeName = typeName;
        this.access = access;
    }

    public Access getAccess() {
        return this.access;
    }

    public String getTypeName() {
        return this.typeName;
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
