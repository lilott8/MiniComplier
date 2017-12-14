package parser.minijava.semantics;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import enums.Scope;
import ir.frame.Frame;
import ir.memory.Memory;
import ir.translate.Fragment;
import ir.translate.Fragments;
import ir.translate.MethodFragment;
import ir.tree.IR;
import ir.tree.statement.Move;
import ir.tree.statement.Statement;
import parser.minijava.ast.AllocationExpression;
import parser.minijava.ast.AndExpression;
import parser.minijava.ast.ArrayAllocationExpression;
import parser.minijava.ast.ArrayAssignmentStatement;
import parser.minijava.ast.ArrayLength;
import parser.minijava.ast.ArrayLookup;
import parser.minijava.ast.AssignmentStatement;
import parser.minijava.ast.Block;
import parser.minijava.ast.BranchStatement;
import parser.minijava.ast.ClassDeclaration;
import parser.minijava.ast.ClassExtendsDeclaration;
import parser.minijava.ast.CompareExpression;
import parser.minijava.ast.Expression;
import parser.minijava.ast.ExpressionList;
import parser.minijava.ast.ExpressionRest;
import parser.minijava.ast.FalseLiteral;
import parser.minijava.ast.Identifier;
import parser.minijava.ast.IntegerLiteral;
import parser.minijava.ast.MJProgram;
import parser.minijava.ast.MainClass;
import parser.minijava.ast.MessageSend;
import parser.minijava.ast.MethodDeclarationUnordered;
import parser.minijava.ast.MinusExpression;
import parser.minijava.ast.Node;
import parser.minijava.ast.NodeChoice;
import parser.minijava.ast.NodeList;
import parser.minijava.ast.NodeListOptional;
import parser.minijava.ast.NodeOptional;
import parser.minijava.ast.NodeSequence;
import parser.minijava.ast.NotExpression;
import parser.minijava.ast.ParenthesisExpression;
import parser.minijava.ast.PlusExpression;
import parser.minijava.ast.PrimaryExpression;
import parser.minijava.ast.PrintStatement;
import parser.minijava.ast.ThisExpression;
import parser.minijava.ast.TimesExpression;
import parser.minijava.ast.TrueLiteral;
import parser.minijava.ast.TypeDeclarationUnordered;
import parser.minijava.ast.WhileStatement;
import parser.minijava.visitor.GJNoArguDepthFirst;
import symboltable.Clazz;
import symboltable.Method;
import symboltable.SymbolTable;
import symboltable.Type;
import translate.TranslateExpression;

/**
 * @created: 12/5/17
 * @since: 0.1
 * @project: minicomplier
 */
public class MJIRTranslator extends GJNoArguDepthFirst<TranslateExpression> {

    private Fragments fragments;
    private Fragments classFragment;

    private Stack<Frame> frames = new Stack<>();
    private SymbolTable symbolTable;
    private Clazz currentClass;
    private Method currentMethod;

    public MJIRTranslator(Frame frame, Fragments fragments, SymbolTable table) {
        this.frames.push(frame);
        this.fragments = fragments;
        this.symbolTable = table;
    }

    @Override
    public TranslateExpression visit(NodeList n) {
        for (Node node : n.nodes) {
            node.accept(this);
        }
        return null;
    }

    @Override
    public TranslateExpression visit(NodeListOptional n) {
        return super.visit(n);
    }

    @Override
    public TranslateExpression visit(NodeOptional n) {
        return super.visit(n);
    }

    @Override
    public TranslateExpression visit(NodeSequence n) {
        return super.visit(n);
    }

    /**
     * f0 -> MainClass()
     * f1 -> ( TypeDeclarationUnordered() )*
     * f2 -> <EOF>
     */
    @Override
    public TranslateExpression visit(MJProgram n) {
        n.f0.accept(this);
        n.f1.accept(this);

        for (Fragment fragment : this.classFragment.getList()) {
            this.fragments.add(fragment);
        }
        return null;
    }

    /**
     * f0 -> <CLASS>
     * f1 -> Identifier()
     * f2 -> <LBRACE>
     * f3 -> <PUBLIC>
     * f4 -> <STATIC>
     * f5 -> <VOID>
     * f6 -> <MAIN>
     * f7 -> <LPAREN>
     * f8 -> <STRING>
     * f9 -> <LSQPAREN>
     * f10 -> <RSQPAREN>
     * f11 -> Identifier()
     * f12 -> <RPAREN>
     * f13 -> <LBRACE>
     * f14 -> ( VarDeclarationUnordered() )*
     * f15 -> ( Statement() )*
     * f16 -> <RBRACE>
     * f17 -> <RBRACE>
     */
    @Override
    public TranslateExpression visit(MainClass n) {
        List<Boolean> frameParams = new ArrayList<>();
        this.frames.push(Frame.buildFrame(new Memory("mj_main"), frameParams));
        this.currentClass = new Clazz(n.f1.f0.toString());
        NodeChoice methodType = new NodeChoice(n.f5);
        this.currentMethod = new Method(n.f6.toString(), new Type(methodType.toString()), Scope.getScopeFromString(n.f3.toString()));

        this.fragments.add(new MethodFragment(this.frames.peek(), this.methodEntryExit(n.f15.accept(this))));

        this.currentClass = null;
        this.currentMethod = null;

        return null;
    }

