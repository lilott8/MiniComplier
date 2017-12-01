package symboltable;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import enums.Scope;

/**
 * @created: 11/30/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class Method<Type> implements Symbol {

    private Map<String, Variable> parameters = new LinkedHashMap<>();
    private Map<String, Variable> locals = new LinkedHashMap<>();
    private Type returnType;
    private Scope scope = Scope.PUBLIC;
    private String name;

    public Method(String name, Type type) {
        this.name = name;
        this.returnType = type;
    }

    public Method(String name, Type type, Scope scope) {
        this.name = name;
        this.returnType = type;
        this.scope = scope;
    }

    public Type getReturnType() {
        return returnType;
    }

    public Scope getScope() {
        return scope;
    }

    public String getName() {
        return name;
    }

    private Method<Type> addParameter(String key, Variable value) {
        this.parameters.put(key, value);
        return this;
    }

    public Method<Type> addParameter(Variable value) {
        return this.addParameter(value.getName(), value);
    }

    private Method<Type> addLocal(String key, Variable value) {
        this.locals.put(key, value);
        return this;
    }

    public Method<Type> addLocal(Variable value) {
        return this.addLocal(value.getName(), value);
    }

    public String getMethodSignature() {
        StringBuilder sb = new StringBuilder(this.name).append("_");
        if (!this.parameters.isEmpty()) {
            for (String s : this.parameters.keySet()) {
                sb.append(s).append("_");
            }
        }
        return StringUtils.substring(sb.toString(), 0, sb.toString().length() - 1);
    }
}
