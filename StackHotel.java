/**
 * An implementation of the abstract class Hotel that uses a stack to store 
 * Guest objects. The last Guest to check in is the first to check out (LIFO).
 * @author Feras Salous
 * @version 02-29-2020
 */
public class StackHotel extends Hotel {
	
	private int numGuests; // This can also called the top of stack;
	private Guest[] guests;
	private static final int MAX_GUESTS = 10;
	
	public StackHotel(String hotelName) {
		
		// Initialize all non-static fields.
		super(hotelName);
		this.numGuests = 0;
		this.guests = new Guest[MAX_GUESTS];
	}
	
	/**
	 * This method can be also called push().
	 * Add a Guest to the next empty element of the stack. If the stack is 
	 * full, leave it unchanged.
	 * @return true if the Guest was added; false otherwise.
	 */
	@Override
	public boolean addGuest(Guest guest) {
		
		// If the stack is full, don't change the array, and return false.
		if (this.numGuests >= MAX_GUESTS) {
			//System.out.println("Stack Overflow");
			return false;
		}
		// Add Guest object to the next empty element, and return true.
		else {
			this.guests[this.numGuests++] = guest;
			return true;
		}
	}
	
	/**
	 * Return the number of guests currently checked into the hotel.
	 * @return the number of guests
	 */
	@Override
	public int numGuestsCheckedIn() {
		return this.numGuests;
		
	}
	
	/**
	 * This method can be also called peek().
	 * Return the Guest that will check out next. (Leave the stack unchanged.)
	 * If no Guests are checked in, return null.
	 * @return the Guest at the top of the stack
	 */
	@Override
	public Guest nextGuestToCheckOut() {
		// LIFO
		// If number of guests is negative, it is stack underflow.
		if (this.numGuests < 0) {
			//System.out.println("Stack Underflow");
			return null;
		}
		// If no Guests are checked in, return null.
		if (this.numGuests == 0) {
			return null;
		}
		// Return the top of the stack.
		else {
			Guest nextGuest = this.guests[this.numGuests - 1];
			return nextGuest;
		}
	}
	
	/**
	 * This method can be also called pop().
	 * Remove and return the Guest at the top of the stack. If the stack is 
	 * empty, return null.
	 * @return the Guest removed from the top of the stack
	 */
	@Override
	public Guest checkOut() {
		
		// If number of guests is negative, it is stack underflow.
		if (this.numGuests < 0) {
			//System.out.println("Stack Underflow");
			return null;
		}
		// If no Guests are checked in, return null.
		if (this.numGuests == 0) {
			return null;
		}
		// Return and remove the top of the stack.
		else {
			Guest guestToCheckout = this.guests[this.numGuests - 1];
			this.numGuests--;
			return guestToCheckout;
		}
	}
}
