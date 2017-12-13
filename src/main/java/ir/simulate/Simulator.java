package ir.simulate;

import org.junit.Assert;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import enums.SimulateMode;
import ir.memory.Memory;
import ir.translate.Fragment;
import ir.translate.Fragments;
import ir.translate.MethodFragment;

/**
 * @created: 12/7/17
 * @since: 0.1
 * @project: minicomplier
 */

/**
 * For testing purposes we implement an IR interpreter that can directly
 * simulate the IR code.
 * <p>
 * We do this so that we can write some tests to see if the generated IR code
 * actually does what it is expected to do.
 * <p>
 * Without this interpreter we are not able to test the IR code except by manual
 * inspection (or until our compiler is completely finished and produces
 * executable machine code).
 * <p>
 * An additional benefit of the interpreter is that it can also serve as a kind
 * of documentation of the exact semantics of the IR code.
 * <p>
 * For practical reasons, the IR can *not* be simulated exactly as it is
 * generated. Some massaging of the IR to make it amenable to interpretation is
 * necessary first. The minimum requirement is that the IR has been "lineasized"
 * as described in chapter 8 of the book, and implemented by the
 * minijava.ir.Canon class. The interpreter is also capable of interpreti
 * ng IR
 * code as produced by the basic blocks algorithm and IR code as produced by the
 * TraceScheduler.
 * <p>
 * You choose which of these types of IR to use by passing in a value of the
 * {@link InterpMode} enumeration to the Simulator constructor.
 * <p>
 * Caveats: 1) this interpreter doesn't use an explicit stack. It rather uses
 * the Java stack implicitly (the interpreter recurses when interpreting CALL
 * instructions). Thus some things that would be possible on a "real machine can
 * not be simulated (e.g. code that manipulates the return address on the
 * stack).
 * <p>
 * 2) The simulated frame (X86SimFrame class) has no provision for storing local
 * variables other than the formal parameters "in frame". It is assumed that all
 * locals are in Register's, which is the case for minijava.
 * <p>
 * 3) Pointer arithmetic is limited. It is not possible to simulate
 * writing/reading to/from pointer's that have strayed outside the bounds of
 * their allocated blocks of memory. This is essentially a good thing. It will
 * detect "out of bounds errors" that may be the result of bugs in your IR code
 * generator (assuming that you are compiling well behaved code, any
 * out-of-bounds errors must be the result of a compiler bug).
 *
 * Taken from: @author kdvolder
 */
public class Simulator {

    /**
     * The program may produce output in here.
     */
    private StringWriter out = null;

    /**
     * A map of all the method fragments in the program. (To find the
     * code for CALLed procedures).
     */
    private Map<Memory, Callable> methods = new HashMap<>();

    /**
     * The "main" method fragment.
     */
    private Callable main = null;

    /**
     * We'll need to know the wordsize to run the simulation (for
     * simulating pointer arithmetic).
     */
    private int wordSize;

    /**
     * Which kind of IR code for methods are we using?
     */
    private SimulateMode simulationMode;

    /**
     * Setup the interpreter for running a given program.
     */
    public Simulator(Fragments program, SimulateMode simMode) {
        this.simulationMode = simMode;
        for (Fragment fragment : program.getList()) {
            if (fragment instanceof MethodFragment) {
                MethodFragment methodFrag = (MethodFragment) fragment;
                SimulateMethod callable = new SimulateMethod(methodFrag, simulationMode);
                if (main == null) {
                    wordSize = methodFrag.getFrame().wordSize();
                    main = callable;
                } else {
                    methods.put(methodFrag.getLabel(), callable);
                }
            } else {
                throw new Error("IR Simulator doesn't know about fragments of this type: " + fragment.getClass());
            }
        }
        defineSystemFunctions();
    }

    private void defineSystemFunctions() {

    }

    public String run() {
        Assert.assertNull("You aren't supposed to run the program more than once with the same interpreter", out);
        out = new StringWriter();
        main.call(this, new ArrayList<>());
        return out.toString();
    }

    public Callable getProcLabel(Memory label) {
        Callable result = methods.get(label);
        return result;
    }
}
