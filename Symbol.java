public class Symbol {
	String type;
	String value;
	boolean isParameter = false;

	public Symbol(String type, String value) {
		this.type = type;
		this.value = value;
	}
}