    /**
     * f0 -> ClassDeclaration()
     * | ClassExtendsDeclaration()
     */
    @Override
    public TranslateExpression visit(TypeDeclarationUnordered n) {
        return super.visit(n);
    }

    /**
     * f0 -> <CLASS>
     * f1 -> Identifier()
     * f2 -> <LBRACE>
     * f3 -> ( Comment() )*
     * f4 -> ( VarDeclarationUnordered() )*
     * f5 -> ( MethodDeclarationUnordered() )*
     * f6 -> <RBRACE>
     */
    @Override
    public TranslateExpression visit(ClassDeclaration n) {
        return super.visit(n);
    }

    /**
     * f0 -> <CLASS>
     * f1 -> Identifier()
     * f2 -> <EXTENDS>
     * f3 -> Identifier()
     * f4 -> <LBRACE>
     * f5 -> ( Comment() )*
     * f6 -> ( VarDeclarationUnordered() )*
     * f7 -> ( MethodDeclarationUnordered() )*
     * f8 -> <RBRACE>
     */
    @Override
    public TranslateExpression visit(ClassExtendsDeclaration n) {
        return super.visit(n);
    }

    /**
     * f0 -> <PUBLIC>
     * f1 -> Type()
     * f2 -> Identifier()
     * f3 -> <LPAREN>
     * f4 -> ( FormalParameterList() )?
     * f5 -> <RPAREN>
     * f6 -> <LBRACE>
     * f7 -> ( Comment() )*
     * f8 -> ( VarDeclarationUnordered() )*
     * f9 -> ( Statement() )*
     * f10 -> <RETURN>
     * f11 -> Expression()
     * f12 -> <SEMICOLON>
     * f13 -> <RBRACE>
     */
    @Override
    public TranslateExpression visit(MethodDeclarationUnordered n) {
        return super.visit(n);
    }

    /**
     * f0 -> <LBRACE>
     * f1 -> ( Statement() )*
     * f2 -> <RBRACE>
     */
    @Override
    public TranslateExpression visit(Block n) {
        return super.visit(n);
    }

    /**
     * f0 -> Identifier()
     * f1 -> <ASSIGN>
     * f2 -> Expression()
     * f3 -> <SEMICOLON>
     */
    @Override
    public TranslateExpression visit(AssignmentStatement n) {
        return super.visit(n);
    }

    /**
     * f0 -> Identifier()
     * f1 -> <LSQPAREN>
     * f2 -> Expression()
     * f3 -> <RSQPAREN>
     * f4 -> <ASSIGN>
     * f5 -> Expression()
     * f6 -> <SEMICOLON>
     */
    @Override
    public TranslateExpression visit(ArrayAssignmentStatement n) {
        return super.visit(n);
    }

    /**
     * f0 -> <IF> <LPAREN> Expression() <RPAREN> <LBRACE> Statement() <RBRACE>
     * | <ELSE_IF> <LPAREN> Expression() <RPAREN> <LBRACE> Statement() <RBRACE>
     * | <ELSE> <LBRACE> Statement() <RBRACE>
     */
    @Override
    public TranslateExpression visit(BranchStatement n) {
        return super.visit(n);
    }

    /**
     * f0 -> <WHILE>
     * f1 -> <LPAREN>
     * f2 -> Expression()
     * f3 -> <RPAREN>
     * f4 -> Statement()
     */
    @Override
    public TranslateExpression visit(WhileStatement n) {
        return super.visit(n);
    }

    /**
     * f0 -> <PRINT>
     * f1 -> <LPAREN>
     * f2 -> Expression()
     * f3 -> <RPAREN>
     * f4 -> <SEMICOLON>
     */
    @Override
    public TranslateExpression visit(PrintStatement n) {
        return super.visit(n);
    }

