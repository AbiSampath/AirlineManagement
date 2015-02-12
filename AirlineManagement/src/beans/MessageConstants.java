package beans;

import java.io.Serializable;

@SuppressWarnings("serial")
// error or success messages to be displayed
public class MessageConstants implements Serializable
{
	// Ex: No of seats exceeds the available:
	public static final String FLIGHT_SEAT_EXCEED = "Sorry customer,Numbers of seats exceeds the availability ";
	public static final String INVALID_LOGIN = "Invalid Credentials!!";
	public static final String REGESTERED_SUCCESSFULLY = "Registeration Successfull. Please login to continue.";
	public static final String RESERVED_SUCCESSFULLY = "BOOKED FLIGHT TICKET SUCCESSFULLY.";
	
	public static final String CANCELED_SUCCESSFULLY = "The Ticket has been cancelled successfully.";
	
	//Messages regarding Login
	public static final String LOGIN_SUCCESS = "Success, ";
	public static final String LOGIN_FAILURE = "Invalid Login";
	
	//Messages regarding Registration
	public static final String REGISTER_SUCCESS = "Registration failed. Email ID already exists.";
	public static final String REGISTER_FAILURE_EMAIL_EXIST = "Successfully Registered!";
	public static final String REGISTER_FAILURE_ERROR_UNKNOWN = "Registration failed. Unknown Error!!";
	
	
	//Messages for EMPLOYEE CLASS
	public static final String EMPLOYEE_NOT_FOUND = "Employee does not exist.";
	public static final String EMPLOYEE_INFO_UPDATE_SUCCESS = "Employee Information Updated Successfully.";
	public static final String EMPLOYEE_INFO_UPDATE_FAILURE = "Employee Information Not Updated!!!";
	public static final String EMPLOYEE_DELETE_SUCCESS = "Employee deleted successfully.";
	public static final String EMPLOYEE_DELETE_FAILURE = "Employee could not be deleted. Please try again.";
	
	//Messages for TRAVELLER CLASS
	public static final String TRAVELLER_NOT_FOUND = "Traveller does not exist.";
	public static final String TRAVELLER_INFO_UPDATE_SUCCESS = "Traveller Information Updated Successfully.";
	public static final String TRAVELLER_INFO_UPDATE_FAILURE = "Traveller Information Not Updated!!!";
	public static final String TRAVELLER_DELETE_SUCCESS = "Traveller deleted successfully.";
	public static final String TRAVELLER_DELETE_FAILURE = "Traveller could not be deleted. Please try again.";
	
	//updates
	public static final String FLIGHT_UPDATE_SUCCESS = "SUCCESSFULLY UPDATED THE FLIGHT INFORMATION";
	public static final String FLIGHT_UPDATE_FAILURE = "ERROR WHILE UPDATING THE FLIGHT INFORMATION. PLEASE TRY AGAIN";
	public static final String FLIGHT_DOESNOT_EXIST = "FLIGHT WITH GIVEN NUMBER DOES NOT EXIST";
	public static final String FLIGHT_NOT_UPDATED = "FLIGHT DETAILS NOT UPDATED";
	
	public static final String CUSTOMER_UPDATE_SUCCESS = "SUCCESSFULLY UPDATED THE CUSTOMER INFORMATION";
	public static final String CUSTOMER_UPDATE_FAILURE = "ERROR WHILE UPDATING THE CUSTOMER INFORMATION. PLEASE TRY AGAIN";
	public static final String CUSTOMER_DOESNOT_EXIST = "CUSTOMER WITH GIVEN EMAIL DOES NOT EXIST";
	
	public static final String NO_CUSTOEMRS = "NO CUSTOEMRS ON BOARD";
	
}
