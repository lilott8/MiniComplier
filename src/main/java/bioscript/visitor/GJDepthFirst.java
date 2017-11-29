//
// Generated by JTB 1.3.2
//

package bioscript.visitor;

import java.util.Enumeration;

import bioscript.ast.AndExpression;
import bioscript.ast.AssignmentStatement;
import bioscript.ast.BranchStatement;
import bioscript.ast.DetectStatement;
import bioscript.ast.DrainStatement;
import bioscript.ast.EqualityExpression;
import bioscript.ast.Expression;
import bioscript.ast.FalseLiteral;
import bioscript.ast.GreaterThanEqualExpression;
import bioscript.ast.GreaterThanExpression;
import bioscript.ast.HeatStatement;
import bioscript.ast.Identifier;
import bioscript.ast.Instruction;
import bioscript.ast.IntegerLiteral;
import bioscript.ast.LessThanEqualExpression;
import bioscript.ast.LessThanExpression;
import bioscript.ast.Manifest;
import bioscript.ast.MinusExpression;
import bioscript.ast.MixStatement;
import bioscript.ast.Node;
import bioscript.ast.NodeList;
import bioscript.ast.NodeListOptional;
import bioscript.ast.NodeOptional;
import bioscript.ast.NodeSequence;
import bioscript.ast.NodeToken;
import bioscript.ast.NotEqualExpression;
import bioscript.ast.NotExpression;
import bioscript.ast.OrExpression;
import bioscript.ast.ParenthesisExpression;
import bioscript.ast.PlusExpression;
import bioscript.ast.PrimaryExpression;
import bioscript.ast.Program;
import bioscript.ast.RepeatStatement;
import bioscript.ast.SplitStatement;
import bioscript.ast.Statement;
import bioscript.ast.Stationary;
import bioscript.ast.TimesExpression;
import bioscript.ast.TrueLiteral;
import bioscript.ast.WhileStatement;

/**
 * Provides default methods which visit each node in the tree in depth-first order.  Your visitors
 * may extend this class.
 */
public class GJDepthFirst<R, A> implements GJVisitor<R, A> {
    //
    // Auto class visitors--probably don't need to be overridden.
    //
    public R visit(NodeList n, A argu) {
        R _ret = null;
        int _count = 0;
        for (Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
            e.nextElement().accept(this, argu);
            _count++;
        }
        return _ret;
    }

    public R visit(NodeListOptional n, A argu) {
        if (n.present()) {
            R _ret = null;
            int _count = 0;
            for (Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
                e.nextElement().accept(this, argu);
                _count++;
            }
            return _ret;
        } else
            return null;
    }

    public R visit(NodeOptional n, A argu) {
        if (n.present())
            return n.node.accept(this, argu);
        else
            return null;
    }

    public R visit(NodeSequence n, A argu) {
        R _ret = null;
        int _count = 0;
        for (Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
            e.nextElement().accept(this, argu);
            _count++;
        }
        return _ret;
    }

    public R visit(NodeToken n, A argu) {
        return null;
    }

    //
    // User-generated visitor methods below
    //