    /**
     * f0 -> AndExpression()
     * | CompareExpression()
     * | PlusExpression()
     * | MinusExpression()
     * | TimesExpression()
     * | ArrayLookup()
     * | ArrayLength()
     * | MessageSend()
     * | PrimaryExpression()
     */
    @Override
    public TranslateExpression visit(Expression n) {
        return super.visit(n);
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> <AND>
     * f2 -> PrimaryExpression()
     */
    @Override
    public TranslateExpression visit(AndExpression n) {
        return super.visit(n);
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> <LT>
     * f2 -> PrimaryExpression()
     */
    @Override
    public TranslateExpression visit(CompareExpression n) {
        return super.visit(n);
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> <PLUS>
     * f2 -> PrimaryExpression()
     */
    @Override
    public TranslateExpression visit(PlusExpression n) {
        return super.visit(n);
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> <MINUS>
     * f2 -> PrimaryExpression()
     */
    @Override
    public TranslateExpression visit(MinusExpression n) {
        return super.visit(n);
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> <MULTIPLY>
     * f2 -> PrimaryExpression()
     */
    @Override
    public TranslateExpression visit(TimesExpression n) {
        return super.visit(n);
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> <LSQPAREN>
     * f2 -> PrimaryExpression()
     * f3 -> <RSQPAREN>
     */
    @Override
    public TranslateExpression visit(ArrayLookup n) {
        return super.visit(n);
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> <DOT>
     * f2 -> <LENGTH>
     */
    @Override
    public TranslateExpression visit(ArrayLength n) {
        return super.visit(n);
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> <DOT>
     * f2 -> Identifier()
     * f3 -> <LPAREN>
     * f4 -> ( ExpressionList() )?
     * f5 -> <RPAREN>
     */
    @Override
    public TranslateExpression visit(MessageSend n) {
        return super.visit(n);
    }

    /**
     * f0 -> Expression()
     * f1 -> ( ExpressionRest() )*
     */
    @Override
    public TranslateExpression visit(ExpressionList n) {
        return super.visit(n);
    }

    /**
     * f0 -> <COMMA>
     * f1 -> Expression()
     */
    @Override
    public TranslateExpression visit(ExpressionRest n) {
        return super.visit(n);
    }

    /**
     * f0 -> IntegerLiteral()
     * | TrueLiteral()
     * | FalseLiteral()
     * | Identifier()
     * | ThisExpression()
     * | ArrayAllocationExpression()
     * | AllocationExpression()
     * | NotExpression()
     * | ParenthesisExpression()
     */
    @Override
    public TranslateExpression visit(PrimaryExpression n) {
        return super.visit(n);
    }

    /**
     * f0 -> <INTEGER_LITERAL>
     */
    @Override
    public TranslateExpression visit(IntegerLiteral n) {
        return super.visit(n);
    }

    /**
     * f0 -> <TRUE>
     */
    @Override
    public TranslateExpression visit(TrueLiteral n) {
        return super.visit(n);
    }

    /**
     * f0 -> <FALSE>
     */
    @Override
    public TranslateExpression visit(FalseLiteral n) {
        return super.visit(n);
    }

    /**
     * f0 -> <IDENTIFIER>
     */
    @Override
    public TranslateExpression visit(Identifier n) {
        return super.visit(n);
    }

    /**
     * f0 -> <THIS>
     */
    @Override
    public TranslateExpression visit(ThisExpression n) {
        return super.visit(n);
    }

    /**
     * f0 -> <NEW>
     * f1 -> <INTEGER>
     * f2 -> <LSQPAREN>
     * f3 -> Expression()
     * f4 -> <RSQPAREN>
     */
    @Override
    public TranslateExpression visit(ArrayAllocationExpression n) {
        return super.visit(n);
    }

    /**
     * f0 -> <NEW>
     * f1 -> Identifier()
     * f2 -> <LPAREN>
     * f3 -> <RPAREN>
     */
    @Override
    public TranslateExpression visit(AllocationExpression n) {
        return super.visit(n);
    }

    /**
     * f0 -> <NOT>
     * f1 -> Expression()
     */
    @Override
    public TranslateExpression visit(NotExpression n) {
        return super.visit(n);
    }

    /**
     * f0 -> <LPAREN>
     * f1 -> Expression()
     * f2 -> <RPAREN>
     */
    @Override
    public TranslateExpression visit(ParenthesisExpression n) {
        return super.visit(n);
    }

    /**
     * Helper function that builds the entry/exit portion of the method
     */
    private Statement methodEntryExit(TranslateExpression body) {
        Frame currentFrame = this.frames.peek();

        Statement s = IR.NOP();
        if (body == null) {
            ir.tree.expression.Expression e = body.unEx();
            if (e != null) {
                s = new Move(currentFrame.returnValue(), e);
            } else {
                s = body.unNx();
            }
        }

        return currentFrame.procedureEntry(s);
    }
}
