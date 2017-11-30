package symboltable;

import com.sun.istack.internal.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

/**
 * @created: 11/30/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class SymbolTable<Key, Value> {

    public static final Logger logger = LogManager.getLogger(SymbolTable.class);

    private Map<Key, Value> symbolTable = new LinkedHashMap<>();
    private Stack<Map<Key, Value>> scope = new Stack<>();

    public boolean put(Key key, Value value) {
        if (this.symbolTable.containsKey(key)) {
            logger.warn(String.format("%s is already present in the symbol table.", key));
            return false;
        }
        this.symbolTable.put(key, value);
        return true;
    }

    public Value get(Key key) {
        if (!this.symbolTable.containsKey(key)) {
            logger.error(key + " isn't represented in the symbol table.");
        }
        // Note: this *could* be null.
        return this.symbolTable.get(key);
    }

    /**
     * Save the current state of the symbol table.
     */
    public void beginScope() {
        scope.push(symbolTable);
    }

    /**
     * This will remove the lowest scoping from the symbol table.
     */
    public void endScope() {
        for (Map.Entry<Key, Value> entry : this.scope.pop().entrySet()) {
            this.symbolTable.remove(entry.getKey());
        }
    }

}
