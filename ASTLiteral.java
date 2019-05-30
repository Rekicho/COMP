/* Generated By:JJTree: Do not edit this line. ASTLiteral.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class ASTLiteral extends SimpleNode {
	static int labels = 0;
	String identifier = "";

	public ASTLiteral(int id) {
		super(id);
	}

	public ASTLiteral(jmm p, int id) {
		super(p, id);
	}

	public String toString() {
		return "Expression: " + identifier;
	}

	public void fixMethodCalls() {
		if (children == null)
			return;

		for (int i = 0; i < children.length; ++i) {
			SimpleNode n = (SimpleNode) children[i];
			if (n != null) {
				n.fixMethodCalls();
			}
		}

		if (children.length != 2)
			return;

		ASTOtherLiteral other_literal = (ASTOtherLiteral) children[1];

		if (!other_literal.type.equals("call"))
			return;

		children[0].jjtSetParent(children[1]);
		children[1].jjtAddChild(children[0], 0);

		Node temp = children[1];
		children = new Node[1];
		children[0] = temp;
	}

	public String analyseVariables(SymbolTable table, String functionName, String type) throws Exception {
		if (type.contains("[]")) {
			if (children == null || children.length == 0)
				return type;

			SimpleNode n = (SimpleNode) children[0];

			String childrenType;

			if ((childrenType = n.semanticAnalysis(table, functionName)).equals("[]"))
				return type;

			return childrenType;
		}

		return type;
	}

	public String semanticAnalysis(SymbolTable table, String functionName) throws Exception {
		super.semanticAnalysis(table, functionName);

		if (identifier.length() == 0) {
			SimpleNode n = (SimpleNode) children[0];
			return n.semanticAnalysis(table, functionName);
		}

		if (Character.isDigit(identifier.charAt(0)))
			return "int";

		if (identifier.equals("true") || identifier.equals("false"))
			return "boolean";

		if (identifier.equals("this")) {
			if (functionName != null && functionName.equals("main"))
				throw new Exception("this cannot be used in a static function");

			if (children == null || children.length == 0)
				return table.className;

			SimpleNode n = (SimpleNode) children[0];

			return n.semanticAnalysis(table, functionName);
		}

		if (identifier.equals("!")) {
			SimpleNode n = (SimpleNode) children[0];
			String childType = n.semanticAnalysis(table, functionName);

			if(!childType.equals("boolean") && !childType.equals(""))
				throw new Exception("Boolean Expression expected after '!'.");

			return "boolean";
		}

		if (identifier.equals("")) {
			SimpleNode n = (SimpleNode) children[0];

			return n.semanticAnalysis(table, functionName);
		}

		if (identifier.equals("new int[...]")) {
			SimpleNode n = (SimpleNode) children[0];
			String childType = n.semanticAnalysis(table, functionName);

			if(!childType.equals("int") && !childType.equals(""))
				throw new Exception("Integer Expression expected inside [].");

			return "int[]";
		}

		else if (identifier.length() >= 4 && identifier.substring(0, 4).equals("new ")) {
			if (children == null || children.length == 0)
				return identifier.substring(4);

			SimpleNode n = (SimpleNode) children[0];

			return n.semanticAnalysis(table, functionName);
		}

		if(children != null && children[0] instanceof ASTOtherLiteral) {
		ASTOtherLiteral other_literal = (ASTOtherLiteral) children[0];

		if (other_literal.type.equals("call"))
			return other_literal.semanticAnalysis(table, functionName);
		}

		if (functionName != null) {
			if (table.functions.get(functionName).locals.containsKey(identifier))
				return analyseVariables(table, functionName,
						table.functions.get(functionName).locals.get(identifier).type);

			if (table.functions.get(functionName).params.containsKey(identifier))
				return analyseVariables(table, functionName,
						table.functions.get(functionName).params.get(identifier).type);
		}

		if (table.symbols.containsKey(identifier))
			return analyseVariables(table, functionName, table.symbols.get(identifier).type);

		throw new Exception("Identifier '" + identifier + "' not found.");
	}

	boolean isArray(SymbolTable table, String functionName) throws Exception {
		if (Character.isDigit(identifier.charAt(0)))
			return false;

		if (identifier.equals("true") || identifier.equals("false") || identifier.equals("this")
				|| identifier.equals("!"))
			return false;

		if (identifier.equals("new int[...]"))
			return true;

		if (identifier.length() >= 4 && identifier.substring(0, 4).equals("new "))
			return false;

		if (identifier.equals("")) {
			SimpleNode n = (SimpleNode) children[0];

			return n.semanticAnalysis(table, functionName).contains("[]");
		}
		
		if (functionName != null) {
			if (table.functions.get(functionName).locals.containsKey(identifier))
				return table.functions.get(functionName).locals.get(identifier).type.contains("[]");

			if (table.functions.get(functionName).params.containsKey(identifier))
				return table.functions.get(functionName).params.get(identifier).type.contains("[]");
		}

		if (table.symbols.containsKey(identifier))
			return table.symbols.get(identifier).type.contains("[]");

		if (table.functions.containsKey(identifier))
			return table.functions.get(identifier).returnType.contains("[]");

		return false;
	}

	public void generateFunctionCode(StringBuilder builder, SymbolTable ST, String functionName) {		
		if(identifier.equals("new int[...]")) {
			Symbol symbol;
			if ((symbol = ST.functions.get(functionName).locals.get(identifier)) != null) {
				builder.append("aload " + symbol.order + "\n");
			}
			else if ((symbol = ST.functions.get(functionName).params.get(identifier)) != null) {
				builder.append("aload " + symbol.order + "\n");
			}
			else if((symbol = ST.symbols.get(identifier)) != null) {
				builder.append("aload_0\ngetfield " + ST.className + "/" + identifier + " [I\n");
			}

			((SimpleNode) children[0]).generateFunctionCode(builder, ST, functionName);
			builder.append("newarray int\n");
			return;
		}

		if(identifier.contains("new ")) {
			if(children != null) 
				super.generateCode(builder, ST, functionName);

			else builder.append(identifier + "\ndup\ninvokespecial " + identifier.substring(4) + "/<init>()V\n");

			return;
		}

		if(identifier.equals("!")) {
			((SimpleNode) children[0]).generateFunctionCode(builder, ST, functionName);

			int label = labels;
			labels += 2;

			builder.append("ifne NOT" + label + "\niconst_1\ngoto NOT" + ((int) label + 1) + "\nNOT" + label + ":\niconst_0\nNOT" + ((int) label + 1) + ":\n");

			return;
		}

		if(identifier.equals("") && children != null) {
			((SimpleNode) children[children.length - 1]).generateFunctionCode(builder, ST, functionName);
			return;
		}

		if(children != null) {
			((SimpleNode) children[children.length - 1]).generateCode(builder, ST, functionName);
			return;
		}

		if (Character.isDigit(identifier.charAt(0))) {
			int value = Integer.parseInt(identifier);

			if (value >= 0 && value <= 5) {
				builder.append("iconst_" + value);
			} else if (value == -1) {
				builder.append("iconst_m1");
			} else if (value > -129 && value < 128) {
				builder.append("bipush " + value);
			} else if (value > -32769 && value < 32768) {
				builder.append("sipush " + value);
			} else {
				builder.append("ldc " + value);
			}
		}
		else if(identifier.equals("this")) {
			builder.append("aload_0");
		}
		else if(identifier.equals("true")) {
			builder.append("iconst_1");
		}
		else if(identifier.equals("false")) {
			builder.append("iconst_0");
		}
		else {
			Symbol symbol;
			if ((symbol = ST.functions.get(functionName).locals.get(identifier)) != null) {
				if(symbol.type.equals("boolean") || symbol.type.equals("int"))
					builder.append("iload " + symbol.order);

				else builder.append("aload " + symbol.order);
			}
			else if ((symbol = ST.functions.get(functionName).params.get(identifier)) != null) {
				if(symbol.type.equals("boolean") || symbol.type.equals("int"))
					builder.append("iload " + symbol.order);

				else builder.append("aload " + symbol.order);
			}
			else if((symbol = ST.symbols.get(identifier)) != null) {
				if(symbol.type.equals("boolean"))
					builder.append("aload_0\ngetfield " + ST.className + "/" + identifier + " Z" );

				else if(symbol.type.equals("int"))
					builder.append("aload_0\ngetfield " + ST.className + "/" + identifier + " I" );

				else if(symbol.type.equals("int[]"))
					builder.append("aload_0\ngetfield " + ST.className + "/" + identifier + " [I" );

				else builder.append("aload_0\ngetfield " + ST.className + "/" + identifier + " L" );
			} 
		}

		builder.append("\n");
	}

	public void generateCode(StringBuilder builder, SymbolTable ST, String functionName) {
		generateFunctionCode(builder,ST,functionName);
	}
}
/*
 * JavaCC - OriginalChecksum=a8d48e9a57e0088d4ff5320e922f78d0 (do not edit this
 * line)
 */
