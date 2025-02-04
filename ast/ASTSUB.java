/* Generated By:JJTree: Do not edit this line. ASTSUB.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package ast;

import jmm.*;

public
class ASTSUB extends SimpleNode {
  public ASTSUB(int id) {
    super(id);
  }

  public ASTSUB(jmm p, int id) {
    super(p, id);
  }

  public String toString() {
	return "-";
  }

  public String semanticAnalysis(SymbolTable table, String functionName) throws Exception {
	String type;

	if (children != null) {
		for (int i = 0; i < children.length; ++i) {
			SimpleNode n = (SimpleNode) children[i];
			if (n != null) {
				type = n.semanticAnalysis(table,functionName);

				if(!type.equals("int") && !type.equals(""))
					throw new Exception("- expected integer but found " + type);
			}
		}
	}

	return "int";
  }

  public void generateCode(StringBuilder builder, SymbolTable ST, String functionName) {
	((SimpleNode)children[0]).generateFunctionCode(builder, ST, functionName);
	((SimpleNode)children[1]).generateFunctionCode(builder, ST, functionName);
	builder.append("isub\n");
  }

  public void generateFunctionCode(StringBuilder builder, SymbolTable ST, String functionName) {
	generateCode(builder,ST,functionName);
}
}
/* JavaCC - OriginalChecksum=353cd3b7689ade7340f0274f1f92fac7 (do not edit this line) */
