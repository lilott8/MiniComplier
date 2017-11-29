//
// Generated by JTB 1.3.2
//

package bioscript.visitor;

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
 * All GJ void visitors must implement this interface.
 */

public interface GJVoidVisitor<A> {

    //
    // GJ void Auto class visitors
    //

    public void visit(NodeList n, A argu);

    public void visit(NodeListOptional n, A argu);

    public void visit(NodeOptional n, A argu);

    public void visit(NodeSequence n, A argu);

    public void visit(NodeToken n, A argu);

    //
    // User-generated visitor methods below
    //

    /**
     * f0 -> Stationary() f1 -> Manifest() f2 -> <INSTRUCTIONS> f3 -> Instruction()
     */
    public void visit(Program n, A argu);

    /**
     * f0 -> <STATIONARY> f1 -> Identifier()
     */
    public void visit(Stationary n, A argu);

    /**
     * f0 -> <MANIFEST> f1 -> Identifier()
     */
    public void visit(Manifest n, A argu);

    /**
     * f0 -> Instruction() | BranchStatement() | WhileStatement()
     */
    public void visit(Statement n, A argu);

    /**
     * f0 -> MixStatement() | SplitStatement() | DrainStatement() | HeatStatement() |
     * DetectStatement() | RepeatStatement() | AssignmentStatement()
     */
    public void visit(Instruction n, A argu);

    /**
     * f0 -> <MIX> PrimaryExpression() <WITH> PrimaryExpression() | <FOR> IntegerLiteral()
     */
    public void visit(MixStatement n, A argu);

    /**
     * f0 -> <SPLIT> f1 -> PrimaryExpression() f2 -> <INTO> f3 -> PrimaryExpression()
     */
    public void visit(SplitStatement n, A argu);

    /**
     * f0 -> <DRAIN> f1 -> PrimaryExpression()
     */
    public void visit(DrainStatement n, A argu);

    /**
     * f0 -> <HEAT> PrimaryExpression() <AT> IntegerLiteral() | <FOR> IntegerLiteral()
     */
    public void visit(HeatStatement n, A argu);

    /**
     * f0 -> <DETECT> Identifier() <ON> PrimaryExpression() | <FOR> <INTEGER_LITERAL>
     */
    public void visit(DetectStatement n, A argu);

    /**
     * f0 -> WhileStatement()
     */
    public void visit(RepeatStatement n, A argu);

    /**
     * f0 -> Identifier() f1 -> <ASSIGN> f2 -> Expression()
     */
    public void visit(AssignmentStatement n, A argu);

    /**
     * f0 -> <IF> <LPAREN> Expression() <RPAREN> <LBRACE> Statement() <RBRACE> | <ELSE_IF> <LPAREN>
     * Expression() <RPAREN> <LBRACE> Statement() <RBRACE> | <ELSE> <LBRACE> Statement() <RBRACE>
     */
    public void visit(BranchStatement n, A argu);

    /**
     * f0 -> <REPEAT> f1 -> IntegerLiteral() f2 -> <TIMES> f3 -> <LBRACE> f4 -> Statement() f5 ->
     * <RBRACE>
     */
    public void visit(WhileStatement n, A argu);

    /**
     * f0 -> IntegerLiteral() | TrueLiteral() | FalseLiteral() | Identifier() |
     * ParenthesisExpression()
     */
    public void visit(PrimaryExpression n, A argu);

    /**
     * f0 -> <INTEGER_LITERAL>
     */
    public void visit(IntegerLiteral n, A argu);

    /**
     * f0 -> <TRUE>
     */
    public void visit(TrueLiteral n, A argu);

    /**
     * f0 -> <FALSE>
     */
    public void visit(FalseLiteral n, A argu);

    /**
     * f0 -> <IDENTIFIER>
     */
    public void visit(Identifier n, A argu);

    /**
     * f0 -> AndExpression() | LessThanExpression() | LessThanEqualExpression() |
     * GreaterThanExpression() | GreaterThanEqualExpression() | NotEqualExpression() |
     * EqualityExpression() | OrExpression() | PlusExpression() | MinusExpression() |
     * TimesExpression() | PrimaryExpression()
     */
    public void visit(Expression n, A argu);

    /**
     * f0 -> PrimaryExpression() f1 -> <AND> f2 -> PrimaryExpression()
     */
    public void visit(AndExpression n, A argu);

    /**
     * f0 -> PrimaryExpression() f1 -> <LESSTHAN> f2 -> PrimaryExpression()
     */
    public void visit(LessThanExpression n, A argu);

    /**
     * f0 -> PrimaryExpression() f1 -> <LESSTHANEQUAL> f2 -> PrimaryExpression()
     */
    public void visit(LessThanEqualExpression n, A argu);

    /**
     * f0 -> PrimaryExpression() f1 -> <GREATERTHAN> f2 -> PrimaryExpression()
     */
    public void visit(GreaterThanExpression n, A argu);

    /**
     * f0 -> PrimaryExpression() f1 -> <GREATERTHANEQUAL> f2 -> PrimaryExpression()
     */
    public void visit(GreaterThanEqualExpression n, A argu);

    /**
     * f0 -> PrimaryExpression() f1 -> <NOTEQUAL> f2 -> PrimaryExpression()
     */
    public void visit(NotEqualExpression n, A argu);

    /**
     * f0 -> PrimaryExpression() f1 -> <OR> f2 -> PrimaryExpression()
     */
    public void visit(EqualityExpression n, A argu);

    /**
     * f0 -> PrimaryExpression() f1 -> <LESSTHAN> f2 -> PrimaryExpression()
     */
    public void visit(OrExpression n, A argu);

    /**
     * f0 -> PrimaryExpression() f1 -> <ADD> f2 -> PrimaryExpression()
     */
    public void visit(PlusExpression n, A argu);

    /**
     * f0 -> PrimaryExpression() f1 -> <MINUS> f2 -> PrimaryExpression()
     */
    public void visit(MinusExpression n, A argu);

    /**
     * f0 -> PrimaryExpression() f1 -> <MULTIPLY> f2 -> PrimaryExpression()
     */
    public void visit(TimesExpression n, A argu);

    /**
     * f0 -> <BANG> f1 -> Expression()
     */
    public void visit(NotExpression n, A argu);

    /**
     * f0 -> <LPAREN> f1 -> Expression() f2 -> <RPAREN>
     */
    public void visit(ParenthesisExpression n, A argu);

}

