/* Generated By:JJTree: Do not edit this line. ASTOtherLiteral.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTOtherLiteral extends SimpleNode {
	String type = "";
	String identifier = "";

  public ASTOtherLiteral(int id) {
    super(id);
  }

  public ASTOtherLiteral(jmm p, int id) {
    super(p, id);
  }

  public String toString() {
	switch(type)
	{
		case "[]": return "[...]";
		case "length": return "length";
		case "call": return "call: " + identifier;
	}

	return "";
  }

  public String semanticAnalysis(SymbolTable table, String functionName) throws Exception {
		super.semanticAnalysis(table, functionName);

	if(!(parent instanceof ASTLiteral))
		throw new Exception("Expression " + ((ASTOtherLiteral) parent).type + "found before expression");

	ASTLiteral literal = (ASTLiteral) parent;

	switch(type) {
		case "[]": 
				if(!literal.isArray(table,functionName))
					throw new Exception(literal.identifier + " is not an array.");

				SimpleNode n = (SimpleNode) children[0];

				if(!n.semanticAnalysis(table,functionName).equals("int"))
					throw new Exception("Integer Expression expected inside []");

				return "[]";

		case "length":
				if(!literal.isArray(table,functionName))
							throw new Exception(literal.identifier + " is not an array.");
				
				return "int";

		// case "call": 

	}
			
	return "";
  }

}
/* JavaCC - OriginalChecksum=6a67081bb7fb5f77aa5f878ca7d3dcab (do not edit this line) */
