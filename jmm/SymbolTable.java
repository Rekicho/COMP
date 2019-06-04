package jmm;

import java.util.Hashtable;
import java.util.Enumeration;

public class SymbolTable {
	public String className = "";
	public String extendsName = "";
	public Hashtable<String, Symbol> symbols = new Hashtable<>();
	public Hashtable<String, FunctionSymbol> functions = new Hashtable<>();

	public SymbolTable(SimpleNode root) throws Exception {
		root.buildST(this, null);
	}

	public void dump() {
		String key;
		Symbol value;
		Enumeration<String> t = symbols.keys();
		System.out.println("class " + className + " Symbols: ");

		while (t.hasMoreElements() == true) {
			key = (String) t.nextElement();
			value = (Symbol) symbols.get(key);
			System.out.println("\t" + key);
			if (value.type != null)
				System.out.println("\t\ttype = " + value.type);
			if (value.value != null)
				System.out.println("\t\tvalue = " + value.value);
		}

		t = functions.keys();

		FunctionSymbol functionSymbol;

		while (t.hasMoreElements()) {
			key = (String) t.nextElement();
			functionSymbol = (FunctionSymbol) functions.get(key);

			System.out.println("\t\tfunction " + key + ":");

			functionSymbol.dump();
		}
	}

	public void removeUnusedLocals() {
		String key;
		Enumeration<String> t = functions.keys();
		FunctionSymbol functionSymbol;

		while (t.hasMoreElements()) {
			key = (String) t.nextElement();
			functionSymbol = (FunctionSymbol) functions.get(key);

			functionSymbol.removeUnusedLocals();
		}
	}
}