package org.archphantom.shenanigans.elements.expressions.operations;
import org.archphantom.shenanigans.elements.Program;
import org.archphantom.shenanigans.elements.expressions.Expression;
import org.archphantom.shenanigans.elements.values.IntValue;
import org.archphantom.shenanigans.elements.values.Value;
import org.archphantom.shenanigans.elements.variables.VarTable;

public class BitAnd extends Expression {
	private static final long serialVersionUID = 628380389054082502L;
	private Expression a, b;
	
	public BitAnd (Expression a, Expression b) {
		this.a = a;
		this.b = b;
	}
	
	public IntValue evaluate (VarTable vars, Program program) {
		Value aval = a.evaluate(vars, program);
		Value bval = b.evaluate(vars, program);
		if (!(aval instanceof IntValue) || !(bval instanceof IntValue)) {
			throw new RuntimeException("Not Implemented");
		}
		return new IntValue ((((IntValue) aval).intValue(vars, program)).add(((IntValue) bval).intValue(vars, program)));
	}
	
}
