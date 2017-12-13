package ir.simulate.word;

import ir.memory.Memory;

/**
 * @created: 12/11/17
 * @since: 0.1
 * @project: minicomplier
 */
public class JumpPointer extends Word {

    private final Memory address;

    public JumpPointer(Memory register, int wordSize) {
        super(wordSize);
        this.address = register;
    }

    @Override
    public Word add(Word word) {
        return null;
    }

    @Override
    public Word add(int word) {
        return null;
    }

    @Override
    public Word minus(Word minus) {
        return null;
    }

    @Override
    public Memory getAddress() {
        return this.address;
    }
}
