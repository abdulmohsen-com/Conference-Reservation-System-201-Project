//Name: Abdulmohsen AlGhanim	ID: 2231118745

public class InvalidDateException extends Exception {
	
	public InvalidDateException() {
		super("Invalid date.");
	}
	
	public InvalidDateException(String message) {
		super(message);
	}
}
