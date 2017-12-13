package ir.translate;

import java.util.ArrayList;
import java.util.List;

import ir.frame.Frame;

/**
 * @created: 12/12/17
 * @since: 0.1
 * @project: minicomplier
 */

/**
 * A collection of fragments of IR code (in minijava without inheritance,
 * there are only method fragments. In other languages various data
 * fragments may exist as well.
 * <p>
 * E.g. if inheritance is implemented in MiniJava we will likely need to
 * have data fragments to store virtual method tables computed by the
 * compiler. If String data type is supported, we will need String data
 * fragments to represent String literals.
 */
public class Fragments {
    /**
     * The translator should provide its FrameFactory in here. This object pretty much
     * centralises/isolates architecture specific code. Subsequent phases should use the
     * same target architecture as the translator, so it is convenient to have the
     * translator pass along this Frame factory object along with its generated IR code.
     */
    private Frame frame;

    private List<Fragment> fragments = new ArrayList<>();

    public Fragments(Frame factory) {
        this.frame = factory;
    }

    public void add(Fragment fragment) {
        this.fragments.add(fragment);
    }

    public List<Fragment> getList() {
        return this.fragments;
    }

    public Frame getFrame() {
        return this.frame;
    }
}
