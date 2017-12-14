package ir.frame;

import java.util.List;

import ir.memory.Memory;

/**
 * @created: 12/13/17
 * @since: 0.1
 * @project: minicomplier
 */
public interface FrameBuilder {

    Frame buildFrame(Memory label, List<Boolean> formals);
}
