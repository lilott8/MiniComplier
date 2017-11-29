//
// Generated by JTB 1.3.2
//

package bioscript.ast;

import bioscript.visitor.GJNoArguVisitor;
import bioscript.visitor.GJVisitor;
import bioscript.visitor.GJVoidVisitor;
import bioscript.visitor.Visitor;


/**
 * Grammar production: f0 -> <MIX> PrimaryExpression() <WITH> PrimaryExpression() | <FOR>
 * IntegerLiteral()
 */
public class MixStatement implements Node {
    public NodeChoice f0;

    public MixStatement(NodeChoice n0) {
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

