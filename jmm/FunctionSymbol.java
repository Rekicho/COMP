package jmm;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Enumeration;
import java.util.Set;

public class FunctionSymbol {
	public String returnType;
	public LinkedHashMap<String, Symbol> params = new LinkedHashMap<>();
	public Hashtable<String, Symbol> locals = new Hashtable<>();

	public FunctionSymbol(String returnType) {
		this.returnType = returnType;
	}

	public void dump() {
		Set<String> keys = params.keySet();
		Iterator<String> it = keys.iterator();

		String key;
		Symbol value;

		System.out.println("\t\t\tParameters:");

		while (it.hasNext() == true) {
			key = (String) it.next();
			value = (Symbol) params.get(key);
			System.out.println("\t\t\t\t" + key);
			if (value.type != null)
				System.out.println("\t\t\t\t\ttype = " + value.type);
			if (value.value != null)
				System.out.println("\t\t\t\t\tname = " + value.value);
		}

		System.out.println("\t\t\tLocals:");

		Enumeration<String> t = locals.keys();

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