package busResver;
import java.util.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Booking {
	
  String passengerName;
  int busNo;
  Date date;
  
  Booking(){
	  Scanner scanner  = new Scanner(System.in);
	  System.out.println("Enter name of Passenger: ");
	  passengerName = scanner.next();
	  System.out.println("Enter Bus No: ");
	  busNo= scanner.nextInt();
	  System.out.println("Enter Date dd-mm-yyyy: ");
	  String dataInput = scanner.next();
	  SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	  try {
		date = dateFormat.parse(dataInput);
	} catch (ParseException e) {
		e.printStackTrace();
	}
  }
 public boolean isAvailable() throws SQLException {
	 BusDAO busdao = new BusDAO();
	 BookingDAO bookingdao = new BookingDAO();
	 
  int capacity=busdao.getCapacity(busNo);
  
  int booked= bookingdao.getBookedCount(busNo,date);
 
  return booked<capacity;
  }
 
 }


  


