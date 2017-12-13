package ir.simulate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import enums.SimulateMode;
import ir.memory.Memory;
import ir.simulate.frames.FrameSimulate;
import ir.simulate.word.EmptyWord;
import ir.simulate.word.Word;
import ir.translate.BasicBlock;
import ir.translate.BasicBlocks;
import ir.translate.MethodFragment;
import ir.tree.statement.Statement;

/**
 * @created: 12/11/17
 * @since: 0.1
 * @project: minicomplier
 */
public class SimulateMethod extends Callable {

    public final static Logger logger = LogManager.getLogger(SimulateMethod.class);

    private MethodFragment method;
    private HashMap<Memory, List<Statement>> addresses = new HashMap<>();
    private List<Statement> start;

    // If using basic blocks, we must JUMP to
    // this in order to finish execution.
    private BasicBlocks blocks;
    private BasicBlock done = BasicBlocks.sink;

    public SimulateMethod(MethodFragment fragment, SimulateMode mode) {
        super(fragment.getFrame().wordSize());
        this.method = fragment;
        switch (mode) {
            case LINEARIZED_IR:
                // TODO: implement linearlized IR
                logger.warn("Linearized not implemented, defaulting to basic blocks");
                this.buildBasicBlocks(this.method.getLinearizedBody());
                break;
            case TRACE_SCHEDULE:
                // TODO: implement trace schedule IR
                logger.warn("Trace Schedule not implemented, defaulting to basic blocks");
                this.buildBasicBlocks(this.method.getLinearizedBody());
                break;
            default:
            case BASIC_BLOCKS:
                this.blocks = new BasicBlocks(this.method.getLinearizedBody());
                break;
        }
    }

    private void buildBasicBlocks(List<Statement> bb) {
        this.blocks = new BasicBlocks(bb);
    }

    private void putLabel(Memory address, List<Statement> end) {
        List<Statement> existing = addresses.get(address);
        if (existing != null) {
            logger.error("Duplicate address in IR code: " + existing);
        } else {
            this.addresses.put(address, end);
        }
    }

    @Override
    public Word call(Simulator simulate, List<Word> args) {
        List<Statement> instructionPointers = this.start;
        FrameSimulate frame = this.method.getFrame().newFrameSimulation(simulate, args);
        while (!instructionPointers.isEmpty()) {
            Memory jumpTo = instructionPointers.remove(0).simulate(frame);
            if (jumpTo == null) {
                List<Statement> temporary = new ArrayList<Statement>();
                temporary.add(instructionPointers.get(instructionPointers.size() - 1));
                instructionPointers = temporary;
            } else if (jumpTo == this.done.getOperations().get(0).getLabel()) {
                return frame.getReturnValue();
            } else {
                instructionPointers = this.addresses.get(jumpTo);
            }
        }
        if (this.done == null) {
            // No basic blocks.  We just return when "done"
            return frame.getReturnValue();
        } else {
            // Use the correct IR code to jump out of the method body.
            logger.fatal("Simulation ended, done label was not defined.");
            return new EmptyWord(this.method.getFrame().wordSize());
        }
    }

    @Override
    public Word add(Word word) {
        return null;
    }

    @Override
    public Word minus(Word minus) {
        return null;
    }

    @Override
    public Memory getAddress() {
        return null;
    }
}
