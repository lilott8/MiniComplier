//
// Generated by JTB 1.3.2
//

package parser.bioscript.ast;
import parser.bioscript.visitor.*;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Represents an optional grammar list, e.g. ( A )*
 */
public class NodeListOptional implements NodeListInterface {
    public Vector<Node> nodes;

    public NodeListOptional() {
        nodes = new Vector<Node>();
    }

    public NodeListOptional(Node firstNode) {
        nodes = new Vector<Node>();
        addNode(firstNode);
    }

    public void addNode(Node n) {
        nodes.addElement(n);
    }

    public Enumeration<Node> elements() {
        return nodes.elements();
    }

    public Node elementAt(int i) {
        return nodes.elementAt(i);
    }

    public int size() {
        return nodes.size();
    }

    public boolean present() {
        return nodes.size() != 0;
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

