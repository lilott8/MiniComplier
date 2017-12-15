package symboltable;

import enums.Scope;
import exceptions.IncompleteVarException;
import ir.frame.Access;

/**
 * @created: 12/14/17
 * @since: 0.1
 * @project: minicomplier
 */
public class VarBuilder {
    private String name;
    private Type type;
    private Scope scope = Scope.LOCAL;
    private String typeName = "";
    private Access access = null;

    public Variable build() {
        if (this.access == null) {
            throw new IncompleteVarException("An access frame must be present to build a variable.");
        }
        return new Variable(this.name, this.type, this.scope, this.typeName, this.access);
    }

    public VarBuilder addName(String name) {
        this.name = name;
        return this;
    }

    public VarBuilder addType(Type type) {
        this.type = type;
        return this;
    }

    public VarBuilder addScope(Scope scope) {
        this.scope = scope;
        return this;
    }

    public VarBuilder addTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    public VarBuilder addAccess(Access access) {
        this.access = access;
        return this;
    }
}
