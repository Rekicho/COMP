package jmm;

public class Symbol {
	public String type;
	public String value;
	public int order;
	public String var_value = "";

	public Symbol(String type, String value) {
		this.type = type;
		this.value = value;
	}
}