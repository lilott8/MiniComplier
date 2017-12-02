package x86;

import java.util.List;

import frame.Access;
import frame.Frame;
import symboltable.Method;

/**
 * @created: 12/1/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class X86Frame extends Frame {

    public X86Frame(Method method, List<Boolean> formal) {
        super(method, formal);
    }

    @Override
    public Access allocateLocal(boolean b) {
        return null;
    }
}
