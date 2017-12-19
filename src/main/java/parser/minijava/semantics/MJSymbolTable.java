package parser.minijava.semantics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import enums.Scope;
import enums.Types;
import ir.frame.Access;
import ir.frame.Frame;
import ir.memory.Memory;
import ir.translate.Fragment;
import ir.translate.Fragments;
import parser.minijava.ast.ArrayType;
import parser.minijava.ast.BooleanType;
import parser.minijava.ast.ClassDeclaration;
import parser.minijava.ast.ClassExtendsDeclaration;
import parser.minijava.ast.FormalParameter;
import parser.minijava.ast.FormalParameterList;
import parser.minijava.ast.Identifier;
import parser.minijava.ast.IntegerType;
import parser.minijava.ast.MJProgram;
import parser.minijava.ast.MainClass;
import parser.minijava.ast.MethodDeclarationUnordered;
import parser.minijava.ast.NodeChoice;
import parser.minijava.ast.NodeList;
import parser.minijava.ast.VarDeclarationUnordered;
import parser.minijava.visitor.GJNoArguDepthFirst;
import shared.Phase;
import symboltable.Clazz;
import symboltable.Method;
import symboltable.Symbol;
import symboltable.SymbolTable;
import symboltable.Type;
import symboltable.VarBuilder;
import translate.TranslateExpression;

