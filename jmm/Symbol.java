package jmm;

public class Symbol {
	public String type;
	public String value;
	public int order;
	public String var_value = "";
	public int defs = 0;
	public boolean needsCode = true;

	public Symbol(String type, String value) {
		this.type = type;
		this.value = value;
	}
}