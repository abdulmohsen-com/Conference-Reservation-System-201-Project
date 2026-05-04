//Name: Abdulmohsen AlGhanim	ID: 2231118745

import java.util.ArrayList;

public class Event {
	
	private String eventID;
	private ArrayList<Seat> seats;
	private Date eventDate;
	
	
	public Event(String eventID, Date eventDate){
		
		this.eventDate = eventDate;
		this.eventID = eventID;
		seats = new ArrayList<Seat>();
		//seats from 1 -> 100
		for(int i = 1; i < 101; i++) {
			seats.add(new Seat(i));
		}
		
	}
	
	
	public ArrayList<Seat> showAvailableSeats() {
		ArrayList<Seat> availableSeats = new ArrayList<Seat>();
		int counter = 0;
		System.out.println("Currently available seats: ");
		
		for(Seat s: seats) {
			
			if(s.isAvailable()) {
				System.out.print(s.getSeatNumber() + " ");
				counter++;
				availableSeats.add(s);
			}
			if(counter % 10 == 0) {
				System.out.println("");
			}
		}
		System.out.println("");
		System.out.println("Number of available seats: " + counter + "/100.");
		return availableSeats;
	}
	
	
	public String getEventID() {
		return this.eventID;
	}


	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	

	public ArrayList<Seat> getSeats() {
		return seats;
	}


	public Date getEventDate() {
		return eventDate;
	}


	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}


	public void bookSeat(int seatNumber, String attendeeName) throws SeatUnavailableException{
		
		for(Seat s: seats) {
			if(s.getSeatNumber() == seatNumber && s.isAvailable()) {
				s.bookSeat();
				s.setAttendeeName(attendeeName);
				return;
			}
			throw new SeatUnavailableException();
		}
	
	}
	
	
	@Override
	public String toString() {
		
		int counter = 0;
		for(Seat s: seats) {
			if(s.isAvailable()) {
				counter++;
			}
			}
		String s = String.format("Event ID: %-18s Date: " + eventDate +"   Number of available seats: %d/100", eventID, counter);
		return s;
	}
	
}
