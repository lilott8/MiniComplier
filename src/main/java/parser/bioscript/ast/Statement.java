//
// Generated by JTB 1.3.2
//

package parser.bioscript.ast;
import parser.bioscript.visitor.*;
/**
 * Grammar production:
 * f0 -> Instruction()
 * | BranchStatement()
 * | WhileStatement()
 */
public class Statement implements Node {
    public NodeChoice f0;

    public Statement(NodeChoice n0) {
        f0 = n0;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public <R, A> R accept(GJVisitor<R, A> v, A argu) {
        return v.visit(this, argu);
    }

    public <R> R accept(GJNoArguVisitor<R> v) {
        return v.visit(this);
    }

    public <A> void accept(GJVoidVisitor<A> v, A argu) {
        v.visit(this, argu);
    }
}

