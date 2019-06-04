/* Generated By:JJTree: Do not edit this line. ASTMainDeclaration.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package ast;

import jmm.*;

public
class ASTMainDeclaration extends SimpleNode {
	public String args = "";

  public ASTMainDeclaration(int id) {
    super(id);
  }

  public ASTMainDeclaration(jmm p, int id) {
    super(p, id);
  }

  public String toString() {
    return "main: param: " + this.args;
  }

  public void buildST(SymbolTable table, String functionName) throws Exception {
	if(table.functions.get("main") != null)
		throw new Exception("Function main declared twice");

	FunctionSymbol functionSymbol = new FunctionSymbol("void");
	table.functions.put("main", functionSymbol);

	Symbol mainSymbol = new Symbol("String[]", args);
	functionSymbol.params.put(args,mainSymbol);
	mainSymbol.order = 0;
	
	if (children != null) {
		for (int i = 0; i < children.length; ++i) {
			SimpleNode n = (SimpleNode) children[i];
			if (n != null) {
				try{
					n.buildST(table, "main");
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
					n.semanticAnalysis(table,"main");
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
		builder.append(".method public static main([Ljava/lang/String;)V\n.limit stack 100\n.limit locals " + ((int) ST.functions.get("main").params.size() + ST.functions.get("main").locals.size() - ST.functions.get("main").unusedLocals) + "\n");

		if (children != null) {
			for (int i = 0; i < children.length; ++i) {
				SimpleNode n = (SimpleNode) children[i];
				if (n != null) {
					n.generateCode(builder,ST,"main");
				}
			}
		}

		builder.append("return\n.end method\n\n");
	}

	public void optimizeO(SymbolTable ST, String functionName) {
		if (children != null) {
			for (int i = 0; i < children.length; ++i) {
				SimpleNode n = (SimpleNode) children[i];
				if (n != null) {
					n.optimizeO(ST,"main");
				}
			}
		}
	}
}
/* JavaCC - OriginalChecksum=0c5227e4bd79d47833b58d1707cedc06 (do not edit this line) */
