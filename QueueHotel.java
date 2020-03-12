/**
 * An implementation of the abstract class Hotel that uses a queue to store 
 * Guest objects. The first Guest to check in is the first to check out (FIFO).
 * @author Feras Salous
 * @version 02-29-2020
 */
public class QueueHotel extends Hotel {
	
	/**
	 * The index of the oldest element in the queue.
	 */
	private int head;
	/**
	 * The index of the next empty element in the queue. If the queue is empty 
	 * or full, tail is equal to head.
	 */
	private int tail;
	private int numGuests;
	private Guest[] guests;
	private static final int MAX_GUESTS = 10;
	
	/**
	 *  Initialize all non-static fields.
	 * @param hotelName
	 */
	public QueueHotel(String hotelName) {
		super(hotelName);
		this.head = 0;
		this.tail = 0;
		this.guests = new Guest[MAX_GUESTS];
	}
	
	/**
	 * Return the index of the oldest element in the queue.
	 * @return head
	 */
	public int getHead() {
		return this.head;
	}
	
	/**
	 * Return the index of the next empty element in the queue.
	 * @return tail
	 */
	public int getTail() {
		return this.tail;
	}
	
	/**
	 * Add a Guest to the next empty element of the queue. If the queue is 
	 * full, leave it unchanged.
	 * @return true if the Guest was added; false otherwise.
	 */
	@Override
	public boolean addGuest(Guest guest) {
		// Hint: Use tail to find the next empty element. Don't forget to 
		// update tail and numGuests after modifying the array. What should 
		// happen if the new value of tail is larger than the last index?
		if (this.numGuests < MAX_GUESTS) {
			guests[this.tail++] = guest;
			
			if(this.tail == MAX_GUESTS)
				this.tail = 0;
			
			this.numGuests++;
			return true;
		}
		else {
			return false;
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
	 * Return the Guest that will check out next. (Leave the queue unchanged.)
	 * If no Guests are checked in, return null.
	 * @return the Guest at the head of the queue
	 */
	@Override
	public Guest nextGuestToCheckOut() {
		// FIFO
		if (this.numGuests == 0)
			return null;
		
		else {
			return this.guests[this.head];
		}
	}
	
	/**
	 * Remove and return the Guest at the head of the queue. If the queue is 
	 * empty, return null.
	 * @return the Guest removed from the head of the queue
	 */
	@Override
	public Guest checkOut() {
		// Hint: Don't forget to update head and numGuests after modifying the 
		// array. What should happen if the new value of head is larger than 
		// the last index?
		if (this.numGuests == 0)
			return null;
		
		else {
			if (this.head >= MAX_GUESTS) {
				this.head = 0;
			}
			this.numGuests--;
			return this.guests[this.head++];
		}
	}
}
