/* Generated By:JJTree: Do not edit this line. ASTADD.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTADD extends SimpleNode {
  public ASTADD(int id) {
    super(id);
  }

  public ASTADD(jmm p, int id) {
    super(p, id);
  }

  public String toString() {
	  return "+";
  }

  public String semanticAnalysis(SymbolTable table, String functionName) throws Exception {
	String type;

	if (children != null) {
		for (int i = 0; i < children.length; ++i) {
			SimpleNode n = (SimpleNode) children[i];
			if (n != null) {
				type = n.semanticAnalysis(table,functionName);

				if(!type.equals("int"))
					throw new Exception("Line "+ lineNumber +": + expected integer but found " + type);
			}
		}
	}

	return "int";
  }

  public void generateCode(StringBuilder builder, SymbolTable ST, String functionName) {
	builder.append(";;; Line: " + lineNumber + "+ ;;;\n");
	
	((SimpleNode)children[0]).generateFunctionCode(builder, ST, functionName);
	((SimpleNode)children[1]).generateFunctionCode(builder, ST, functionName);
	builder.append("iadd\n");
  }

  public void generateFunctionCode(StringBuilder builder, SymbolTable ST, String functionName) {
	generateCode(builder,ST,functionName);
}

}
/* JavaCC - OriginalChecksum=a27409a1dcc9e09ff4c35def8683db96 (do not edit this line) */
