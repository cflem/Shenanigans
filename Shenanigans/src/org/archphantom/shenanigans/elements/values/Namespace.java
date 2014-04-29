package org.archphantom.shenanigans.elements.values;
import org.archphantom.shenanigans.elements.Program;
import org.archphantom.shenanigans.elements.expressions.Expression;
import org.archphantom.shenanigans.elements.variables.VarTable;

public class Namespace extends Value {
	private String name;
	private Expression body;
	private VarTable myvars;
	
	public Namespace (String name, Expression body, VarTable myvars) {
		this.name = name;
		this.body = body;
		this.myvars = myvars;
	}
	
	public String getName () {
		return name;
	}
	
	public Expression getVar (String identifier, VarTable vars, Program program) {
		if (identifier.contains(".")) {
			String nspacecall = identifier.substring(0, identifier.indexOf('.'));
			String varname = identifier.substring(identifier.indexOf('.')+1);
			Value vns;
			if ((vns = myvars.get(nspacecall).evaluate(vars, program)) != null) {
				if (vns instanceof Namespace) {
					Namespace ns = (Namespace) vns;
					return ns.getVar(varname, vars, program);
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return myvars.get(identifier);
		}
	}
	
	public Value setVar (String identifier, Expression value, VarTable vars, Program program) {
		if (identifier.contains(".")) {
			String nspacecall = identifier.substring(0, identifier.indexOf('.'));
			String varname = identifier.substring(identifier.indexOf('.')+1);
			Value vns;
			if ((vns = myvars.get(nspacecall).evaluate(vars, program)) != null) {
				if (vns instanceof Namespace) {
					Namespace ns = (Namespace) vns;
					return ns.setVar(varname, value, vars, program);
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return vars.get(identifier).evaluate(vars, program);
		}
	}
	
	public Value getValue (VarTable vars, Program program) {
		System.out.println("Running namespace's body...");
		return body.evaluate(myvars, program);
	}
	
}