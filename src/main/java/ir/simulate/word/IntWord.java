package ir.simulate.word;

import ir.memory.Memory;

/**
 * @created: 12/12/17
 * @since: 0.1
 * @project: minicomplier
 */
public class IntWord extends Word {

    private final int value;

    public IntWord(int value, int wordSize) {
        super(wordSize);
        this.value = value;
    }

    @Override
    public Word add(Word word) {
        return null;
    }

    @Override
    public Word add(int word) {
        return new IntWord(this.value + word, this.wordSize);
    }

    @Override
    public Word minus(Word minus) {
        return null;
    }

    @Override
    public Memory getAddress() {
        return null;
    }

    @Override
    public Word multiply(Word multiply) {
        //return new IntWord(this.value * multiply)
        return null;
    }

    @Override
    public boolean isLessThan(Word lessThan) {
        return super.isLessThan(lessThan);
    }

    @Override
    public boolean isEqual(Word equal) {
        return super.isEqual(equal);
    }
}
