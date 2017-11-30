//
// Generated by JTB 1.3.2
//

package parser.bioscript.ast;

import parser.bioscript.visitor.GJNoArguVisitor;
import parser.bioscript.visitor.GJVisitor;
import parser.bioscript.visitor.GJVoidVisitor;
import parser.bioscript.visitor.Visitor;


/**
 * Grammar production: f0 -> <HEAT> PrimaryExpression() <AT> IntegerLiteral() | <FOR>
 * IntegerLiteral()
 */
public class HeatStatement implements Node {
    public NodeChoice f0;

    public HeatStatement(NodeChoice n0) {
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

