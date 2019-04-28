/* Generated By:JJTree: Do not edit this line. ASTMethodDeclaration.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class ASTMethodDeclaration extends SimpleNode {
	String name = "";

	public ASTMethodDeclaration(int id) {
		super(id);
	}

	public ASTMethodDeclaration(jmm p, int id) {
		super(p, id);
	}

	public String toString() {
		return "method: " + this.name;
	}

	public void buildST(SymbolTable table, String functionName) throws Exception {
		if(table.functions.get(name) != null)
			throw new Exception("Function" + name + "declared twice");

		ASTType type = (ASTType) children[0];
		String typeName = type.name;
		if(type.isArray) typeName += "[]";
		
		FunctionSymbol functionSymbol = new FunctionSymbol(typeName);
		table.functions.put(name, functionSymbol);
		
		if (children != null) {
			for (int i = 0; i < children.length; ++i) {
				SimpleNode n = (SimpleNode) children[i];
				if (n != null) {
					try{
						n.buildST(table, name);
					}
					catch(Exception e){
						System.out.println(e.getMessage());
					}
				}
			}
		}
	}

	public String semanticAnalysis(SymbolTable table, String functionName) throws Exception {
		if (children != null) {
			for (int i = 0; i < children.length; ++i) {
				SimpleNode n = (SimpleNode) children[i];
				if (n != null) {
					try{
						n.semanticAnalysis(table, name);
					  }  
					  catch(Exception e){
						System.out.println(e.getMessage());
					  }
				}
			}
		}
	
		return "";
	}

	public void generateCode(StringBuilder builder, SymbolTable ST, String functionName) {
		builder.append("---" + name + ":---\n");
		if (children != null) {
			for (int i = 0; i < children.length; ++i) {
				SimpleNode n = (SimpleNode) children[i];
				if (n != null) {
					n.generateCode(builder,ST,name);
				}
			}
		}
	}
}
/*
 * JavaCC - OriginalChecksum=11d41e8ab96800088d3eba562689294a (do not edit this
 * line)
 */
