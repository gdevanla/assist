package com.ser.assist.oraclefinder;

import soot.Body;
import soot.Local;
import soot.Unit;
import soot.ValueBox;
import soot.jimple.AssignStmt;
import soot.jimple.InvokeExpr;
import soot.jimple.StaticInvokeExpr;
import soot.jimple.Stmt;
import soot.jimple.internal.JimpleLocal;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.graph.pdg.EnhancedUnitGraph;
import soot.toolkits.scalar.SimpleLocalDefs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


class UnitWrapper{
    public final Unit u;
    public final UnitWrapper reachingUseNode;

    public UnitWrapper(Unit u, UnitWrapper parentNode){
        this.u = u;
        this.reachingUseNode = parentNode;
    }

    public void printCallTree(){
        if (this.reachingUseNode==null) return;
        System.out.println(this.u);
        this.reachingUseNode.printCallTree();
    }

    public String getCallTree(){
        if (reachingUseNode == null){
            return ""; //this will be the actual assert statement. #bad design, sorry!
        }
        String s = this.reachingUseNode.getCallTree();
        if (!((Stmt)u).containsInvokeExpr()){
            return s;
        }
        else
        {
            if ( s.length() > 0){
                return ((Stmt)this.u).getInvokeExpr().getMethod().getName() + "." + s;
            }
            else{
                return ((Stmt)this.u).getInvokeExpr().getMethod().getName() + "()";
            }

        }
    }


}

public class AssertOnReturnObjectsMethod extends AbstractOracleFinder {

    public AssertOnReturnObjectsMethod(Body body, String mutSignature){
        super(body, mutSignature);
    }


    public List<Oracle> getAllOccurences() throws Exception {
        UnitGraph cfg = new EnhancedUnitGraph(body);
        SimpleLocalDefs simpleLocalDefs = new SimpleLocalDefs(cfg);

        LinkedList<Oracle> oraclesFound = new LinkedList<Oracle>();
        System.out.println("getAllOccurences="+body.getMethod().getName());
        //SimpleLocalUses localUses = new SimpleLocalUses(cfg, localVarDefs);
        List<UnitWrapper> uws = new ArrayList<UnitWrapper>();
        for (Unit unit: body.getUnits()){

            Stmt stmt = (Stmt)unit;
            if (!stmt.containsInvokeExpr()) continue;

            InvokeExpr expr = stmt.getInvokeExpr();
            if (!expr.getMethod().getName().startsWith("assert")) continue;


            UnitWrapper uw = new UnitWrapper(unit, null);

            System.out.println("Collecting oracles from method="+body.getMethod().getName());
            collectAllMethodCallChains(simpleLocalDefs, uw, uws);
        }

        for (UnitWrapper uw:uws){
            System.out.println("Adding oracle with following trace:");
            uw.printCallTree();
            System.out.println("Here is the call tree="+uw.reachingUseNode.getCallTree());
            //oraclesFound.add(new Oracle(Oracle.OraclePattern.ASSERT_MODIFIED_OBJECTS_VALUE,
            //        new Assertion(unit), this.mutSignature));
        }

        return oraclesFound;
    }

    public void collectAllMethodCallChains(SimpleLocalDefs simpleLocalDefs,
                                                   UnitWrapper uw, List<UnitWrapper> uws) throws Exception {

        if (haveWeReachedTheMUTYet(uw.u)){
            System.out.println("Reached Method with"+uw.u);
            uws.add(uw);
            return;
        }

        //special handling for first node, which is an assert node
        Local local;
        if ( uw.reachingUseNode == null){
            local = getLocalUsedInAssert(uw.u);
        }
        else
        {
            //for all other units, check if it makes sense to proceed
            if (hasInvokeExprsWithArgs(uw.u) || !isSimpleAssignment(uw.u)
                    || isStaticInvokeExpression(uw.u))
            {
                System.out.println("Continuing...");
                return;
            }

            List<ValueBox> useBoxes = uw.u.getUseBoxes();
            if ( useBoxes.size() > 2){
                System.out.println("UseBoxes"+useBoxes);
                System.out.println("Failing for unit"+uw.u);
                throw new Exception("This method does not support a unit having more than one use box");
            }

            if (useBoxes.size()==0) {
                System.out.println("UseBox size was 0");
                return;
            }

            System.out.println("getting local from usebox="+useBoxes.get(0));
            local = (Local)useBoxes.get(0).getValue();
        }

        if (simpleLocalDefs.hasDefsAt(local, uw.u) ){

            List<Unit> localDefs = simpleLocalDefs.getDefsOfAt(local, uw.u);
            for (Unit u:localDefs){
                //System.out.print("Found localDefs for local="+local+"at"+u);

                UnitWrapper nextUw = new UnitWrapper(u, uw);
                collectAllMethodCallChains(simpleLocalDefs, nextUw, uws);
            }
        }

        return;
    }

    private boolean isStaticInvokeExpression(Unit u) {
        if (((Stmt)u).containsInvokeExpr() &&
                u instanceof StaticInvokeExpr)
            return true;

        return false;
    }


}
