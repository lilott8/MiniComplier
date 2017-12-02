package frame;

import java.util.List;

import symboltable.Method;

/**
 * @created: 12/1/17
 * @since: 0.1
 * @project: MiniComplier
 */
public abstract class Frame {

    public Frame(Method method, List<Boolean> formal) {

    }

    public abstract Access allocateLocal(boolean b);
}
