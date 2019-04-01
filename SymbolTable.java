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
			System.out.println(key);
			if (value.type != null)
				System.out.println(" type = " + value.type);
			if (value.value != null)
				System.out.println(" value = " + value.value);
		}

		Hashtable<String, Symbol> hash;
		t = functionSymbols.keys();

		while (t.hasMoreElements()) {
			key = (String) t.nextElement();
			hash = (Hashtable<String, Symbol>) functionSymbols.get(key);

			Enumeration tt = hash.keys();
			System.out.println("function " + key + "Symbols:");

			while (tt.hasMoreElements() == true) {
				key = (String) tt.nextElement();
				value = (Symbol) hash.get(key);
				System.out.println(key);
				if (value.type != null)
					System.out.println(" type = " + value.type);
				if (value.value != null)
					System.out.println(" value = " + value.value);
			}
		}
	}
}