    /**
     * f0 -> Stationary() f1 -> Manifest() f2 -> <INSTRUCTIONS> f3 -> Instruction()
     */
    public R visit(Program n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> <STATIONARY> f1 -> Identifier()
     */
    public R visit(Stationary n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> <MANIFEST> f1 -> Identifier()
     */
    public R visit(Manifest n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> Instruction() | BranchStatement() | WhileStatement()
     */
    public R visit(Statement n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> MixStatement() | SplitStatement() | DrainStatement() | HeatStatement() |
     * DetectStatement() | RepeatStatement() | AssignmentStatement()
     */
    public R visit(Instruction n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> <MIX> PrimaryExpression() <WITH> PrimaryExpression() | <FOR> IntegerLiteral()
     */
    public R visit(MixStatement n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> <SPLIT> f1 -> PrimaryExpression() f2 -> <INTO> f3 -> PrimaryExpression()
     */
    public R visit(SplitStatement n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> <DRAIN> f1 -> PrimaryExpression()
     */
    public R visit(DrainStatement n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> <HEAT> PrimaryExpression() <AT> IntegerLiteral() | <FOR> IntegerLiteral()
     */
    public R visit(HeatStatement n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> <DETECT> Identifier() <ON> PrimaryExpression() | <FOR> <INTEGER_LITERAL>
     */
    public R visit(DetectStatement n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> WhileStatement()
     */
    public R visit(RepeatStatement n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> Identifier() f1 -> <ASSIGN> f2 -> Expression()
     */
    public R visit(AssignmentStatement n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> <IF> <LPAREN> Expression() <RPAREN> <LBRACE> Statement() <RBRACE> | <ELSE_IF> <LPAREN>
     * Expression() <RPAREN> <LBRACE> Statement() <RBRACE> | <ELSE> <LBRACE> Statement() <RBRACE>
     */
    public R visit(BranchStatement n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> <REPEAT> f1 -> IntegerLiteral() f2 -> <TIMES> f3 -> <LBRACE> f4 -> Statement() f5 ->
     * <RBRACE>
     */
    public R visit(WhileStatement n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        n.f4.accept(this, argu);
        n.f5.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> IntegerLiteral() | TrueLiteral() | FalseLiteral() | Identifier() |
     * ParenthesisExpression()
     */
    public R visit(PrimaryExpression n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> <INTEGER_LITERAL>
     */
    public R visit(IntegerLiteral n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> <TRUE>
     */
    public R visit(TrueLiteral n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> <FALSE>
     */
    public R visit(FalseLiteral n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> <IDENTIFIER>
     */
    public R visit(Identifier n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> AndExpression() | LessThanExpression() | LessThanEqualExpression() |
     * GreaterThanExpression() | GreaterThanEqualExpression() | NotEqualExpression() |
     * EqualityExpression() | OrExpression() | PlusExpression() | MinusExpression() |
     * TimesExpression() | PrimaryExpression()
     */
    public R visit(Expression n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> PrimaryExpression() f1 -> <AND> f2 -> PrimaryExpression()
     */
    public R visit(AndExpression n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> PrimaryExpression() f1 -> <LESSTHAN> f2 -> PrimaryExpression()
     */
    public R visit(LessThanExpression n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> PrimaryExpression() f1 -> <LESSTHANEQUAL> f2 -> PrimaryExpression()
     */
    public R visit(LessThanEqualExpression n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> PrimaryExpression() f1 -> <GREATERTHAN> f2 -> PrimaryExpression()
     */
    public R visit(GreaterThanExpression n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> PrimaryExpression() f1 -> <GREATERTHANEQUAL> f2 -> PrimaryExpression()
     */
    public R visit(GreaterThanEqualExpression n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> PrimaryExpression() f1 -> <NOTEQUAL> f2 -> PrimaryExpression()
     */
    public R visit(NotEqualExpression n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> PrimaryExpression() f1 -> <OR> f2 -> PrimaryExpression()
     */
    public R visit(EqualityExpression n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> PrimaryExpression() f1 -> <LESSTHAN> f2 -> PrimaryExpression()
     */
    public R visit(OrExpression n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> PrimaryExpression() f1 -> <ADD> f2 -> PrimaryExpression()
     */
    public R visit(PlusExpression n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> PrimaryExpression() f1 -> <MINUS> f2 -> PrimaryExpression()
     */
    public R visit(MinusExpression n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> PrimaryExpression() f1 -> <MULTIPLY> f2 -> PrimaryExpression()
     */
    public R visit(TimesExpression n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> <BANG> f1 -> Expression()
     */
    public R visit(NotExpression n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> <LPAREN> f1 -> Expression() f2 -> <RPAREN>
     */
    public R visit(ParenthesisExpression n, A argu) {
        R _ret = null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
    }

}
