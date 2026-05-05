import java.util.ArrayList;

public class Conference {
	
	private String conferenceName;
	private ArrayList<Event> events;
	
	
	
	public Conference(String conferenceName) {
		this.conferenceName = conferenceName;
		events = new ArrayList<Event>();
	}
	
	public void addEvent(String eventID, Date eventDate) throws AlreadyExistsException {
		
		for(Event e: events) {
			if(e.getEventID().equals(eventID)) {
				throw new AlreadyExistsException();
			}
		}
		
		events.add(new Event(eventID, eventDate));
		
		
	}
	
	
	public ArrayList<String> showAllEvents() {
		
		ArrayList<String> s = new ArrayList<>();
		for(Event e: events) {
			s.add(e.toString());
		}
		
		return s;
	}
	
	public ArrayList<String> findEventByDate(Date eventDate) throws InvalidDateException {
		int count = 0;
		ArrayList<String> eventsList = new ArrayList<String>();
		for(Event e: events) {
			if(eventDate.validate(e.getEventDate(), eventDate)) {
				eventsList.add(e.toString());
				count++;
			}
		}
		
		if(count == 0) {
			throw new InvalidDateException("The date provided is outside the conference duration.");
		}
		
		return eventsList;
	}
	
	
	
	public void bookEventSeat(String eventID, int seatNumber, String attendeeName) throws SeatUnavailableException, EventUnavailableException{
		
		for(Event e: events) {
			
			if(e.getEventID().equals(eventID)) {
				
			for(Seat s: e.getSeats()) {
				
				if(s.getSeatNumber() == seatNumber) {
					s.setAttendeeName(attendeeName);
					s.bookSeat();
					return;
				}
				
				
			}
			
			}
		}
		
		throw new EventUnavailableException();
		
	}
	
	
	public void cancelEventSeat(String eventID, int seatNumber, String attendeeName) throws ReservationUnavailableException, EventUnavailableException {
	
		for(Event e: events) {
			
			if(e.getEventID().equals(eventID)) {
				
			for(Seat s: e.getSeats()) {
				
				if(s.getSeatNumber() == seatNumber && s.getAttendeeName().equals(attendeeName)) {

					if(!s.isAvailable() ) {
					s.setAttendeeName(null);
					s.releaseSeat();
					return;
					}else if(s.getSeatNumber() == seatNumber && s.isAvailable()){
					
						throw new ReservationUnavailableException("Cannot cancel reservation. Seat number " + seatNumber + " for event (" + eventID + ") is not reserved.");
						
					}
				}
				
			}
				
			}
		}
		
		throw new EventUnavailableException();
		
	}
	
	
	public ArrayList<Seat> showEventSeats(String eventID) throws EventUnavailableException {
		int count = 0;
		for(Event e: events) {
			System.out.println(e.getEventID());
			if(e.getEventID().equalsIgnoreCase(eventID)) {
				ArrayList<Seat> seats = e.showAvailableSeats();
				count++;
				return seats;
			}
			
		}
		System.out.println(eventID);

		if(count == 0) {
		throw new EventUnavailableException();
		}
		return null;
	}
	
	public ArrayList<String> showReservedSeats() {
		int count = 0;
		ArrayList<String> seatsAndEvents = new ArrayList<String>();
		for(Event e : events) {
			
			seatsAndEvents.add(e.getEventID());
			for(Seat s : e.getSeats()) {
			if(!s.isAvailable()) {
				seatsAndEvents.add(s.toString());
				count++;
				}
			}
			if(count == 0) {
				seatsAndEvents.add(" No reservations for this event yet.");
			}else {
				count = 0;
			}
			
		}		
		
		return seatsAndEvents;
	}
	
}
