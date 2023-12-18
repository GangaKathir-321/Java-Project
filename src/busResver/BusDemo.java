package busResver;

import java.util.Scanner;
import java.sql.*;

import java.util.ArrayList;

public class BusDemo {

	public static void main(String[] args)  {
		BusDAO busdao = new BusDAO();
	
     try {
    		busdao.displayBusInfo();
		int userOpt = 1;
		Scanner scanner = new Scanner(System.in);
		
		
		while (userOpt == 1) {
			System.out.println("Enter 1 to Book and 2 to exit");
			userOpt = scanner.nextInt();
			if (userOpt == 1){
			Booking booking = new Booking ();
			if(booking.isAvailable()) {
				BookingDAO bookingdao = new BookingDAO();
				bookingdao.addBooking(booking);
				System.out.println("your Booking is Confirmed");
				}
			else 
				System.out.println("Sorry Bus is Full,Try Another Bus");
			
			}

		}
		scanner.close();
	}catch(Exception e) {
		System.out.println(e);
	}
}
}