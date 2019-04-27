/* Generated By:JJTree: Do not edit this line. ASTMethodParams.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTMethodParams extends SimpleNode {
  public ASTMethodParams(int id) {
    super(id);
  }

  public ASTMethodParams(jmm p, int id) {
    super(p, id);
  }

  public String[] getParams(SymbolTable table, String functionName) throws Exception {
      String[] res = new String[children.length];

      for (int i = 0; i < children.length; ++i) {
				SimpleNode n = (SimpleNode) children[i];
				res[i] = n.semanticAnalysis(table,functionName);
			}

      return res;
  }

  public void generateCode(StringBuilder builder, SymbolTable ST) {
    ASTOtherLiteral parentNode = (ASTOtherLiteral) parent;
    if (parentNode.identifier.equals("call")) {
			if (children != null) {
				for (int i = 0; i < children.length; i++) {
          ((SimpleNode) children[i]).generateCode(builder, ST);
				}
			}
		}
	}  
}
/* JavaCC - OriginalChecksum=0d0f34f346a0ba514d6b41b522453a82 (do not edit this line) */
