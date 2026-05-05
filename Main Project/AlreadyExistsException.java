public class AlreadyExistsException extends Exception {
	
	public AlreadyExistsException() {
		super("Entry already exists.");
	}
	
	public AlreadyExistsException(String message) {
		super(message);
	}
}
