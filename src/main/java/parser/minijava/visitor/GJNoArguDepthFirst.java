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
public class GJNoArguDepthFirst<R> implements GJNoArguVisitor<R> {
   //
   // Auto class visitors--probably don't need to be overridden.
   //
   public R visit(NodeList n) {
      R _ret = null;
      int _count = 0;
      for (Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this);
         _count++;
      }
      return _ret;
   }

   public R visit(NodeListOptional n) {
      if (n.present()) {
         R _ret = null;
         int _count = 0;
         for (Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
            e.nextElement().accept(this);
            _count++;
         }
         return _ret;
      } else
         return null;
   }

   public R visit(NodeOptional n) {
      if (n.present())
         return n.node.accept(this);
      else
         return null;
   }

   public R visit(NodeSequence n) {
      R _ret = null;
      int _count = 0;
      for (Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this);
         _count++;
      }
      return _ret;
   }

   public R visit(NodeToken n) {
      return null;
   }

   //
   // User-generated visitor methods below
   //

   /**
    * f0 -> MainClass()
    * f1 -> ( TypeDeclarationUnordered() )*
    * f2 -> <EOF>
    */
   public R visit(MJProgram n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      return _ret;
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
   public R visit(MainClass n) {
      R _ret = null;
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
      return _ret;
   }

   /**
    * f0 -> ClassDeclaration()
    * | ClassExtendsDeclaration()
    */
   public R visit(TypeDeclarationUnordered n) {
      R _ret = null;
      n.f0.accept(this);
      return _ret;
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
   public R visit(ClassDeclaration n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      n.f4.accept(this);
      n.f5.accept(this);
      n.f6.accept(this);
      return _ret;
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
   public R visit(ClassExtendsDeclaration n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      n.f4.accept(this);
      n.f5.accept(this);
      n.f6.accept(this);
      n.f7.accept(this);
      n.f8.accept(this);
      return _ret;
   }

   /**
    * f0 -> Type()
    * f1 -> Identifier()
    * f2 -> <SEMICOLON>
    */
   public R visit(VarDeclarationUnordered n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      return _ret;
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
   public R visit(MethodDeclarationUnordered n) {
      R _ret = null;
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
      return _ret;
   }

   /**
    * f0 -> FormalParameter()
    * f1 -> ( FormalParameterRest() )*
    */
   public R visit(FormalParameterList n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * f0 -> Type()
    * f1 -> Identifier()
    */
   public R visit(FormalParameter n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * f0 -> <COMMA>
    * f1 -> FormalParameter()
    */
   public R visit(FormalParameterRest n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * f0 -> ArrayType()
    * | BooleanType()
    * | IntegerType()
    * | Identifier()
    */
   public R visit(Type n) {
      R _ret = null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * f0 -> <INTEGER>
    * f1 -> <LSQPAREN>
    * f2 -> <RSQPAREN>
    */
   public R visit(ArrayType n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      return _ret;
   }

   /**
    * f0 -> <BOOLEAN>
    */
   public R visit(BooleanType n) {
      R _ret = null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * f0 -> <INTEGER>
    */
   public R visit(IntegerType n) {
      R _ret = null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * f0 -> Block()
    * | AssignmentStatement()
    * | ArrayAssignmentStatement()
    * | BranchStatement()
    * | WhileStatement()
    * | PrintStatement()
    */
   public R visit(Statement n) {
      R _ret = null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * f0 -> <LBRACE>
    * f1 -> ( Statement() )*
    * f2 -> <RBRACE>
    */
   public R visit(Block n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> <ASSIGN>
    * f2 -> Expression()
    * f3 -> <SEMICOLON>
    */
   public R visit(AssignmentStatement n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      return _ret;
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
   public R visit(ArrayAssignmentStatement n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      n.f4.accept(this);
      n.f5.accept(this);
      n.f6.accept(this);
      return _ret;
   }

   /**
    * f0 -> <IF> <LPAREN> Expression() <RPAREN> <LBRACE> Statement() <RBRACE>
    * | <ELSE_IF> <LPAREN> Expression() <RPAREN> <LBRACE> Statement() <RBRACE>
    * | <ELSE> <LBRACE> Statement() <RBRACE>
    */
   public R visit(BranchStatement n) {
      R _ret = null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * f0 -> <WHILE>
    * f1 -> <LPAREN>
    * f2 -> Expression()
    * f3 -> <RPAREN>
    * f4 -> Statement()
    */
   public R visit(WhileStatement n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      n.f4.accept(this);
      return _ret;
   }

   /**
    * f0 -> <PRINT>
    * f1 -> <LPAREN>
    * f2 -> Expression()
    * f3 -> <RPAREN>
    * f4 -> <SEMICOLON>
    */
   public R visit(PrintStatement n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      n.f4.accept(this);
      return _ret;
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
   public R visit(Expression n) {
      R _ret = null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> <AND>
    * f2 -> PrimaryExpression()
    */
   public R visit(AndExpression n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> <LT>
    * f2 -> PrimaryExpression()
    */
   public R visit(CompareExpression n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> <PLUS>
    * f2 -> PrimaryExpression()
    */
   public R visit(PlusExpression n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> <MINUS>
    * f2 -> PrimaryExpression()
    */
   public R visit(MinusExpression n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> <MULTIPLY>
    * f2 -> PrimaryExpression()
    */
   public R visit(TimesExpression n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> <LSQPAREN>
    * f2 -> PrimaryExpression()
    * f3 -> <RSQPAREN>
    */
   public R visit(ArrayLookup n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> <DOT>
    * f2 -> <LENGTH>
    */
   public R visit(ArrayLength n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> <DOT>
    * f2 -> Identifier()
    * f3 -> <LPAREN>
    * f4 -> ( ExpressionList() )?
    * f5 -> <RPAREN>
    */
   public R visit(MessageSend n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      n.f4.accept(this);
      n.f5.accept(this);
      return _ret;
   }

   /**
    * f0 -> Expression()
    * f1 -> ( ExpressionRest() )*
    */
   public R visit(ExpressionList n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * f0 -> <COMMA>
    * f1 -> Expression()
    */
   public R visit(ExpressionRest n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
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
   public R visit(PrimaryExpression n) {
      R _ret = null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * f0 -> <INTEGER_LITERAL>
    */
   public R visit(IntegerLiteral n) {
      R _ret = null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * f0 -> <TRUE>
    */
   public R visit(TrueLiteral n) {
      R _ret = null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * f0 -> <FALSE>
    */
   public R visit(FalseLiteral n) {
      R _ret = null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * f0 -> <IDENTIFIER>
    */
   public R visit(Identifier n) {
      R _ret = null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * f0 -> <THIS>
    */
   public R visit(ThisExpression n) {
      R _ret = null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * f0 -> <NEW>
    * f1 -> <INTEGER>
    * f2 -> <LSQPAREN>
    * f3 -> Expression()
    * f4 -> <RSQPAREN>
    */
   public R visit(ArrayAllocationExpression n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      n.f4.accept(this);
      return _ret;
   }

   /**
    * f0 -> <NEW>
    * f1 -> Identifier()
    * f2 -> <LPAREN>
    * f3 -> <RPAREN>
    */
   public R visit(AllocationExpression n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      return _ret;
   }

   /**
    * f0 -> <NOT>
    * f1 -> Expression()
    */
   public R visit(NotExpression n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * f0 -> <LPAREN>
    * f1 -> Expression()
    * f2 -> <RPAREN>
    */
   public R visit(ParenthesisExpression n) {
      R _ret = null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      return _ret;
   }

   /**
    * f0 -> <SINGLE_LINE_COMMENT>
    * | <FORMAL_COMMENT>
    * | <MULTI_LINE_COMMENT>
    */
   public R visit(Comment n) {
      R _ret = null;
      n.f0.accept(this);
      return _ret;
   }

}
