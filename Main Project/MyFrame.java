import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.InputMismatchException;

import javax.swing.*;

public class MyFrame extends JFrame {
	
	public MyFrame() {
		
		super("Conference Registration System");
		this.setLayout(new BorderLayout());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);
		this.revalidate();
		this.repaint();
		JPanel total = new JPanel();
		add(total, BorderLayout.CENTER);
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
		
		JPanel viewAll = new JPanel(new GridLayout(7,1, 10, 10));
	    JPanel reserveSeat = new JPanel(new BorderLayout());
	    JPanel cancelSeat = new JPanel(new BorderLayout());
	    JPanel viewSeat = new JPanel(new GridLayout(20,1, 10, 10));
	    JPanel search = new JPanel(new BorderLayout());
	    JPanel menuButton = new JPanel(new FlowLayout());
	    String[] choices = { "View All Events","Reserve a Seat for an Event", "Cancel a Reservation","Display All Reservations","Search Events by Date"};
	    
	    JComboBox<String> cb = new JComboBox<String>(choices);
	    JLabel welcome = new JLabel("Please select an action then press OK:");
	    
	    JButton ok = new JButton("OK");
	    
	    JLabel title = new JLabel("Global Innovations 2025");
	    JLabel title2 = new JLabel("Global Innovations 2025 - Reserved Seats");

	    viewAll.add(title);
		this.revalidate();
		this.repaint();
	    ArrayList<String> st = c.showAllEvents();
	    JPanel seatsPanel = new JPanel(new BorderLayout());
		JPanel seatsSelection = new JPanel(new GridLayout(10,10));
		
