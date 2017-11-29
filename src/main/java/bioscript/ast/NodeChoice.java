//
// Generated by JTB 1.3.2
//

package bioscript.ast;

import bioscript.visitor.GJNoArguVisitor;
import bioscript.visitor.GJVisitor;
import bioscript.visitor.GJVoidVisitor;
import bioscript.visitor.Visitor;


/**
 * Represents a grammar choice, e.g. ( A | B )
 */
public class NodeChoice implements Node {
    public Node choice;
    public int which;

    public NodeChoice(Node node) {
        this(node, -1);
    }

    public NodeChoice(Node node, int whichChoice) {
        choice = node;
        which = whichChoice;
    }

    public void accept(Visitor v) {
        choice.accept(v);
    }

    public <R, A> R accept(GJVisitor<R, A> v, A argu) {
        return choice.accept(v, argu);
    }

    public <R> R accept(GJNoArguVisitor<R> v) {
        return choice.accept(v);
    }

    public <A> void accept(GJVoidVisitor<A> v, A argu) {
        choice.accept(v, argu);
    }
}