/**
 * @created: 11/30/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MJSymbolTable extends GJNoArguDepthFirst<SymbolTable> implements Phase {

    //private Map<String, Symbol> symbolTable = new LinkedHashMap<>();

    private Fragments fragments;
    private Fragments classFragments;
    private Stack<Frame> frames = new Stack<>();
    private SymbolTable symbols = new SymbolTable();
    private int methodParams = 0;
    private List<String> varNames = new ArrayList<>();

    private Method currentMethod;
    private Clazz currentClazz;
    private Type currentType;
    private String varName;

    public static final Logger logger = LogManager.getLogger(MJSymbolTable.class);

    public MJSymbolTable(Frame frame, Fragments fragment) {
        this.frames.push(frame);
        this.fragments = fragments;
        this.classFragments = new Fragments(frame);
    }

    @Override
    public String getPhaseName() {
        return null;
    }

    @Override
    public Phase runPhase() {
        return null;
    }

    private void put(Symbol symbol) {
        //this.symbolTable.put(symbol.getName(), symbol);
    }

    public SymbolTable getSymbolTable() {
        return this.symbols;
    }

    @Override
    public SymbolTable visit(NodeList n) {
        for (int i = 0; i < n.size(); i++) {
            n.elementAt(i).accept(this);
        }
        return null;
    }

    /**
     * f0 -> MainClass()
     * f1 -> ( TypeDeclarationUnordered() )*
     * f2 -> <EOF>
     */
    @Override
    public SymbolTable visit(MJProgram n) {
        n.f0.accept(this);
        n.f1.accept(this);

        // This ensures that the mainClass fragment is first in the list
        for (Fragment frag : this.classFragments.getList()) {
            this.fragments.add(frag);
        }

        return this.symbols;
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
    public SymbolTable visit(MainClass n) {
        //super.visit(n);

        Clazz mainClass = new Clazz(n.f1.f0.toString(), true);
        this.frames.push(Frame.buildFrame(TranslateExpression.MAIN, new ArrayList<>()));

        //Method mainMethod = new Method(n.f5.toString(), new Type(new NodeChoice(n.f5)), Scope.getScopeFromString(n.f3.toString()));
        NodeChoice methodType = new NodeChoice(n.f5);
        Method mainMethod = new Method(n.f5.toString(), new Type(methodType.toString()), Scope.getScopeFromString(n.f3.toString()));
        //Variable argument = new Variable(n.f11.f0.toString(), new Type(new NodeChoice(n.f8)));
        VarBuilder builder = new VarBuilder();
        builder.addAccess(this.frames.peek().allocateLocal(false)).addName(n.f11.f0.toString()).addType(new Type(Types.STRING));
        mainMethod.addParameter(builder.build());
        mainClass.addMethod(mainMethod);
        this.currentClazz = mainClass;
        this.currentMethod = mainMethod;
        this.symbols.addClass(this.currentClazz);

        // fetch the rest of the symbols to add them to the symbol table.
        n.f0.accept(this);
        n.f1.accept(this);
        n.f2.accept(this);
        n.f3.accept(this);
        n.f4.accept(this);
        n.f5.accept(this);
        n.f6.accept(this);
        n.f7.accept(this);
        n.f8.accept(this);
        n.f9.accept(this);
        n.f10.accept(this);
        n.f11.accept(this);
        n.f12.accept(this);
        n.f13.accept(this);

        n.f14.accept(this);
        n.f15.accept(this);
        n.f16.accept(this);
        n.f17.accept(this);

        this.currentClazz = null;
        this.currentMethod = null;
        this.frames.pop();

        return this.symbols;
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
    public SymbolTable visit(ClassDeclaration n) {
        //super.visit(n);

        Clazz clazz = new Clazz(n.f1.f0.toString());
        this.currentClazz = clazz;
        this.symbols.addClass(clazz);

        n.f0.accept(this);
        n.f1.accept(this);
        n.f2.accept(this);
        n.f3.accept(this);
        n.f4.accept(this);
        n.f5.accept(this);
        n.f6.accept(this);

        this.currentClazz = null;

        return null;
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
    public SymbolTable visit(ClassExtendsDeclaration n) {
        //super.visit(n);

        Clazz clazz = new Clazz(n.f1.f0.toString());
        //Clazz extending = (Clazz) this.symbols.get(n.f3.f0.toString());

        //for (Map.Entry<String, Variable> entry : extending.getLocals().entrySet()) {
        //    clazz.addVariable(entry.getValue());
        //}

        //for (Map.Entry<String, Method> entry : extending.getMethods().entrySet()) {
        //    clazz.addMethod(entry.getValue());
        //}
        this.currentClazz = clazz;
        this.put(clazz);

        n.f0.accept(this);
        n.f1.accept(this);
        n.f2.accept(this);
        n.f3.accept(this);
        n.f4.accept(this);
        n.f5.accept(this);
        n.f6.accept(this);
        n.f7.accept(this);
        n.f8.accept(this);

        currentClazz = null;

        return null;
    }

    /**
     * f0 -> ArrayType()
     * | BooleanType()
     * | IntegerType()
     * | Identifier()
     */
    @Override
    public SymbolTable visit(parser.minijava.ast.Type n) {
        //this.currentType = new Type(n.f0.choice.toString());
        n.f0.accept(this);

        return null;
    }

    /**
     * f0 -> <INTEGER>
     * f1 -> <LSQPAREN>
     * f2 -> <RSQPAREN>
     */
    @Override
    public SymbolTable visit(ArrayType n) {
        this.currentType = new Type(Types.ARRAY);

        n.f0.accept(this);
        n.f1.accept(this);
        n.f2.accept(this);

        return null;
    }

    /**
     * f0 -> <BOOLEAN>
     */
    @Override
    public SymbolTable visit(BooleanType n) {
        this.currentType = new Type(Types.BOOL);
        n.f0.accept(this);

        return null;
    }

    /**
     * f0 -> <INTEGER>
     */
    @Override
    public SymbolTable visit(IntegerType n) {
        this.currentType = new Type(Types.INT);
        n.f0.accept(this);

        return null;
    }



    /**
     * f0 -> Type()
     * f1 -> Identifier()
     * f2 -> <SEMICOLON>
     */
    // TODO: Start Here
    @Override
    public SymbolTable visit(VarDeclarationUnordered n) {
        //super.visit(n);
        n.f0.accept(this);

        VarBuilder builder = new VarBuilder();
        if (currentMethod != null) {
            builder.addAccess(this.frames.peek().allocateLocal(false))
                    .addName(n.f1.f0.toString())
                    .addType(this.currentType);
            this.symbols.addClassMethodVar(this.currentClazz, this.currentMethod, builder.build());
            this.currentMethod.addLocal(builder.build());
            this.currentClazz.addMethod(this.currentMethod);
        } else {
            builder.addAccess(this.frames.peek().allocate(this.symbols.getClassVars(this.currentClazz).size() * this.frames.peek().wordSize()))
                    .addName(n.f1.f0.toString())
                    .addType(this.currentType);
            this.currentClazz.addVariable(builder.build());
        }

        this.put(this.currentClazz);

        this.currentType = null;

        n.f1.accept(this);
        n.f2.accept(this);

        return null;
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
    public SymbolTable visit(MethodDeclarationUnordered n) {
        //super.visit(n);
        n.f1.accept(this);

        Method method = new Method(n.f2.f0.toString(), this.currentType);

        this.currentClazz.addMethod(method);
        this.currentMethod = method;
        this.symbols.addClass(this.currentClazz);

        List<Boolean> frameParams = new ArrayList<>();
        // Handle the implicit "this".
        frameParams.add(true);
        // Build the list of method parameters.
        n.f4.accept(this);
        // Process the list of method parameters.
        for (int x = 0; x < this.methodParams; x++) {
            frameParams.add(true);
        }
        this.frames.push(Frame.buildFrame(Memory.addressExists(method.getName()), frameParams));

        // Track formal variables
        this.addFormal(0, "this");
        for (int x = 0; x < this.methodParams; x++) {
            n.f4.accept(this);
            this.addFormal(x + 1, this.varName);
            this.varName = "";
        }
        // Unset the list of method parameters.
        this.methodParams = 0;

        this.currentMethod = null;
        this.currentType = null;

        this.frames.pop();

        return null;
    }

    /**
     * f0 -> FormalParameter()
     * f1 -> ( FormalParameterRest() )*
     */
    @Override
    public SymbolTable visit(FormalParameterList n) {
        this.methodParams = n.f1.size();
        n.f0.accept(this);
        return null;
    }

    /**
     * f0 -> Type()
     * f1 -> Identifier()
     */
    @Override
    public SymbolTable visit(FormalParameter n) {
        //super.visit(n);

        Method method = this.currentClazz.getMethodByName(this.currentMethod.getName());
        n.f0.accept(this);
        VarBuilder vb = new VarBuilder();
        //method.addParameter(new Variable(n.f1.f0.toString(), this.currentType));

        this.currentClazz.addMethod(method);

        this.put(this.currentClazz);

        this.currentType = null;

        n.f1.accept(this);

        return this.symbols;
    }

    /**
     * f0 -> <IDENTIFIER>
     */
    @Override
    public SymbolTable visit(Identifier n) {
        this.varName = n.f0.toString();

        return null;
    }

    private Access addFormal(int location, String id) {
        Access var = this.frames.peek().getFormal(location);
        this.symbols.addClassMethodVar(this.currentClazz, this.currentMethod, var);

        return var;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Clazz> entry : this.symbols.getTable().entrySet()) {
            sb.append(entry.getValue()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
