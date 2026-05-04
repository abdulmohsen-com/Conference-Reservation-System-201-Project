//Name: Abdulmohsen AlGhanim	ID: 2231118745

public class SeatUnavailableException extends Exception{

	public SeatUnavailableException() {
		super("Seat is unavailable.");
	}
	
	public SeatUnavailableException(String message) {
		super(message);
	}
}
