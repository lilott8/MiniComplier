//
// Generated by JTB 1.3.2
//

package parser.minijava.visitor;

import java.util.Enumeration;

import parser.minijava.ast.AllocationExpression;
import parser.minijava.ast.AndExpression;
import parser.minijava.ast.ArrayAllocationExpression;
import parser.minijava.ast.ArrayAssignmentStatement;
import parser.minijava.ast.ArrayLength;
import parser.minijava.ast.ArrayLookup;
import parser.minijava.ast.ArrayType;
import parser.minijava.ast.AssignmentStatement;
import parser.minijava.ast.Block;
import parser.minijava.ast.BooleanType;
import parser.minijava.ast.BranchStatement;
import parser.minijava.ast.ClassDeclaration;
import parser.minijava.ast.ClassExtendsDeclaration;
import parser.minijava.ast.Comment;
import parser.minijava.ast.CompareExpression;
import parser.minijava.ast.Expression;
import parser.minijava.ast.ExpressionList;
import parser.minijava.ast.ExpressionRest;
import parser.minijava.ast.FalseLiteral;
import parser.minijava.ast.FormalParameter;
import parser.minijava.ast.FormalParameterList;
import parser.minijava.ast.FormalParameterRest;
import parser.minijava.ast.Identifier;
import parser.minijava.ast.IntegerLiteral;
import parser.minijava.ast.IntegerType;
import parser.minijava.ast.MJProgram;
import parser.minijava.ast.MainClass;
import parser.minijava.ast.MessageSend;
import parser.minijava.ast.MethodDeclarationUnordered;
import parser.minijava.ast.MinusExpression;
import parser.minijava.ast.Node;
import parser.minijava.ast.NodeList;
import parser.minijava.ast.NodeListOptional;
import parser.minijava.ast.NodeOptional;
import parser.minijava.ast.NodeSequence;
import parser.minijava.ast.NodeToken;
import parser.minijava.ast.NotExpression;
import parser.minijava.ast.ParenthesisExpression;
import parser.minijava.ast.PlusExpression;
import parser.minijava.ast.PrimaryExpression;
import parser.minijava.ast.PrintStatement;
import parser.minijava.ast.Statement;
import parser.minijava.ast.ThisExpression;
import parser.minijava.ast.TimesExpression;
import parser.minijava.ast.TrueLiteral;
import parser.minijava.ast.Type;
import parser.minijava.ast.TypeDeclarationUnordered;
import parser.minijava.ast.VarDeclarationUnordered;
import parser.minijava.ast.WhileStatement;

/**
 * Provides default methods which visit each node in the tree in depth-first
 * order.  Your visitors may extend this class.
 */
