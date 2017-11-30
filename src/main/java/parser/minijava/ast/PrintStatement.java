//
// Generated by JTB 1.3.2
//

package parser.minijava.ast;

import parser.minijava.visitor.GJNoArguVisitor;
import parser.minijava.visitor.GJVisitor;
import parser.minijava.visitor.GJVoidVisitor;
import parser.minijava.visitor.Visitor;

/**
 * Grammar production:
 * f0 -> <PRINT>
 * f1 -> <LPAREN>
 * f2 -> Expression()
 * f3 -> <RPAREN>
 * f4 -> <SEMICOLON>
 */
public class PrintStatement implements Node {
    public NodeToken f0;
    public NodeToken f1;
    public Expression f2;
    public NodeToken f3;
    public NodeToken f4;

    public PrintStatement(NodeToken n0, NodeToken n1, Expression n2, NodeToken n3, NodeToken n4) {
        f0 = n0;
        f1 = n1;
        f2 = n2;
        f3 = n3;
        f4 = n4;
    }

    public PrintStatement(Expression n0) {
        f0 = new NodeToken("System.out.println");
        f1 = new NodeToken("(");
        f2 = n0;
        f3 = new NodeToken(")");
        f4 = new NodeToken(";");
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

