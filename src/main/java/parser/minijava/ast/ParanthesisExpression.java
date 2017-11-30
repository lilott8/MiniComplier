//
// Generated by JTBToolkit 1.3.2
//

package parser.minijava.ast;

import parser.minijava.visitor.GJNoArguVisitor;
import parser.minijava.visitor.GJVisitor;
import parser.minijava.visitor.GJVoidVisitor;
import parser.minijava.visitor.Visitor;

/**
 * Grammar production: f0 -> <LPAREN> f1 -> Expression() f2 -> <RPAREN>
 */
public class ParanthesisExpression implements Node {
    public NodeToken f0;
    public Expression f1;
    public NodeToken f2;

    public ParanthesisExpression(NodeToken n0, Expression n1, NodeToken n2) {
        f0 = n0;
        f1 = n1;
        f2 = n2;
    }

    public ParanthesisExpression(Expression n0) {
        f0 = new NodeToken("(");
        f1 = n0;
        f2 = new NodeToken(")");
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

