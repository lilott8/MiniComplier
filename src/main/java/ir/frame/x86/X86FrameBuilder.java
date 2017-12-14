package ir.frame.x86;

import java.util.ArrayList;
import java.util.List;

import ir.frame.Access;
import ir.frame.Frame;
import ir.frame.FrameBuilder;
import ir.memory.Memory;
import ir.memory.Register;

/**
 * @created: 12/13/17
 * @since: 0.1
 * @project: minicomplier
 */
public class X86FrameBuilder implements FrameBuilder {
    public Frame buildFrame(Memory label, List<Boolean> formals) {
        List<Access> newFormals = new ArrayList<>();

        int offset = X86Frame.FIRST_FORMAL_OFFSET;

        for (Boolean b : formals) {
            if (b) {
                newFormals.add(new X86InFrame(offset));
                offset += X86Frame.WORD_SIZE;
            } else {
                newFormals.add(new X86InRegister(new Register()));
            }
        }

        return new X86Frame(label, newFormals);
    }
}
