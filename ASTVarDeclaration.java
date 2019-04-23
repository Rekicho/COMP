import java.util.Hashtable;

/* Generated By:JJTree: Do not edit this line. ASTVarDeclaration.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class ASTVarDeclaration extends SimpleNode {
	String name = "";

	public ASTVarDeclaration(int id) {
		super(id);
	}

	public ASTVarDeclaration(jmm p, int id) {
		super(p, id);
	}

	public String toString() {
		return "var: " + this.name;
	}

	public void buildST(SymbolTable table, String functionName) throws Exception {
		ASTType type = (ASTType) children[0];
		Symbol symbol = new Symbol(type.name,name);
		
		if(functionName == null) {
			if(table.symbols.get(name) != null)
				throw new Exception("Class local " + name + " declared more than once.");

			table.symbols.put(name,symbol);
		}
			

		else {			
			if(table.functions.get(functionName).locals.get(name) != null)
				throw new Exception("Function " + functionName + " local " + name + " declared more than once.");

			table.functions.get(functionName).locals.put(name,symbol);
		}
	}

}
/*
 * JavaCC - OriginalChecksum=64dbd233ecaf7257caed4a6a751f30bf (do not edit this
 * line)
 */
