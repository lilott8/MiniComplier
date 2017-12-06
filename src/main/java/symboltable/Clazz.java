package symboltable;

import java.util.LinkedHashMap;
import java.util.Map;

import enums.Scope;

/**
 * @created: 11/30/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class Clazz<Type> implements Symbol {

    private String name;
    private Map<String, Variable<Type>> locals = new LinkedHashMap<>();
    private Map<String, Method<Type>> methods = new LinkedHashMap<>();
    private Scope scope = Scope.PUBLIC;
    private boolean isMainClass = false;

    public Clazz(String name) {
        this.name = name;
    }

    public Clazz(String name, boolean main) {
        this.name = name;
        this.isMainClass = main;
    }

    public boolean isMainClass() {
        return isMainClass;
    }

    public Clazz<Type> addMethod(Method<Type> method) {
        this.methods.put(method.getMethodSignature(), method);
        return this;
    }

    public Clazz<Type> addVariable(Variable<Type> v) {
        this.locals.put(v.getName(), v);
        return this;
    }

    public Method<Type> getMethodByName(String name) {
        return this.methods.get(name);
    }

    public Map<String, Variable<Type>> getLocals() {
        return locals;
    }

    public Map<String, Method<Type>> getMethods() {
        return methods;
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
        StringBuilder sb = new StringBuilder();

        sb.append("Class name: ").append(this.name).append(System.lineSeparator());
        sb.append("Variables: ").append(System.lineSeparator());
        for (Map.Entry<String, Variable<Type>> entry : this.locals.entrySet()) {
            sb.append("\t").append(entry.getValue()).append(System.lineSeparator());
        }
        sb.append("Methods: ").append(System.lineSeparator());
        for (Map.Entry<String, Method<Type>> entry : this.methods.entrySet()) {
            sb.append("\t").append(entry.getValue()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
