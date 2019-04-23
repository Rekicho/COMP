import java.util.Hashtable;
import java.util.Enumeration;

public class FunctionSymbol {
	String returnType;
	Hashtable<String, Symbol> params = new Hashtable<>();
	Hashtable<String, Symbol> locals = new Hashtable<>();

	public FunctionSymbol(String returnType) {
		this.returnType = returnType;
	}

	public void dump() {
		Enumeration t = params.keys();
		String key;
		Symbol value;

		System.out.println("\t\t\tParameters:");

		while (t.hasMoreElements() == true) {
			key = (String) t.nextElement();
			value = (Symbol) params.get(key);
			System.out.println("\t\t\t\t" + key);
			if (value.type != null)
				System.out.println("\t\t\t\t\ttype = " + value.type);
			if (value.value != null)
				System.out.println("\t\t\t\t\tname = " + value.value);
		}

		System.out.println("\t\t\tLocals:");

		t = locals.keys();

		while (t.hasMoreElements() == true) {
			key = (String) t.nextElement();
			value = (Symbol) locals.get(key);
			System.out.println("\t\t\t\t" + key);
			if (value.type != null)
				System.out.println("\t\t\t\t\ttype = " + value.type);
			if (value.value != null)
				System.out.println("\t\t\t\t\tname = " + value.value);
		}

		System.out.println("\t\t\treturn: " + returnType);
	}
}