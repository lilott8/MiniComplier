package parser.minijava.semantics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

import enums.Scope;
import enums.Types;
import parser.minijava.ast.ArrayType;
import parser.minijava.ast.BooleanType;
import parser.minijava.ast.ClassDeclaration;
import parser.minijava.ast.ClassExtendsDeclaration;
import parser.minijava.ast.FormalParameter;
import parser.minijava.ast.FormalParameterList;
import parser.minijava.ast.IntegerType;
import parser.minijava.ast.MainClass;
import parser.minijava.ast.MethodDeclarationUnordered;
import parser.minijava.ast.NodeChoice;
import parser.minijava.ast.VarDeclarationUnordered;
import parser.minijava.visitor.DepthFirstVisitor;
import shared.Phase;
import symboltable.Clazz;
import symboltable.Method;
import symboltable.Symbol;
import symboltable.SymbolTable;
import symboltable.Type;
import symboltable.Variable;

/**
 * @created: 11/30/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MJSymbolTable extends DepthFirstVisitor implements SymbolTable, Phase {

    private Map<String, Symbol> symbolTable = new LinkedHashMap<>();
    private Method currentMethod;
    private Clazz currentClazz;
    private Type currentType;

    public static final Logger logger = LogManager.getLogger(MJSymbolTable.class);

    public MJSymbolTable() {
    }

    @Override
    public void buildTable() {

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
        this.symbolTable.put(symbol.getName(), symbol);
    }

    public Map<String, Symbol> getSymbolTable() {
        return this.symbolTable;
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
    public void visit(MainClass n) {
        //super.visit(n);

        Clazz mainClass = new Clazz(n.f1.f0.toString(), true);

        //Method mainMethod = new Method(n.f5.toString(), new Type(new NodeChoice(n.f5)), Scope.getScopeFromString(n.f3.toString()));
        NodeChoice methodType = new NodeChoice(n.f5);
        Method mainMethod = new Method(n.f5.toString(), new Type(methodType.toString()), Scope.getScopeFromString(n.f3.toString()));
        //Variable argument = new Variable(n.f11.f0.toString(), new Type(new NodeChoice(n.f8)));
        Variable argument = new Variable(n.f11.f0.toString(), new Type(Types.STRING));
        mainMethod.addParameter(argument);
        mainClass.addMethod(mainMethod);
        this.currentClazz = mainClass;
        this.currentMethod = mainMethod;
        this.put(currentClazz);

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
    public void visit(ClassDeclaration n) {
        //super.visit(n);

        Clazz clazz = new Clazz(n.f1.f0.toString());
        this.currentClazz = clazz;
        this.put(clazz);

        n.f0.accept(this);
        n.f1.accept(this);
        n.f2.accept(this);
        n.f3.accept(this);
        n.f4.accept(this);
        n.f5.accept(this);
        n.f6.accept(this);

        this.currentClazz = null;
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
    public void visit(ClassExtendsDeclaration n) {
        //super.visit(n);

        Clazz clazz = new Clazz(n.f1.f0.toString());
        Clazz extending = (Clazz) this.symbolTable.get(n.f3.f0.toString());

        for (Map.Entry<String, Variable> entry : extending.getLocals().entrySet()) {
            clazz.addVariable(entry.getValue());
        }

        for (Map.Entry<String, Method> entry : extending.getMethods().entrySet()) {
            clazz.addMethod(entry.getValue());
        }
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
    }

    /**
     * f0 -> ArrayType()
     * | BooleanType()
     * | IntegerType()
     * | Identifier()
     */
    @Override
    public void visit(parser.minijava.ast.Type n) {
        //this.currentType = new Type(n.f0.choice.toString());
        n.f0.accept(this);
    }

    /**
     * f0 -> <INTEGER>
     * f1 -> <LSQPAREN>
     * f2 -> <RSQPAREN>
     */
    @Override
    public void visit(ArrayType n) {
        this.currentType = new Type(Types.ARRAY);

        n.f0.accept(this);
        n.f1.accept(this);
        n.f2.accept(this);
    }

    /**
     * f0 -> <BOOLEAN>
     */
    @Override
    public void visit(BooleanType n) {
        this.currentType = new Type(Types.BOOL);
        n.f0.accept(this);
    }

    /**
     * f0 -> <INTEGER>
     */
    @Override
    public void visit(IntegerType n) {
        this.currentType = new Type(Types.INT);
        n.f0.accept(this);
    }



    /**
     * f0 -> Type()
     * f1 -> Identifier()
     * f2 -> <SEMICOLON>
     */
    @Override
    public void visit(VarDeclarationUnordered n) {
        //super.visit(n);
        n.f0.accept(this);

        if (currentMethod != null) {
            this.currentMethod.addLocal(new Variable(n.f1.f0.toString(), this.currentType));
            this.currentClazz.addMethod(this.currentMethod);
        } else {
            this.currentClazz.addVariable(new Variable(n.f1.f0.toString(), this.currentType));
        }

        this.put(this.currentClazz);

        this.currentType = null;

        n.f1.accept(this);
        n.f2.accept(this);
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
    public void visit(MethodDeclarationUnordered n) {
        //super.visit(n);
        n.f1.accept(this);

        Method method = new Method(n.f2.f0.toString(), this.currentType);

        this.currentClazz.addMethod(method);
        currentMethod = method;
        this.put(this.currentClazz);

        n.f0.accept(this);
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

        this.currentMethod = null;
        this.currentType = null;

    }

    /**
     * f0 -> FormalParameter()
     * f1 -> ( FormalParameterRest() )*
     */
    @Override
    public void visit(FormalParameterList n) {
        super.visit(n);
    }

    /**
     * f0 -> Type()
     * f1 -> Identifier()
     */
    @Override
    public void visit(FormalParameter n) {
        //super.visit(n);

        Method method = this.currentClazz.getMethodByName(this.currentMethod.getName());
        n.f0.accept(this);
        method.addParameter(new Variable(n.f1.f0.toString(), this.currentType));

        this.currentClazz.addMethod(method);

        this.put(this.currentClazz);

        this.currentType = null;

        n.f1.accept(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Symbol> entry : this.symbolTable.entrySet()) {
            sb.append(entry.getValue()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
