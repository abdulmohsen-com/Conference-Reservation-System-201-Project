public class ReservationUnavailableException extends Exception{
	
	public ReservationUnavailableException() {
		super("Reservation does not exist.");
	}
	
	public ReservationUnavailableException(String message) {
		super(message);
	}
}
