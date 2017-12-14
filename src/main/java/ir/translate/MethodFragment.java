package ir.translate;

import java.util.List;

import ir.frame.Frame;
import ir.memory.Memory;
import ir.tree.statement.Statement;

/**
 * @created: 12/11/17
 * @since: 0.1
 * @project: minicomplier
 */

/**
 * A Method fragment represent the result of transforming a
 * minijava method declaration into IR code.
 */
public class MethodFragment implements Fragment {

    // Note that we keep all the different versions of the IR and Assem code.
    // In production code, you will only keep the "last" result
    // produced after trace scheduling. Even that could be thrown
    // away after the assembly has been produced to a file.
    // But having the different version here, though it takes up
    // memory is rather useful when you are debugging the compiler.

    private Frame frame;

    private Statement body;

    private List<Statement> linearizedBody;

    private BasicBlocks blocks;
    // TODO: add: private BasickBlocks blocks;

    private List<Statement> traceScheduled;

    public MethodFragment(Frame frame, Statement body) {
        this.frame = frame;
        this.body = body;
    }

    public Memory getLabel() {
        return this.frame.getLabel();
    }

    public Frame getFrame() {
        return this.frame;
    }

    public List<Statement> getLinearizedBody() {
        if (this.linearizedBody == null) {
            this.linearizedBody = Canon.linearize(this.body);
        }
        return this.linearizedBody;
    }

    public BasicBlocks getBasicBlocks() {
        if (this.blocks == null) {
            this.blocks = new BasicBlocks(getLinearizedBody());
        }
        return this.blocks;
    }

}