		this.revalidate();
		this.repaint();
	    ok.addActionListener(new ActionListener() {
	    	
	    	
			@Override
			public void actionPerformed(ActionEvent e) {
				total.removeAll();
				viewAll.removeAll();
				seatsSelection.removeAll();
				seatsPanel.removeAll();
				reserveSeat.removeAll();
				cancelSeat.removeAll();
				viewSeat.removeAll();
				search.removeAll();
				
				total.revalidate();
				total.repaint();
				viewAll.revalidate();
				viewAll.repaint();
				seatsSelection.revalidate();
				seatsSelection.repaint();
				seatsPanel.revalidate();
				seatsPanel.repaint();
				reserveSeat.revalidate();
				reserveSeat.repaint();
				cancelSeat.revalidate();
				cancelSeat.repaint();
				viewSeat.revalidate();
				viewSeat.repaint();
				search.revalidate();
				search.repaint();
				
				if(cb.getSelectedItem().equals("View All Events")) {
					viewAll.removeAll();
					seatsSelection.removeAll();
					seatsPanel.removeAll();
					reserveSeat.removeAll();
				    viewAll.add(title);
				    ArrayList<String> st = c.showAllEvents();
					for(String s : st) {
						JLabel event = new JLabel(s);
						viewAll.add(event);
					}
					
					total.add(viewAll);
					viewAll.revalidate();
					viewAll.repaint();
				}else if(cb.getSelectedItem().equals("Reserve a Seat for an Event")) {
					try {
					JPanel top = new JPanel(new FlowLayout());	
					JLabel option = new JLabel("Enter the event ID: ");
					top.add(option);
				    String[] events = { "AI Basics","Intro to Robotics", "Machine Learning","Deep Learning","Green Tech", "Data Privacy"};

				    JComboBox<String> idText = new JComboBox<String>(events);
					top.add(idText);
					JButton submit = new JButton("Submit");
					submit.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							String id = idText.getSelectedItem().toString();
							viewAll.revalidate();
							viewAll.repaint();
							seatsSelection.revalidate();
							seatsSelection.repaint();
							seatsPanel.revalidate();
							seatsPanel.repaint();
							reserveSeat.revalidate();
							reserveSeat.repaint();

							JLabel event = new JLabel(id + " seats");
							
							seatsPanel.add(event, BorderLayout.NORTH);
							seatsSelection.removeAll();
							seatsPanel.removeAll();
							try {
								ArrayList<Seat> seats = c.showEventSeats(id);
								for(Seat s : seats) {
									JLabel seat = new JLabel(s.getSeatNumber() + "");
									seatsSelection.add(seat);
								}
							} catch (EventUnavailableException e1) {
								JOptionPane.showMessageDialog(cancelSeat, e1.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);								
							}
							
							viewAll.revalidate();
							viewAll.repaint();
							seatsSelection.revalidate();
							seatsSelection.repaint();
							seatsPanel.revalidate();
							seatsPanel.repaint();
							reserveSeat.revalidate();
							reserveSeat.repaint();
							JLabel seatWords = new JLabel("Enter desired seat number:");
							JTextField seatText = new JTextField(15);
							JLabel nameWords = new JLabel("Enter attendee name:");
							JTextField nameText = new JTextField(15);
							JButton submit2 = new JButton("Submit");
							
							submit2.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									
									try {
										int seat = Integer.parseInt(seatText.getText());
										String name = nameText.getText();
										c.bookEventSeat(id, seat, name);
										JOptionPane.showMessageDialog(reserveSeat, "Attendee " + name + " has booked Seat " + seat + " in event " + id + " sucessfully!");
									} catch (SeatUnavailableException | EventUnavailableException e1) {
										JOptionPane.showMessageDialog(cancelSeat, "Seat is unavailable", "ERROR", JOptionPane.ERROR_MESSAGE);								
									}catch(Exception e1) {
										JOptionPane.showMessageDialog(cancelSeat, "Please input a valid integer for the seat number", "ERROR", JOptionPane.ERROR_MESSAGE);
									}		
									
									
								}
								
							});
							
							JPanel south = new JPanel(new FlowLayout());
							south.add(seatWords);
							south.add(seatText);
							south.add(nameWords);
							south.add(nameText);
							south.add(submit2);
							seatsPanel.add(south, BorderLayout.SOUTH);
							seatsPanel.add(seatsSelection, BorderLayout.CENTER);
							reserveSeat.add(seatsPanel, BorderLayout.CENTER);
							

						}
						
					});
					
					top.add(submit);					
					reserveSeat.add(top, BorderLayout.NORTH);
					total.add(reserveSeat);
					
					}catch(InputMismatchException e1) {
						JOptionPane.showMessageDialog(cancelSeat, e1.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);								
					}catch(Exception e1){
						JOptionPane.showMessageDialog(cancelSeat, e1.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);								
					}
				}else if(cb.getSelectedItem().equals("Cancel a Reservation")) {
					try {
						JPanel top = new JPanel(new FlowLayout());

					JLabel option = new JLabel("Enter the event ID: ");
					top.add(option);
				    String[] events = { "AI Basics","Intro to Robotics", "Machine Learning","Deep Learning","Green Tech", "Data Privacy"};

				    JComboBox<String> idText = new JComboBox<String>(events);
					top.add(idText);
					JButton submit = new JButton("Submit");
					submit.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							String id = idText.getSelectedItem().toString();
							search.removeAll();
							viewAll.revalidate();
							viewAll.repaint();
							seatsSelection.revalidate();
							seatsSelection.repaint();
							seatsPanel.revalidate();
							seatsPanel.repaint();
							reserveSeat.revalidate();
							reserveSeat.repaint();
							cancelSeat.revalidate();
							cancelSeat.repaint();
							search.revalidate();
							search.repaint();
							
							JLabel event = new JLabel(id + " seats");
							
							seatsPanel.add(event, BorderLayout.NORTH);
							seatsSelection.removeAll();
							seatsPanel.removeAll();
							try {
								ArrayList<Seat> seats = c.showEventSeats(id);
								for(Seat s : seats) {
									JLabel seat = new JLabel(s.getSeatNumber() + "");
									seatsSelection.add(seat);
								}
							} catch (EventUnavailableException e1) {
								System.err.println(e1);
							}
							
							viewAll.revalidate();
							viewAll.repaint();
							seatsSelection.revalidate();
							seatsSelection.repaint();
							seatsPanel.revalidate();
							seatsPanel.repaint();
							reserveSeat.revalidate();
							reserveSeat.repaint();
							cancelSeat.revalidate();
							cancelSeat.repaint();
							JLabel seatWords = new JLabel("Enter seat number to cancel:");
							JTextField seatText = new JTextField(15);
							JLabel nameWords = new JLabel("Enter attendee name:");
							JTextField nameText = new JTextField(15);
							JButton submit2 = new JButton("Submit");
							
							submit2.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									
									
									try {
										int seat = Integer.parseInt(seatText.getText());
										String name = nameText.getText();
										c.cancelEventSeat(id, seat, name);
										JOptionPane.showMessageDialog(cancelSeat, "Attendee " + name + " has cancelled Seat " + seat + " in event " + id + " sucessfully!");
									} catch (EventUnavailableException e1) {
										JOptionPane.showMessageDialog(cancelSeat, "Unauthorised Access, Name Incorrect.", "ERROR", JOptionPane.ERROR_MESSAGE);
									} catch (ReservationUnavailableException e1) {
										JOptionPane.showMessageDialog(cancelSeat, e1.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);									
									}catch(NullPointerException e1) {
										JOptionPane.showMessageDialog(cancelSeat, "Seat is not booked", "ERROR", JOptionPane.ERROR_MESSAGE);
									}catch(Exception e1) {

										System.err.println(e1);
										JOptionPane.showMessageDialog(cancelSeat, "Please input a valid integer for the seat number", "ERROR", JOptionPane.ERROR_MESSAGE);
									}	
									
								}
								
							});
							
							JPanel south = new JPanel(new FlowLayout());
							south.add(seatWords);
							south.add(seatText);
							south.add(nameWords);
							south.add(nameText);
							south.add(submit2);
							seatsPanel.add(south, BorderLayout.SOUTH);
							seatsPanel.add(seatsSelection, BorderLayout.CENTER);
							cancelSeat.add(seatsPanel, BorderLayout.CENTER);
							

						}
						
					});
					
					top.add(submit);					
					cancelSeat.add(top, BorderLayout.NORTH);
					total.add(cancelSeat);
					
					}catch(InputMismatchException e1) {
						JOptionPane.showMessageDialog(cancelSeat, e1.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);	
							
					}catch(Exception e1){
						JOptionPane.showMessageDialog(cancelSeat, e1.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);								
					}	
					
				}else if(cb.getSelectedItem().equals("Display All Reservations")) {
					viewAll.removeAll();
					seatsSelection.removeAll();
					seatsPanel.removeAll();
					reserveSeat.removeAll();
					cancelSeat.removeAll();
					
				    viewSeat.add(title2);
				    
				    ArrayList<String> st = c.showReservedSeats();
					for(String s : st) {
						JLabel event = new JLabel(s);
						viewSeat.add(event);
					}
					
					total.add(viewSeat);
					viewSeat.revalidate();
					viewSeat.repaint();
				
					add(total, BorderLayout.CENTER);
					
					viewSeat.revalidate();
					viewSeat.repaint();
				}else if(cb.getSelectedItem().equals("Search Events by Date")) {
					JPanel top = new JPanel(new FlowLayout());
					JLabel option = new JLabel("Enter date (DD/MM/YYYY) to search: ");
					top.add(option);
					JTextField dateText = new JTextField(15);
					top.add(dateText);
					JButton submit = new JButton("Submit");
					submit.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							
							String dmy = dateText.getText();
							
							
							try {
								
								int d = Integer.parseInt(dmy.valueOf(dmy.substring(0, dmy.indexOf('/'))));
								dmy = dmy.substring(dmy.indexOf('/') + 1);
								int m = Integer.parseInt(dmy.valueOf(dmy.substring(0, dmy.indexOf('/'))));
								dmy = dmy.substring(dmy.indexOf('/') + 1);
								int y = Integer.parseInt(dmy.valueOf(dmy.substring(0)));
								
							    ArrayList<String> st = c.findEventByDate(new Date(d,m,y));
								JPanel searchedFor = new JPanel(new GridLayout(10,1));
							    for(String s : st) {
									JLabel event = new JLabel(s);
									searchedFor.add(event);
								}
							    search.add(searchedFor, BorderLayout.CENTER);
							    search.revalidate();
								search.repaint();
							} catch (InvalidDateException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(cancelSeat, "No Event found on selected date", "ERROR", JOptionPane.ERROR_MESSAGE);								
							}catch(Exception e1) {
								JOptionPane.showMessageDialog(cancelSeat, "Please input the correct format (DD/MM/YYYY) in integers", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
														
						}
						
						
					});
					
					
					top.add(submit);
					search.add(top, BorderLayout.NORTH);
					total.add(search);
					add(total, BorderLayout.CENTER);
					
					search.revalidate();
					search.repaint();
					
					
				} 
				
				
			}
	    	
	    });
	    
	    
	    JButton exit = new JButton("Exit");
	    
	    exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
		        System.exit(0);

				
			}
	    	
	    });
	    
	    menuButton.add(welcome);
	    menuButton.add(cb);
	    menuButton.add(ok);
	    menuButton.add(exit);
		this.revalidate();
		this.repaint();
	    this.add(menuButton, BorderLayout.NORTH);
	    
		this.revalidate();
		this.repaint();
	}
	
	
}
