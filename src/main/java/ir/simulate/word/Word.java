package ir.simulate.word;

/**
 * @created: 12/7/17
 * @since: 0.1
 * @project: minicomplier
 */

import ir.memory.Memory;

/**
 * Subclasses of this class represents words in memory. It is tempting to
 * implement this just as a wrapper around an int, but we want to be able
 * to simulate pointers to blocks of memory without actually implementing
 * our own memory manager. Thus our Words are essentially typed. They may
 * be true integers or pointers.
 * <p>
 * We will simulate pointer arithmethic appropriately. This has the added
 * benefit that we can detect when pointer arithmetic creates "out of bounds"
 * pointers.
 *
 * taken from: @author kdvolder
 */
public abstract class Word {

    protected final int wordSize;

    protected Word(int wordSize) {
        this.wordSize = wordSize;
    }

    public abstract Word add(Word word);

    public abstract Word add(int word);

    public abstract Word minus(Word minus);

    public abstract Memory getAddress();

    public Word multiply(Word multiply) {
        return null;
    }

    public boolean isLessThan(Word lessThan) {
        return false;
    }

    public boolean isEqual(Word equal) {
        return false;
    }
}
