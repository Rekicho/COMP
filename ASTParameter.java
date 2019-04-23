import java.util.Hashtable;

/* Generated By:JJTree: Do not edit this line. ASTParameter.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTParameter extends SimpleNode {
  String name = "";
	
  public ASTParameter(int id) {
    super(id);
  }

  public ASTParameter(jmm p, int id) {
    super(p, id);
  }

  public String toString() {
	return "parameter: " + this.name;
  }

  public void buildST(SymbolTable table, String functionName) throws Exception {
	ASTType type = (ASTType) children[0];
	Symbol symbol = new Symbol(type.name,name);

	if(table.functions.get(functionName).params.get(name) != null)
		throw new Exception("Function " + functionName + " parameter " + name + " declared more than once.");
	
	table.functions.get(functionName).params.put(name, symbol);
  }
}
/* JavaCC - OriginalChecksum=7286d9338b7f7138e7c6c36551a2771c (do not edit this line) */
