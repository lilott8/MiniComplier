package ir.simulate.word;

import enums.WordState;

/**
 * @created: 12/7/17
 * @since: 0.1
 * @project: minicomplier
 */
public abstract class Pointer extends Word {

    protected Pointer(int wordSize) {
        super(wordSize);
    }

    public abstract void set(Word newValue);

    public abstract Word get();

    public abstract Pointer add(int offSet);

    public abstract WordState getState();
}