public class GJVoidDepthFirst<A> implements GJVoidVisitor<A> {
   //
   // Auto class visitors--probably don't need to be overridden.
   //
   public void visit(NodeList n, A argu) {
      int _count = 0;
      for (Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this, argu);
         _count++;
      }
   }

   public void visit(NodeListOptional n, A argu) {
      if (n.present()) {
         int _count = 0;
         for (Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
            e.nextElement().accept(this, argu);
            _count++;
         }
      }
   }

   public void visit(NodeOptional n, A argu) {
      if (n.present())
         n.node.accept(this, argu);
   }

   public void visit(NodeSequence n, A argu) {
      int _count = 0;
      for (Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this, argu);
         _count++;
      }
   }

   public void visit(NodeToken n, A argu) {
   }

   //
   // User-generated visitor methods below
   //

   /**
    * f0 -> MainClass()
    * f1 -> ( TypeDeclarationUnordered() )*
    * f2 -> <EOF>
    */
   public void visit(MJProgram n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
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
   public void visit(MainClass n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
      n.f7.accept(this, argu);
      n.f8.accept(this, argu);
      n.f9.accept(this, argu);
      n.f10.accept(this, argu);
      n.f11.accept(this, argu);
      n.f12.accept(this, argu);
      n.f13.accept(this, argu);
      n.f14.accept(this, argu);
      n.f15.accept(this, argu);
      n.f16.accept(this, argu);
      n.f17.accept(this, argu);
   }

   /**
    * f0 -> ClassDeclaration()
    * | ClassExtendsDeclaration()
    */
   public void visit(TypeDeclarationUnordered n, A argu) {
      n.f0.accept(this, argu);
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
   public void visit(ClassDeclaration n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
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
   public void visit(ClassExtendsDeclaration n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
      n.f7.accept(this, argu);
      n.f8.accept(this, argu);
   }

   /**
    * f0 -> Type()
    * f1 -> Identifier()
    * f2 -> <SEMICOLON>
    */
   public void visit(VarDeclarationUnordered n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
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
   public void visit(MethodDeclarationUnordered n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
      n.f7.accept(this, argu);
      n.f8.accept(this, argu);
      n.f9.accept(this, argu);
      n.f10.accept(this, argu);
      n.f11.accept(this, argu);
      n.f12.accept(this, argu);
      n.f13.accept(this, argu);
   }

   /**
    * f0 -> FormalParameter()
    * f1 -> ( FormalParameterRest() )*
    */
   public void visit(FormalParameterList n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> Type()
    * f1 -> Identifier()
    */
   public void visit(FormalParameter n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> <COMMA>
    * f1 -> FormalParameter()
    */
   public void visit(FormalParameterRest n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> ArrayType()
    * | BooleanType()
    * | IntegerType()
    * | Identifier()
    */
   public void visit(Type n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> <INTEGER>
    * f1 -> <LSQPAREN>
    * f2 -> <RSQPAREN>
    */
   public void visit(ArrayType n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> <BOOLEAN>
    */
   public void visit(BooleanType n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> <INTEGER>
    */
   public void visit(IntegerType n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> Block()
    * | AssignmentStatement()
    * | ArrayAssignmentStatement()
    * | BranchStatement()
    * | WhileStatement()
    * | PrintStatement()
    */
   public void visit(Statement n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> <LBRACE>
    * f1 -> ( Statement() )*
    * f2 -> <RBRACE>
    */
   public void visit(Block n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> Identifier()
    * f1 -> <ASSIGN>
    * f2 -> Expression()
    * f3 -> <SEMICOLON>
    */
   public void visit(AssignmentStatement n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
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
   public void visit(ArrayAssignmentStatement n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
   }

   /**
    * f0 -> <IF> <LPAREN> Expression() <RPAREN> <LBRACE> Statement() <RBRACE>
    * | <ELSE_IF> <LPAREN> Expression() <RPAREN> <LBRACE> Statement() <RBRACE>
    * | <ELSE> <LBRACE> Statement() <RBRACE>
    */
   public void visit(BranchStatement n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> <WHILE>
    * f1 -> <LPAREN>
    * f2 -> Expression()
    * f3 -> <RPAREN>
    * f4 -> Statement()
    */
   public void visit(WhileStatement n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
   }

   /**
    * f0 -> <PRINT>
    * f1 -> <LPAREN>
    * f2 -> Expression()
    * f3 -> <RPAREN>
    * f4 -> <SEMICOLON>
    */
   public void visit(PrintStatement n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
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
   public void visit(Expression n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> <AND>
    * f2 -> PrimaryExpression()
    */
   public void visit(AndExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> <LT>
    * f2 -> PrimaryExpression()
    */
   public void visit(CompareExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> <PLUS>
    * f2 -> PrimaryExpression()
    */
   public void visit(PlusExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> <MINUS>
    * f2 -> PrimaryExpression()
    */
   public void visit(MinusExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> <MULTIPLY>
    * f2 -> PrimaryExpression()
    */
   public void visit(TimesExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> <LSQPAREN>
    * f2 -> PrimaryExpression()
    * f3 -> <RSQPAREN>
    */
   public void visit(ArrayLookup n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> <DOT>
    * f2 -> <LENGTH>
    */
   public void visit(ArrayLength n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> <DOT>
    * f2 -> Identifier()
    * f3 -> <LPAREN>
    * f4 -> ( ExpressionList() )?
    * f5 -> <RPAREN>
    */
   public void visit(MessageSend n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
   }

   /**
    * f0 -> Expression()
    * f1 -> ( ExpressionRest() )*
    */
   public void visit(ExpressionList n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> <COMMA>
    * f1 -> Expression()
    */
   public void visit(ExpressionRest n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
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
   public void visit(PrimaryExpression n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> <INTEGER_LITERAL>
    */
   public void visit(IntegerLiteral n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> <TRUE>
    */
   public void visit(TrueLiteral n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> <FALSE>
    */
   public void visit(FalseLiteral n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> <IDENTIFIER>
    */
   public void visit(Identifier n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> <THIS>
    */
   public void visit(ThisExpression n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> <NEW>
    * f1 -> <INTEGER>
    * f2 -> <LSQPAREN>
    * f3 -> Expression()
    * f4 -> <RSQPAREN>
    */
   public void visit(ArrayAllocationExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
   }

   /**
    * f0 -> <NEW>
    * f1 -> Identifier()
    * f2 -> <LPAREN>
    * f3 -> <RPAREN>
    */
   public void visit(AllocationExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
   }

   /**
    * f0 -> <NOT>
    * f1 -> Expression()
    */
   public void visit(NotExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> <LPAREN>
    * f1 -> Expression()
    * f2 -> <RPAREN>
    */
   public void visit(ParenthesisExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> <SINGLE_LINE_COMMENT>
    * | <FORMAL_COMMENT>
    * | <MULTI_LINE_COMMENT>
    */
   public void visit(Comment n, A argu) {
      n.f0.accept(this, argu);
   }

}
