/* Generated By:JJTree: Do not edit this line. ASTMULT.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package ast;

import jmm.*;

public
class ASTMULT extends SimpleNode {
  public ASTMULT(int id) {
    super(id);
  }

  public ASTMULT(jmm p, int id) {
    super(p, id);
  }

  public String toString() {
	return "*";
  }

  public String semanticAnalysis(SymbolTable table, String functionName) throws Exception {
	String type;

	if (children != null) {
		for (int i = 0; i < children.length; ++i) {
			SimpleNode n = (SimpleNode) children[i];
			if (n != null) {
				type = n.semanticAnalysis(table,functionName);

				if(!type.equals("int") && !type.equals(""))
					throw new Exception("* expected integer but found " + type);
			}
		}
	}

	return "int";
  }

  public void generateCode(StringBuilder builder, SymbolTable ST, String functionName) {	
	((SimpleNode)children[0]).generateFunctionCode(builder, ST, functionName);
	((SimpleNode)children[1]).generateFunctionCode(builder, ST, functionName);
	builder.append("imul\n");
  }

  public void generateFunctionCode(StringBuilder builder, SymbolTable ST, String functionName) {
	generateCode(builder,ST,functionName);
}
}
/* JavaCC - OriginalChecksum=a00233398377fabcd1d752fd409bf567 (do not edit this line) */
