package ir.interpreter;

import java.util.List;

import frame.Word;

/**
 * @created: 12/5/17
 * @since: 0.1
 * @project: MiniComplier
 */
public abstract class Callable extends Word {

    public abstract Word call(Interpreter interp, List<Word> list);

    @Override
    public Word add(int value) {
        return null;
    }
}
