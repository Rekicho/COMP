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
		if(type.isArray) symbol.type += "[]";
		
		if(functionName == null) {
			if(table.symbols.get(name) != null)
				throw new Exception("Line " + lineNumber + ": Class local " + name + " declared more than once.");

			table.symbols.put(name,symbol);
		}
			

		else {			
			if(table.functions.get(functionName).locals.get(name) != null || table.functions.get(functionName).params.get(name) != null)
				throw new Exception("Line " + lineNumber + ": Function " + functionName + " local " + name + " declared more than once.");

			table.functions.get(functionName).locals.put(name,symbol);
			symbol.order = table.functions.get(functionName).locals.size() + table.functions.get(functionName).params.size();

			if(functionName.equals("main")) {
				symbol.order++;
			}
		}
	}

	public void generateCode(StringBuilder builder, SymbolTable ST, String functionName) {
		if(functionName == null) {
			ASTType type = (ASTType) children[0];
			String typeName = type.name;
			if(type.isArray) typeName += "[]";
			
			builder.append(".field private " + name);

			if(typeName.equals("boolean"))
			builder.append(" Z\n");

			else if(typeName.equals("int"))
				builder.append(" I\n");

			else if(typeName.equals("int[]"))
				builder.append(" [I\n");

			else if(typeName.equals(ST.className))
				builder.append(" L" + ST.className + "\n");

			else builder.append(" Ljava/lang/" + typeName + ";\n");
		}
	}

}
/*
 * JavaCC - OriginalChecksum=64dbd233ecaf7257caed4a6a751f30bf (do not edit this
 * line)
 */
