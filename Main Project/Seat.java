public class Seat {
	
	private int seatNumber;
	private boolean isAvailable; //True = unoccupied, False = occupied
	private String attendeeName;
	
	public Seat(int seatNumber) {
		this.seatNumber = seatNumber;
		isAvailable = true;
		attendeeName = null;
	}
	
	public Seat(int seatNumber, String attendeeName) {
		this.seatNumber = seatNumber;
		isAvailable = true;
		this.attendeeName = attendeeName;
	}
	
	public void bookSeat()  throws SeatUnavailableException{
		
		if(isAvailable) {
			isAvailable = false;
		}else {
			throw new SeatUnavailableException();
		}
	}
	
	public void releaseSeat() throws ReservationUnavailableException{
		isAvailable = true;
	}

	public String getAttendeeName() {
		return attendeeName;
	}

	public void setAttendeeName(String attendeeName) {
		this.attendeeName = attendeeName;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return  String.format("Attendee: %-10s Seat Number: %d",attendeeName, this.seatNumber);
		
	}
	
}
