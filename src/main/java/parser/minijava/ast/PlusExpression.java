//
// Generated by JTBToolkit 1.3.2
//

package parser.minijava.ast;

import parser.minijava.visitor.GJNoArguVisitor;
import parser.minijava.visitor.GJVisitor;
import parser.minijava.visitor.GJVoidVisitor;
import parser.minijava.visitor.Visitor;

/**
 * Grammar production: f0 -> PrimaryExpression() f1 -> <PLUS> f2 -> PrimaryExpression()
 */
public class PlusExpression implements Node {
    public PrimaryExpression f0;
    public NodeToken f1;
    public PrimaryExpression f2;

    public PlusExpression(PrimaryExpression n0, NodeToken n1, PrimaryExpression n2) {
        f0 = n0;
        f1 = n1;
        f2 = n2;
    }

    public PlusExpression(PrimaryExpression n0, PrimaryExpression n1) {
        f0 = n0;
        f1 = new NodeToken("+");
        f2 = n1;
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

