//
// Generated by JTBToolkit 1.3.2
//

package parser.minijava.ast;

import parser.minijava.visitor.GJNoArguVisitor;
import parser.minijava.visitor.GJVisitor;
import parser.minijava.visitor.GJVoidVisitor;
import parser.minijava.visitor.Visitor;

/**
 * The interface which all syntax tree classes must implement.
 */
public interface Node extends java.io.Serializable {
    public void accept(Visitor v);

    public <R, A> R accept(GJVisitor<R, A> v, A argu);

    public <R> R accept(GJNoArguVisitor<R> v);

    public <A> void accept(GJVoidVisitor<A> v, A argu);
}

