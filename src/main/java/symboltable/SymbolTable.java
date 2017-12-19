package symboltable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ir.frame.Access;

/**
 * @created: 11/30/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class SymbolTable {

    private Map<String, Clazz> table = new HashMap<>();

    public Map<String, Clazz> getTable() {
        return this.table;
    }

    public void addClass(Clazz clazz) {
        //if (!this.table.containsKey(clazz.getName())) {
        this.table.put(clazz.getName(), clazz);
        //}
    }

    public void addClassMethod(Clazz clazz, Method method) {
        if (!this.table.containsKey(clazz.getName())) {
            this.table.put(clazz.getName(), clazz);
        }

        this.table.get(clazz.getName()).addMethod(method);
    }

    public void addClassMethodVar(Clazz clazz, Method method, Variable variable, Access var) {
        if (!this.table.containsKey(clazz.getName())) {
            this.table.put(clazz.getName(), clazz);
        }

        this.table.get(clazz.getName()).getMethodByName(method.getName()).addLocal(variable);
    }

    public void addClassMethodVar(Clazz clazz, Method method, Variable variable) {
        if (!this.table.containsKey(clazz.getName())) {
            this.table.put(clazz.getName(), clazz);
        }

        this.table.get(clazz.getName()).getMethodByName(method.getName()).addLocal(variable);
    }

    public void addClassMethodVar(Clazz clazz, Method method, Access var) {
        if (!this.table.containsKey(clazz.getName())) {
            this.table.put(clazz.getName(), clazz);
        }
        VarBuilder varBuilder = new VarBuilder();
        varBuilder.addAccess(var).addName("UNKNOWN");

        this.table.get(clazz.getName()).getMethodByName(method.getName()).addLocal(varBuilder.build());
    }

    public void addClassVar(Clazz clazz, Variable variable, Access var) {
        if (!this.table.containsKey(clazz.getName())) {
            this.table.put(clazz.getName(), clazz);
        }

        this.table.get(clazz.getName()).addVariable(variable);
    }

    public Set<Variable> getClassVars(Clazz clazz) {
        Set<Variable> vars = new HashSet<>();
        for (Map.Entry<String, Variable> entry : this.table.get(clazz.getName()).getLocals().entrySet()) {
            vars.add(entry.getValue());
        }
        return vars;
    }

    public Access getMethodVar(Clazz clazz, Method method, String id) {
        return clazz.getMethodByName(method.getName()).getLocals().get(id).getAccess();
    }

    public Access getClassVar(Clazz clazz, Variable var) {
        return this.getClassVar(clazz, var.getName());
    }

    public Access getClassVar(Clazz clazz, String varName) {
        return this.table.get(clazz.getName()).getLocals().get(varName).getAccess();
    }

    public int getNumClasses(Clazz clazz) {
        return this.table.size();
    }
}
