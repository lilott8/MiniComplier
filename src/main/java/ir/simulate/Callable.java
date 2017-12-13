package ir.simulate;

import java.util.List;

import ir.simulate.word.Pointer;
import ir.simulate.word.Word;

/**
 * @created: 12/7/17
 * @since: 0.1
 * @project: minicomplier
 */
public abstract class Callable extends Word {

    public Callable(int wordSize) {
        super(wordSize);
    }

    public abstract Word call(Simulator simulate, List<Word> list);

    public Pointer add(int value) {
        return null;
    }
}
