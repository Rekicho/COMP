import java.util.Hashtable;
import java.util.Enumeration;

public class SymbolTable {
	Hashtable<String, Symbol> classSymbols = new Hashtable<>();
	Hashtable<String, Hashtable<String, Symbol>> functionSymbols = new Hashtable<>();

	public SymbolTable(SimpleNode root) {
		root.buildST(classSymbols, functionSymbols);
	}

	public void dump() {
		String key;
		Symbol value;
		Enumeration t = classSymbols.keys();
		System.out.println("class Symbols: ");

		while (t.hasMoreElements() == true) {
			key = (String) t.nextElement();
			value = (Symbol) classSymbols.get(key);
			System.out.println("\t" + key);
			if (value.type != null)
				System.out.println("\t\ttype = " + value.type);
			if (value.value != null)
				System.out.println("\t\tvalue = " + value.value);
		}

		System.out.println("\n\n");

		Hashtable<String, Symbol> hash;
		t = functionSymbols.keys();

		while (t.hasMoreElements()) {
			key = (String) t.nextElement();
			hash = (Hashtable<String, Symbol>) functionSymbols.get(key);

			Enumeration tt = hash.keys();
			System.out.println("\t\tfunction " + key + "Symbols:");

			while (tt.hasMoreElements() == true) {
				key = (String) tt.nextElement();
				value = (Symbol) hash.get(key);
				if(value.isParameter) System.out.println("\t\t\tParameter " + key); else System.out.println("\t\t\tLocal " + key);
				if (value.type != null)
					System.out.println("\t\t\t\ttype = " + value.type);
				if (value.value != null)
					System.out.println("\t\t\t\tname = " + value.value);
			}
		}
	}
}