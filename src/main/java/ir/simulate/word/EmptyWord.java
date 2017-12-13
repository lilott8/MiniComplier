package ir.simulate.word;

import ir.memory.Memory;

/**
 * @created: 12/12/17
 * @since: 0.1
 * @project: minicomplier
 */

/**
 * The EmptyWord represents a value that is unknown because
 * it is in a memory location that was never written to.
 * <p>
 * Our implementation will throw an error if a program tries to operate
 * on an uninitialised word in any way.
 */
public class EmptyWord extends Word {

    /**
     * If something other than null is here, this will be used as the
     * toString value for this UnitializedWord (to make the printout
     * of stack frames a little more readable).
     */
    private String contents;

    public EmptyWord(int wordSize) {
        super(wordSize);
        this.contents = "?";
    }

    public EmptyWord(String contents, int wordSize) {
        super(wordSize);
        this.contents = contents;
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
        return null;
    }
}
