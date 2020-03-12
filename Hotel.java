import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An abstract class that stores information about guests in a hotel. The 
 * concrete classes StackHotel and QueueHotel implement the abstract methods of 
 * this class using different data structures (stack and queue).
 * @author Feras Salous
 * @version 02-29-2020
 */
public abstract class Hotel { 
	/**
	 * The name of the hotel.
	 */
	private String hotelName;
	
	/**
	 * Define your own custom patterns
	 */
	public static final DateTimeFormatter FORMAT_HOTEL_TIME = 
			DateTimeFormatter.ofPattern("MM/dd/yyyy h:mma");

	/**
	 * Non-default constructor
	 * @param hotelName
	 */
	protected Hotel(String hotelName) {
		this.hotelName = hotelName;
	}

	/**
	 * Add a Guest to the Hotel if there is room.
	 * checkOut() is completed.
	 * @param guest the new Guest
	 * @return true if the guest is added; false otherwise.
	 */
	public abstract boolean addGuest(Guest guest);

	/**
	 * Return the number of guests currently checked into the hotel.
	 * @return the number of guests added to the hotel that have not been 
	 * checked out
	 */
	public abstract int numGuestsCheckedIn();
	
	/**
	 * Return the Guest that will check out next. (Leave the Hotel unchanged.)
	 * If no Guests are checked in, return null.
	 * 
	 * StackHotel and QueueHotel use FIFO and FILO rules respectively to decide 
	 * which Guest will check out next.
	 * @return the next Guest to check out
	 */
	public abstract Guest nextGuestToCheckOut();

	/**
	 * Remove and return the next Guest from the Hotel. If the Hotel is empty, 
	 * return null.
	 * 
	 * StackHotel and QueueHotel have different implementations of this method. 
	 * A StackHotel checks out the most recent Guest added to the Hotel. A 
	 * QueueHotel checks out the Guest that has been checked in the longest.
	 * 
	 * @return the Guest removed from the Hotel
	 */
	public abstract Guest checkOut();

	/**
	 * Remove the next Guest from the Hotel and return a String with 
	 * information about the duration of the Guest's stay.
	 * 
	 * @param timeCheckedOut the date and time when the Guest checked out
	 * @return A String with the following format:
	 *         "Hotel Name: <hotelName>
	 *          Guest Checking out: <guest.toString()>
	 *          Length of Stay: <Number of days at the hotel>
	 *          Checked In: <timeCheckedIn> Checked Out: <timeCheckedOut>"
	 * 
	 *         If the Hotel is empty, the String has this format:
	 *         "No rooms are currently taken at the <hotelName>"
	 */
	public String checkOut(LocalDateTime timeCheckedOut) {
		
		// Remove the next Guest and assign it to a reference named 
		// "guestCheckedOut".
		Guest guestCheckedOut = checkOut();
		
		StringBuilder sb = new StringBuilder();
		if (guestCheckedOut == null) {
			sb.append("No rooms are currently taken at the " + this.hotelName);
			return sb.toString();
		}
		
		// Get the time the Guest checked in and assign it to a reference 
		// named "timeCheckedIn".
		LocalDateTime timeCheckedIn = guestCheckedOut.getTimeCheckedIn();

		// Format timeCheckedIn using FORMAT_HOTEL_TIME. (See the API 
		// documentation for LocalDateTime.) Assign the result to a String 
		// reference named "formattedTimeCheckedIn".
		//
		// Note: The toString method of LocalDateTime objects outputs the date 
		// and time in a format like this: 2016-11-09T11:44:44.797. This step 
		// outputs the information in the format mm/DD/yyyy h:mma.  
		String formattedTimeCheckedIn = timeCheckedIn.format(FORMAT_HOTEL_TIME);
		
		// Format timeCheckedOut and assign it to a reference named 
		// "formattedTimeCheckedOut".
		String formattedTimeCheckedOut = timeCheckedOut.format(FORMAT_HOTEL_TIME);
				
		// Calculate the duration of the Guest's stay between check in 
		// and check out. Use a method of the Duration class:
		// https://docs.oracle.com/javase/8/docs/api/java/time/Duration.html 
		// Assign the resulting object to a reference named "durationOfStay".
		Duration durationOfStay = Duration.between(timeCheckedIn, timeCheckedOut);
		
		// LEAVE THE FOLLOWING CODE UNCHANGED (please).
		sb.append("Hotel Name: " + this.hotelName + "\n");
		sb.append("Guest Checking Out: " + guestCheckedOut.toString() + "\n");
		sb.append("Length of Stay: ");
		if (durationOfStay.toDays() == 1) {
			sb.append(durationOfStay.toDays() + " Day" + "\n");
		} else {
			sb.append(durationOfStay.toDays() + " Days" + "\n");
		}
		sb.append("Checked In: " + formattedTimeCheckedIn + " Checked Out: " + formattedTimeCheckedOut);

		return sb.toString();
	}
}