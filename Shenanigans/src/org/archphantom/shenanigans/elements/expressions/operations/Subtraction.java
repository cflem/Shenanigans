package org.archphantom.shenanigans.elements.expressions.operations;
import org.archphantom.shenanigans.elements.Program;
import org.archphantom.shenanigans.elements.expressions.Expression;
import org.archphantom.shenanigans.elements.values.DecimalValue;
import org.archphantom.shenanigans.elements.values.Value;
import org.archphantom.shenanigans.elements.variables.VarTable;

public class Subtraction extends Expression {
	private static final long serialVersionUID = 7561035138901827673L;
	private Expression a, b;
	
	public Subtraction (Expression a, Expression b) {
		this.a = a;
		this.b = b;
	}
	
	public DecimalValue evaluate (VarTable vars, Program program) {
		Value aval = a.evaluate(vars, program);
		Value bval = b.evaluate(vars, program);
		if (!(aval instanceof DecimalValue) || !(bval instanceof DecimalValue)) {
			throw new RuntimeException("Not Implemented");
		}
		return new DecimalValue ((((DecimalValue) aval).getValue(vars, program)).subtract(((DecimalValue) bval).getValue(vars, program)));
	}
	
}
