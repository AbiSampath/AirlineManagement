package airlineSystem;

import javax.jws.WebService;

import beans.Employee;
import beans.FlightDetails;
import beans.Person;
import beans.Reservation;
import beans.Traveller;

@WebService
public class AirlineServer
{
	public String[] fetchStateList()
	{
		String[] stateArray = null;
		stateArray = ModelController.getInstance().fetchStateList();

		return stateArray;
	}

	public String registerCustomer(Person person)
	{
		String message = null;
		message = ModelController.getInstance().registerCustomer(person);

		return message;
	}

	public String deleteCustomer(String emailID, int roleID)
	{
		String message = null;
		message = ModelController.getInstance().deleteCustomer(emailID, roleID);
		return message;
	}

	public String login(String userName, String password)
	{
		String message = null;
		message = ModelController.getInstance().login(userName, password);

		return message;
	}

	public String addEmployee(Employee employee)
	{
		String message = null;
		message = ModelController.getInstance().addEmployee(employee);

		return message;
	}

	public String deleteEmployee(String emailID)
	{
		String message = null;

		return message;
	}

	// Added by Pradyumna
	public String createNewReservation(FlightDetails journeyDetails, Traveller travellerInfo)
	{
		String message = ModelController.getInstance().reserveTicket(journeyDetails, travellerInfo);
		return message;
	}

	public String cancelReservation(String userID, int reservationID)
	{
		return ModelController.getInstance().cancelTicket(userID, reservationID);
	}

	public Reservation issueTicket(String userId, int reservationID)
	{
		return ModelController.getInstance().issueTicket(userId, reservationID);
	}

	public Reservation[] getBookedTickets(String userID,int issued)
	{
		Reservation[] jDetials = ModelController.getInstance().getAllReservations(userID,issued);
		;
		return jDetials;
	}

	// End of code added by Pradyumna

	public String processPayement(String cardID)
	{
		String message = null;

		return message;
	}

	public Employee[] listAllEmployees()
	{
		Employee[] employeeArray = ModelController.getInstance().listAllEmployees();

		return employeeArray;
	}

	// GEt 1 or many traveller information
	public Traveller[] listAllCustomers()
	{
		Traveller[] travellerArray = ModelController.getInstance().searchTraveller(null);

		return travellerArray;
	}

	public Reservation[] listAllReservation()
	{
		return ModelController.getInstance().listAllReservations();
	}

	// Start: Code added by Pradyumna
	public FlightDetails[] listAllFlights(String source, String destination, String flightTime)
	{
		FlightDetails[] flightArray = ModelController.getInstance().searchFlightForSourceAndDest(source, destination,
				flightTime);

		return flightArray;
	}

	// End of code added by Pradyumna

	/**
	 * UPDATE TRAVELLER INFO: RUSHAB
	 * 
	 * @param traveller
	 * @return
	 */
	public String updateTravellerInfo(Traveller traveller)
	{
		return ModelController.getInstance().updateTravellerInfo(traveller);
	}

	public String updateEmployeeInfo(Employee emp)
	{
		return ModelController.getInstance().updateEmployeeInfo(emp);
	}

	/**
	 * UPDATE FLIGHT DETAILS: PRADYUMNA
	 * 
	 * @param flight
	 * @return
	 */
	public String updateFlightDetails(FlightDetails flight)
	{
		String message = ModelController.getInstance().updateFlightDetails(flight);
		return message;
	}

	public Employee[] searchEmployeeForID(int empID, String workDesc, String hireDate)
	{
		Employee[] employeeArray = null;

		employeeArray = ModelController.getInstance().searchEmployeeForID(empID, workDesc, hireDate);
		return employeeArray;
	}

	public Employee findEmployee(Employee emp)
	{
		Employee employee = null;
		return employee;
	}

	public FlightDetails[] findFlights(FlightDetails flight)
	{
		FlightDetails[] flightDetails = ModelController.getInstance().searchFlight(flight);
		return flightDetails;
	}

	public Traveller[] findPassengersOnBoard(FlightDetails flight)
	{
		Traveller[] travellerArray = null;

		return travellerArray;
	}

//	public Traveller[] searchTravelers(int travelerID)
//	{
//		System.out.println("Entering Airline Server" + travelerID);
//		Traveller[] travellerArray = null;
//
//		travellerArray = ModelController.getInstance().searchTravelers(travelerID);
//		System.out.println("returning" + travellerArray);
//		return travellerArray;
//	}
	
	public Traveller[] findTravellers(Traveller traveller)
	{
		System.out.println("entering airlineserver" +traveller);
		Traveller[] travellerDetails = ModelController.getInstance().searchTraveller(traveller);
		System.out.println("returning" + travellerDetails);
		return travellerDetails;
	}
	
	
	public Traveller[] getTravellersOnBoard(int flightNo){
		return ModelController.getInstance().listAllCustomersOnBoard(flightNo);
	}
	public FlightDetails[] findAllFlights()
	{
		return ModelController.getInstance().myCache.getAllFlights();
	}

	public void buildCache()
	{
		ModelController.getInstance().buildFlightCache();
	}

}
