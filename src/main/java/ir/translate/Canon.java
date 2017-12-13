package ir.translate;

import java.util.ArrayList;
import java.util.List;

import enums.IROpCodes;
import ir.memory.Memory;
import ir.memory.Register;
import ir.simulate.frames.FrameSimulate;
import ir.tree.expression.Call;
import ir.tree.expression.Constant;
import ir.tree.expression.Expression;
import ir.tree.expression.ExpressionSequence;
import ir.tree.expression.Name;
import ir.tree.expression.Temp;
import ir.tree.statement.Evaluate;
import ir.tree.statement.Move;
import ir.tree.statement.Sequence;
import ir.tree.statement.Statement;

/**
 * @created: 12/12/17
 * @since: 0.1
 * @project: minicomplier
 */
class MoveCall extends Statement {
    final Temp dst;
    final Call src;

    MoveCall(Temp d, Call s) {
        dst = d;
        src = s;
    }

    @Override
    public List<Expression> expressions() {
        List<Expression> list = new ArrayList<>();
        list.add(src);
        list.add(dst);
        return list;
    }

    /**
     * If this statement is a kind of JUMP, then this method should be
     * implemented and it should return a List of possible jump targets.
     * <p>
     * This method is used by the TraceScheduling algorithm. The order
     * in which the jumptargets are retur
     */
    @Override
    public List<Memory> getJumpTargets() {
        return null;
    }

    @Override
    public String getIRName() {
        return "MoveCall";
    }

    @Override
    public IROpCodes getIROpCode() {
        return IROpCodes.MOVE;
    }

    @Override
    public Statement build(List<Expression> kids) {
        return new Move(dst, src.build(kids));
    }

    @Override
    public Memory simulate(FrameSimulate env) {
        // No need to implement this. This is a temporary class
        // its instances will be replaced / expanded by the end of
        // the canonicalisation process.
        throw new Error("Not implemented");
    }

}

class ExpCall extends Statement {
    final Call call;

    ExpCall(Call c) {
        call = c;
    }

    public List<Expression> kids() {
        return call.getSubExpressions();
    }

    public Statement build(List<Expression> kids) {
        return new Evaluate(call.build(kids));
    }

    @Override
    public Memory simulate(FrameSimulate env) {
        // No need to implement this. This is a temporary class ...
        throw new Error("Not implemented");
    }

    @Override
    public List<Expression> expressions() {
        return call.getSubExpressions();
    }

    /**
     * If this statement is a kind of JUMP, then this method should be
     * implemented and it should return a List of possible jump targets.
     * <p>
     * This method is used by the TraceScheduling algorithm. The order
     * in which the jumptargets are retur
     */
    @Override
    public List<Memory> getJumpTargets() {
        return null;
    }

    @Override
    public String getIRName() {
        return "EvaluateCall";
    }

    @Override
    public IROpCodes getIROpCode() {
        return IROpCodes.EVALUATE;
    }
}

class StmExpList {
    Statement stm;
    List<Expression> exps;

    StmExpList(Statement s, List<Expression> e) {
        stm = s;
        exps = e;
    }
}

public class Canon {

    private static final List<Statement> nullStmList = new ArrayList<>();
    private static final List<Expression> nullExpList = new ArrayList<>();
    static StmExpList nopNull = new StmExpList(new Evaluate(new Constant(0)), nullExpList);

    static boolean isNop(Statement a) {
        return a instanceof Evaluate
                && ((Evaluate) a).expression instanceof Constant;
    }

    static Statement seq(Statement a, Statement b) {
        if (isNop(a)) return b;
        else if (isNop(b)) return a;
        else return new Sequence(a, b);
    }

    static boolean commute(Statement a, Expression b) {
        return isNop(a)
                || b instanceof Name
                || b instanceof Constant;
    }

    static Statement do_stm(Sequence s) {
        return seq(do_stm(s.left), do_stm(s.right));
    }

    static Statement do_stm(Move s) {
        if (s.getDestination() instanceof Temp
                && s.getSource() instanceof Call)
            return reorder_stm(new MoveCall((Temp) s.getDestination(),
                    (Call) s.getSource()));
        else if (s.getDestination() instanceof ExpressionSequence)
            return do_stm(new Sequence(((ExpressionSequence) s.getDestination()).statement,
                    new Move(((ExpressionSequence) s.getDestination()).expression,
                            s.getSource())));
        else return reorder_stm(s);
    }

    static Statement do_stm(Evaluate s) {
        if (s.expression instanceof Call)
            return reorder_stm(new ExpCall((Call) s.expression));
        else return reorder_stm(s);
    }

    static Statement do_stm(Statement s) {
        if (s instanceof Sequence) return do_stm((Sequence) s);
        else if (s instanceof Move) return do_stm((Move) s);
        else if (s instanceof Evaluate) return do_stm((Evaluate) s);
        else return reorder_stm(s);
    }

    static Statement reorder_stm(Statement s) {
        StmExpList x = reorder(s.expressions());
        return seq(x.stm, s.build(x.exps));
    }

    static ExpressionSequence do_exp(ExpressionSequence e) {
        Statement stms = do_stm(e.statement);
        ExpressionSequence b = do_exp(e.expression);
        return new ExpressionSequence(seq(stms, b.statement), b.expression);
    }

    static ExpressionSequence do_exp(Expression e) {
        if (e instanceof ExpressionSequence) return do_exp((ExpressionSequence) e);
        else return reorder_exp(e);
    }

    static ExpressionSequence reorder_exp(Expression e) {
        StmExpList x = reorder(e.getSubExpressions());
        return new ExpressionSequence(x.stm, e.build(x.exps));
    }

    static StmExpList reorder(List<Expression> exps) {
        if (exps.isEmpty()) return nopNull;
        else {
            Expression a = exps.get(0);
            if (a instanceof Call) {
                Register register = new Register();
                Expression e = new ExpressionSequence(new Move(new Temp(register), a),
                        new Temp(register));
                List<Expression> list = new ArrayList<>();
                list.add(e);
                list.addAll(exps);
                return reorder(list);
            } else {
                ExpressionSequence aa = do_exp(a);
                StmExpList bb = reorder(exps);
                List<Expression> list = new ArrayList<>();
                if (commute(bb.stm, aa.expression)) {
                    list.add(aa.expression);
                    list.addAll(bb.exps);
                    return new StmExpList(seq(aa.statement, bb.stm),
                            list);
                } else {
                    Register register = new Register();
                    list.add(new Temp(register));
                    list.addAll(bb.exps);
                    return new StmExpList(seq(aa.statement, seq(new Move(new Temp(register), aa.expression), bb.stm)), list);
                }
            }
        }
    }

    static List<Statement> linear(Sequence s, List<Statement> l) {
        return linear(s.left, linear(s.right, l));
    }

    static List<Statement> linear(Statement s, List<Statement> l) {
        if (s instanceof Sequence) {
            return linear((Sequence) s, l);
        } else {
            List<Statement> list = new ArrayList<>();
            list.add(s);
            list.addAll(l);
            return list;
        }
    }

    static public List<Statement> linearize(Statement s) {
        return linear(do_stm(s), nullStmList);
    }
}

