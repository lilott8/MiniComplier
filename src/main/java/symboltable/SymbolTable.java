package symboltable;

/**
 * @created: 11/30/17
 * @since: 0.1
 * @project: MiniComplier
 */
public interface SymbolTable<Program> {

    void buildTable();

    Program getSymbolTable();
}
