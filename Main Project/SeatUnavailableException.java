public class SeatUnavailableException extends Exception{

	public SeatUnavailableException() {
		super("Seat is unavailable.");
	}
	
	public SeatUnavailableException(String message) {
		super(message);
	}
}
