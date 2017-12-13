package ir.translate;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;
import java.util.List;

import ir.memory.Memory;
import ir.tree.statement.Jump;
import ir.tree.statement.Label;
import ir.tree.statement.Statement;

/**
 * @created: 12/12/17
 * @since: 0.1
 * @project: minicomplier
 */
public class BasicBlocks {

    public static final BasicBlock source = new BasicBlock();
    public static final BasicBlock sink = new BasicBlock();
    public List<List<Statement>> blocks = new ArrayList<>();
    Graph<BasicBlock, DefaultEdge> basicBlockGraph = new DefaultDirectedGraph<>(DefaultEdge.class);
    private BasicBlock currentBlock = source;
    private BasicBlock workingBlock = null;

    private Label doneAddress = null;

    {
        sink.addOperation(new Label(new Memory("Done")));
        basicBlockGraph.addVertex(source);
        basicBlockGraph.addVertex(sink);
    }

    public BasicBlocks(List<Statement> ops) {
        this.runBasicBlockAlgorithm(ops);
    }

    public Label getDoneAddress() {
        return this.doneAddress;
    }

    private void endBlock() {
        // Add it to the graph.
        basicBlockGraph.addVertex(workingBlock);
        // Build the edge.
        basicBlockGraph.addEdge(currentBlock, workingBlock);
        // Save the working block.
        currentBlock = workingBlock;
        // Create a new working block.
        workingBlock = null;
    }

    private void runBasicBlockAlgorithm(List<Statement> ops) {
        BasicBlock bb;
        List<Statement> instructions = new ArrayList<>();
        if (ops.get(0) instanceof Label) {
            instructions.add(new Label(new Memory()));
            instructions.addAll(ops.subList(1, ops.size() - 1));
        }

        for (Statement op : instructions) {
            if (op.isJump()) {
                // Add the last operation.
                workingBlock.addOperation(op);
                this.endBlock();
            } else if (op.isLabel()) {
                if (workingBlock == null) {
                    workingBlock = new BasicBlock();
                    workingBlock.addOperation(new Jump(op.getLabel()));
                }
                workingBlock.addOperation(op);
            } else {
                this.workingBlock.addOperation(op);
            }
        }
        if (workingBlock == null) {
            this.basicBlockGraph.addEdge(currentBlock, sink);
            this.doneAddress = (Label) sink.getOperations().get(0);
        }
    }

    public BasicBlocks addBlock(BasicBlock block) {
        this.basicBlockGraph.addVertex(block);
        this.basicBlockGraph.addEdge(currentBlock, block);
        this.currentBlock = block;
        return this;
    }

    public BasicBlocks addBlocks(List<BasicBlock> blocks) {
        for (BasicBlock block : blocks) {
            this.addBlock(block);
        }
        return this;
    }
}
