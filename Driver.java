import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

/**
 *  @author Feras Salous
 *  @version 02-29-2020
 */
public class Driver {

	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Java Hotel Guest Manager");
		System.out.println("------------------------");
		System.out.println();

		StackHotel stackHotel = new StackHotel("Stack Hotel");
		QueueHotel queueHotel = new QueueHotel("Queue Hotel");
		DateTimeFormatter df =  Hotel.FORMAT_HOTEL_TIME;

		boolean done = false;
		while(!done) {
			
			System.out.println("Select an option:");
			System.out.println("\t1. [check in] a guest.");
			System.out.println("\t2. [check out] a guest.");
			System.out.println("\t3. [check] the next Guest to be checked out");
			System.out.println("\t4. [quit]");
			String input = reader.readLine();
			
			if (input.equalsIgnoreCase("quit")) {
				done = true;
			}
			else if (input.equalsIgnoreCase("check in")) {

				System.out.print("Please enter a guest name: ");
				String name = reader.readLine();
				
				// Check the guest into the room with the same number as the 
				// next empty element in the stack or queue.
				int stackRoom = stackHotel.numGuestsCheckedIn();
				int queueRoom = queueHotel.getTail();
				Guest stackGuest = new Guest(name, LocalDateTime.now(), 
						stackRoom);
				Guest queueGuest = new Guest(name, LocalDateTime.now(), 
						queueRoom);
				
				if (stackHotel.addGuest(stackGuest)) {
					System.out.println(name + " added to Stack Hotel at index " 
							+ stackRoom + ".");
				}
				else {
					System.out.println("Stack Hotel is full!");
				}
				
				if (queueHotel.addGuest(queueGuest)) {
					System.out.println(name + " added to Queue Hotel at index " 
							+ queueRoom + ".");
				}
				else {
					System.out.println("Queue is full!");
				}
				System.out.println();
			}
			else if (input.equalsIgnoreCase("check out")) {
				
				System.out.println("Enter the checkout time in the following "
						+ "format: MM/dd/yyyy h:mma");
				System.out.println("Note: \"a\" is AM or PM.");
				String checkoutTime = reader.readLine();
				
				try {
					LocalDateTime parsedCheckoutTime = 
							LocalDateTime.parse(checkoutTime, df);
					System.out.println(stackHotel.checkOut(parsedCheckoutTime));
					System.out.println();
					System.out.println(queueHotel.checkOut(parsedCheckoutTime));
				}
				catch(DateTimeParseException e) {
					System.out.println("Invalid checkout time!");
				}
				System.out.println();
			}
			else if (input.equalsIgnoreCase("check"))
			{
				System.out.println("Stack Hotel");
				System.out.println("Number of guests: " 
						+ stackHotel.numGuestsCheckedIn());
				System.out.println("Next to check out: " 
						+ stackHotel.nextGuestToCheckOut());
				System.out.println();
				
				System.out.println("Queue Hotel");
				System.out.println("Number of guests: " 
						+ queueHotel.numGuestsCheckedIn());
				System.out.println("Next to check out: " 
						+ queueHotel.nextGuestToCheckOut());
				System.out.println();
			}
		}
	}
}
