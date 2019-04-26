/* Generated By:JJTree: Do not edit this line. ASTOtherLiteral.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
import java.util.Iterator;
import java.util.Set;

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

		case "call":
				if(table.functions.get(identifier) == null)
					return "";

				Set<String> keys = table.functions.get(identifier).params.keySet();
				Iterator<String> it = keys.iterator();
				int i = 0;

				if(children == null || children.length == 0)
				{
					if(keys.size() != 0)
						throw new Exception("Function call " + identifier + " expected " + keys.size() + " parameters but found 0.");
				}



				else {
					ASTMethodParams methodParams = (ASTMethodParams) children[0];

					String[] params = methodParams.getParams(table, functionName);

					for (; i < params.length && it.hasNext(); ++i) {
						String key = (String) it.next();
						Symbol value = (Symbol) table.functions.get(identifier).params.get(key);

						if(!params[i].equals(value.type))
							throw new Exception("Function call " + identifier + " parameter " + i + " expected: " + value.type + " found: " + params[i] + ".");
					}

					if(i < params.length || it.hasNext())
						throw new Exception("Function call " + identifier + " expected " + keys.size() + " parameters but found " + params.length + ".");
						
				}
			
				return table.functions.get(identifier).returnType;
	}
			
	return "";
  }

}
/* JavaCC - OriginalChecksum=6a67081bb7fb5f77aa5f878ca7d3dcab (do not edit this line) */
