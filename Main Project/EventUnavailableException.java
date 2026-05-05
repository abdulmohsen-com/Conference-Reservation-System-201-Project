public class EventUnavailableException extends Exception{

	public EventUnavailableException() {
		super("Event does not exist.");
	}
	
	public EventUnavailableException(String message) {
		super(message);
	}
}
