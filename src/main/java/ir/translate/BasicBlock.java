package ir.translate;

import java.util.List;

import ir.memory.Memory;
import ir.tree.statement.Statement;

/**
 * @created: 12/12/17
 * @since: 0.1
 * @project: minicomplier
 */
public class BasicBlock {

    private static int counter = 0;
    public final int id;

    private Memory done;

    private List<Statement> operations;

    {
        id = counter;
        counter++;
    }

    public BasicBlock() {

    }

    public BasicBlock(List<Statement> ops) {
        this.operations = ops;
    }

    public List<Statement> getOperations() {
        return this.operations;
    }

    public BasicBlock addOperation(Statement op) {
        this.operations.add(op);
        return this;
    }
}
