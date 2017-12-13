package ir.simulate.word;

import java.util.ArrayList;
import java.util.List;

import enums.WordState;
import ir.memory.Memory;

/**
 * @created: 12/12/17
 * @since: 0.1
 * @project: minicomplier
 */
public class ArrayWord extends Pointer {

    private List<Word> words = new ArrayList<>();
    private WordState state = WordState.UNINITIALIZED;

    public ArrayWord(int numWords, int wordSize) {
        super(wordSize);
        for (int x = 0; x < numWords; x++) {
            this.words.add(new EmptyWord(this.wordSize));
        }
    }

    @Override
    public void set(Word newValue) {
        words.set(0, newValue);
    }

    @Override
    public Word get() {
        return this.words.get(0);
    }

    @Override
    public Pointer add(int offSet) {
        return null;
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

    @Override
    public WordState getState() {
        return this.state;
    }
}
