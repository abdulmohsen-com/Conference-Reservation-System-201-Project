//Name: Abdulmohsen AlGhanim	ID: 2231118745

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		
		Conference c = new Conference("Global Innovations 2025");
		try {
		c.addEvent("AI Basics", new Date(23, 12, 2025));
		c.addEvent("Intro to Robotics", new Date(23, 12, 2025));
		c.addEvent("Machine Learning", new Date(24, 12, 2025));
		c.addEvent("Deep Learning", new Date(24, 12, 2025));
		c.addEvent("Green Tech", new Date(25, 12, 2025));
		c.addEvent("Data Privacy", new Date(25, 12, 2025));
		}catch(Exception e) {
			System.err.println(e);
		}
		
		Scanner input = new Scanner(System.in);
		
		int choice = 0;
		
		while(choice != 6) {
			try {
			displayMenu();
			
			choice = input.nextInt();
			input.nextLine();
			
			switch(choice) {
			case 1:
				ArrayList<String> s = c.showAllEvents();
				
				for(String st : s) {
					System.out.println(st);
				}
				break;
			case 2:
				System.out.print("Enter the event ID: ");
				String id = input.nextLine();
				ArrayList<Seat> seats = c.showEventSeats(id);
				System.out.print("Enter desired seat number: ");
				int seat = input.nextInt();
				input.nextLine();
				System.out.print("Enter attendee name: ");
				String name = input.next();
				c.bookEventSeat(id, seat, name);
				break;
			case 3:
				System.out.print("Enter the event ID: ");
				String id2 = input.nextLine();
				c.showEventSeats(id2);
				System.out.println("Currently available seats: ");
				System.out.println("");
				System.out.print("Enter seat number: ");
				int seat2 = input.nextInt();
				input.nextLine();
				System.out.print("Enter attendee name: ");
				String name2 = input.nextLine();
				c.cancelEventSeat(id2, seat2, name2);
				System.out.println("Successfuly cancelled reservation for Event: " + id2 + " Attendee: " + name2 + " Seat number: " + seat2);
				break;
			case 4:
				ArrayList<String> s1 = c.showReservedSeats();
				
				for(String st : s1) {
					System.out.println(st);
				}
				
				break;
			case 5:
				System.out.print("Enter the date as Day/Month/Year: ");
				String dmy = input.nextLine();
				
				int d = Integer.parseInt(dmy.valueOf(dmy.substring(0, dmy.indexOf('/'))));
				dmy = dmy.substring(dmy.indexOf('/') + 1);
				int m = Integer.parseInt(dmy.valueOf(dmy.substring(0, dmy.indexOf('/'))));
				dmy = dmy.substring(dmy.indexOf('/') + 1);
				int y = Integer.parseInt(dmy.valueOf(dmy.substring(0)));
				
				
				
				ArrayList<String> s2 = c.findEventByDate(new Date(d,m,y));
				
				for(String st : s2) {
					System.out.println(st);
				}
				break;
			case 6:
				return;
			default:
				throw new InputMismatchException();
			}
			
			}catch(InputMismatchException e) {
				System.err.println(e);
			}catch(Exception e){
				System.err.println(e);
			}
			
			System.out.println("");

		
		}

		
				
		
	}

	public static void displayMenu() {
		System.out.println("Please select an action: ");
		System.out.println("1- View All Events");
		System.out.println("2- Reserve a Seat for an Event");
		System.out.println("3- Cancel a Reservation");
		System.out.println("4- Display All Reservations");
		System.out.println("5- Search Events by Date");
		System.out.println("6- Exit");
		System.out.print("Enter selections: ");
	}
	
	
}
