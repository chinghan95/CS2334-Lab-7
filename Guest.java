import java.time.LocalDateTime;

/**
 * A class that represents a single guest staying at a hotel. Guests have three 
 * fields: a name, check-in time, and room number.
 * @author Feras Salous
 * @version 02-29-2020
 *
 */
public class Guest {
	/**
	 * The name of the Guest checking in
	 */
	private String name;
	/**
	 * The time the Guest checked in
	 */
	private LocalDateTime timeCheckedIn;
	/**
	 * The room number of the Guest checking in
	 */
	private int roomNumber;
	
	/**
	 * Non-default constructor
	 * @param name
	 * @param timeCheckedIn
	 * @param roomNumber
	 */
	public Guest(String name, LocalDateTime timeCheckedIn, int roomNumber) {
		// Initialize the fields.  
		this.name = name;
		this.timeCheckedIn = timeCheckedIn;
		this.roomNumber = roomNumber;
	}
	
	/**
	 * Getter method 
	 * @return the name of Guest checking in
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Getter method 
	 * @return the time the Guest checked in
	 */
	public LocalDateTime getTimeCheckedIn() {
		return this.timeCheckedIn;
	}
	
	/**
	 * Getter method 
	 * @return the room number of the Guest checking in
	 */
	public int getRoomNumber() {
		return this.roomNumber;
	}
	
	/**
	 * Return a String representation of the Guest.
	 * @return A String with the following format: "<name> in Room <roomNumber>"
	 */
	@Override
	public String toString() {
		return this.name + " in Room " + this.roomNumber;
	}
}
