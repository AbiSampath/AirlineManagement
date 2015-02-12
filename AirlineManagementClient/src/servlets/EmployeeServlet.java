package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Employee;
import beans.FlightDetails;
import beans.Reservation;
import beans.Traveller;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int task = Integer.parseInt(request.getParameter("task"));
		airlineSystem.AirlineServerProxy airlnproxy = new airlineSystem.AirlineServerProxy();
		// airlnproxy.setEndpoint("http://localhost:8080/CMPE_273_AirlineNEW/services/AirlineServer?wsdl");

		String userId = (String) session.getAttribute("userName");
		// AirlineServerProxy airlnproxy = new AirlineServerProxy();
		RequestDispatcher dispatch = null;
		String message = null;

		switch (task) {
		case 1: // CREATE NEW EMPLOYEE
			break;

		case 2: // DELETE EMPLOYEE
			break;

		case 3: // DELETE CUSTOMER
			break;

		case 4: // LIST EMPLOYEES
			Employee[] employees = airlnproxy.listAllEmployees();
			if (employees != null) {
				request.setAttribute("employees", employees);
				dispatch = request
						.getRequestDispatcher("Admin.jsp?task=listAllEmp");
			} else {
				message = "NO EMPLOYEE FOUND";
				dispatch = request.getRequestDispatcher("Admin.jsp?message="
						+ message);
			}
			dispatch.forward(request, response);

			break;

		case 5: // LIST CUSTOMERS
			Traveller[] travellers = airlnproxy.listAllCustomers();
			if (travellers != null) {
				request.setAttribute("straveller", travellers);
				dispatch = request
						.getRequestDispatcher("Admin.jsp?task=searchTravellerForAttrib");
			} else {
				message = "NO CUSTOMERS FOUND MATCHING THE CRITERIA";
				dispatch = request.getRequestDispatcher("Admin.jsp?message="
						+ message);
			}
			dispatch.forward(request, response);
			break;

		case 6: // LIST RESERVATIONS
			Reservation[] reservations = airlnproxy.listAllReservation();
			session.setAttribute("reservations", reservations);
			dispatch = request.getRequestDispatcher("Admin.jsp?task=6");
			dispatch.forward(request, response);
			break;

		case 7: // LIST FLIGHTS
			System.out.println("List All flights");
			FlightDetails[] flightsArray = airlnproxy.findAllFlights();
			System.out.println(flightsArray.length);
			request.setAttribute("flights", flightsArray);
			dispatch = request.getRequestDispatcher("Admin.jsp?task=7");
			dispatch.forward(request, response);
			break;

		case 8: // UPDATE CUSTOMER INFO
			// Traveller cand = new Traveller();
			//
			// //Populate the Traveler Class with the values to update
			// cand.setFirstName(request.getParameter("fname"));
			// cand.setLastName(request.getParameter("lname"));
			// cand.setAddress(request.getParameter("address"));
			// cand.setCity(request.getParameter("city"));
			// cand.setZipCode(request.getParameter("zip"));
			// cand.setEmailID(request.getParameter("email"));
			// cand.setPassportNo(request.getParameter("passport"));
			// cand.setNationality(request.getParameter("nationality"));
			//
			// message = airlnproxy.updateTravellerInfo(cand);
			// request.setAttribute("message", message);
			dispatch = request.getRequestDispatcher("Admin.jsp?task=8");
			dispatch.forward(request, response);
			break;

		case 9: // UPDATE EMPLOYEE INFO
			// Employee emp = new Employee();
			//
			// //Populate the Employee Class with the values to update
			// emp.setFirstName(request.getParameter("fname"));
			// emp.setLastName(request.getParameter("lname"));
			// emp.setAddress(request.getParameter("address"));
			// emp.setCity(request.getParameter("city"));
			// emp.setZipCode(request.getParameter("zip"));
			// emp.setEmailID(request.getParameter("email"));
			// emp.setWorkDescription(request.getParameter("work"));
			// emp.setPosition(request.getParameter("position"));
			//
			// message = airlnproxy.updateEmployeeInfo(emp);
			// request.setAttribute("message", message);
			dispatch = request.getRequestDispatcher("Admin.jsp?task=9");
			dispatch.forward(request, response);
			break;

		case 10: // UPDATE FLIGHT
			dispatch = request.getRequestDispatcher("Admin.jsp?task=10");
			dispatch.forward(request, response);
			break;

		case 11: // LIST ALL CUSTOMERS ON BOARD
			int flightNo = Integer.parseInt(request.getParameter("flightNo"));
			travellers = airlnproxy.getTravellersOnBoard(flightNo);
			if (travellers == null) {
				message = "NO CUSTOMERS ON BOARD FOR THIS FLIGHT";
				dispatch = request.getRequestDispatcher("Admin.jsp?message="
						+ message);
			} else {
				request.setAttribute("straveller", travellers);
				dispatch = request
						.getRequestDispatcher("Admin.jsp?task=searchTravellerForAttrib");
			}

			dispatch.forward(request, response);
			break;
		default: // INVALID OPTION
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Employee Servlet");
		HttpSession session = request.getSession();
		int task = Integer.parseInt(request.getParameter("task"));
		airlineSystem.AirlineServerProxy airlnproxy = new airlineSystem.AirlineServerProxy();
		// airlnproxy.setEndpoint("http://localhost:8080/CMPE_273_AirlineNEW/services/AirlineServer?wsdl");

		String userId = (String) session.getAttribute("userName");
		// AirlineServerProxy airlnproxy = new AirlineServerProxy();
		RequestDispatcher dispatch = null;
		String message = null;

		switch (task) {
		case 1: // CREATE NEW EMPLOYEE
			break;

		case 2: // DELETE EMPLOYEE
			break;

		case 3: // DELETE CUSTOMER
			break;

		case 4: // LIST EMPLOYEES
			break;

		case 5: // LIST CUSTOMERS
			break;

		case 6: // LIST RESERVATIONS
			// Reservation reservation = new Reservation();
			// request.setAttribute("reservations", reservation);
			// response.sendRedirect("Admin.jsp?task=1");
			//
			// Reservation[] reservations = airlnproxy.listAllReservation();
			// request.setAttribute("reservations", reservations);
			// dispatch = request.getRequestDispatcher("Admin.jsp?task=6");
			// dispatch.forward(request, response);
			break;

		case 7: // LIST FLIGHTS
			System.out.println("List All flights");
			FlightDetails[] flightsArray = airlnproxy.findAllFlights();
			System.out.println(flightsArray.length);
			request.setAttribute("flights", flightsArray);
			dispatch = request.getRequestDispatcher("Admin.jsp?task=7");
			dispatch.forward(request, response);
			break;

		case 8: // UPDATE CUSTOMER INFO
			Traveller cand = new Traveller();

			// Populate the Traveler Class with the values to update
			if (!"".equals(request.getParameter("fname"))) {
				cand.setFirstName(request.getParameter("fname"));
			}
			if (!"".equals(request.getParameter("lname"))) {
				cand.setLastName(request.getParameter("lname"));
			}
			if (!"".equals(request.getParameter("address"))) {
				cand.setAddress(request.getParameter("address"));
			}
			if (!"".equals(request.getParameter("city"))) {
				cand.setCity(request.getParameter("city"));
			}

			if (!"".equals(request.getParameter("zip"))) {
				cand.setZipCode(request.getParameter("zip"));
			}

			if (!"".equals(request.getParameter("email"))) {
				cand.setEmailID(request.getParameter("email"));
				cand.setCustomerId(request.getParameter("email"));
			}
			if (!"".equals(request.getParameter("dob"))) {
				cand.setDateOfBirth((request.getParameter("dob")));
			}
			if (!"".equals(request.getParameter("passport"))) {
				cand.setPassportNo(request.getParameter("passport"));
			}

			if (!"".equals(request.getParameter("nationality"))) {
				cand.setNationality(request.getParameter("nationality"));
			}

			if (!"".equals(request.getParameter("nationality"))) {
				String state = request.getParameter("selectedState");
				cand.setState(state);
			}

			message = airlnproxy.updateTravellerInfo(cand);
			request.setAttribute("message", message);
			dispatch = request.getRequestDispatcher("Admin.jsp?message="
					+ message);
			dispatch.forward(request, response);
			break;

		case 9: // UPDATE EMPLOYEE INFO
			Employee emp = new Employee();
			String empID = request.getParameter("empId");

			empID = empID.replaceAll("-", "");
			// Populate the Employee Class with the values to update
			emp.setEmployeeID(Integer.parseInt(empID));
			emp.setFirstName(request.getParameter("fname"));
			emp.setLastName(request.getParameter("lname"));
			emp.setAddress(request.getParameter("address"));
			emp.setCity(request.getParameter("city"));
			emp.setZipCode(request.getParameter("zip"));
			emp.setEmailID(request.getParameter("email"));
			emp.setWorkDescription(request.getParameter("workDes"));
			emp.setPosition(request.getParameter("position"));
			emp.setDateOfBirth(request.getParameter("DOB"));
			emp.setState(request.getParameter("selectedState"));

			message = airlnproxy.updateEmployeeInfo(emp);

			request.setAttribute("message", message);
			dispatch = request.getRequestDispatcher("Message.jsp");
			dispatch.forward(request, response);
			break;

		case 10: // UPDATE FLIGHT
			FlightDetails flight = new FlightDetails();
			flight.setFlightNumber(Integer.parseInt(request
					.getParameter("flightNo")));
			if (!"".equals(request.getParameter("flightName"))) {
				flight.setAirlineName(request.getParameter("flightName"));
			}
			if (!"".equals(request.getParameter("source"))) {
				flight.setSource(request.getParameter("source"));
			}
			if (!"".equals(request.getParameter("destination"))) {
				flight.setDestination(request.getParameter("destination"));
			}
			if (!"".equals(request.getParameter("flightTime"))) {
				flight.setFlightTime(request.getParameter("flightTime"));
			}
			if (request.getParameter("seats") != null) {
				if (!request.getParameter("seats").equals("")) {
					flight.setNumberOfSeats(Integer.parseInt(request
							.getParameter("seats")));
				}
			}
			message = airlnproxy.updateFlightDetails(flight);
			dispatch = request.getRequestDispatcher("Admin.jsp?message="
					+ message);
			dispatch.forward(request, response);
			break;

		case 11: // SEARCH FLIGHT
			flight = new FlightDetails();
			if (request.getParameter("flightNo") != null
					&& !"".equals(request.getParameter("flightNo"))) {
				flight.setFlightNumber(Integer.parseInt(request
						.getParameter("flightNo")));
			}
			if (!"".equals(request.getParameter("flightName"))) {
				flight.setAirlineName(request.getParameter("flightName"));
			}
			if (!"".equals(request.getParameter("source"))) {
				flight.setSource(request.getParameter("source"));
			}
			if (!"".equals(request.getParameter("destination"))) {
				flight.setDestination(request.getParameter("destination"));
			}
			if (!"".equals(request.getParameter("flightTime"))) {
				flight.setFlightTime(request.getParameter("flightTime"));
			}

			FlightDetails[] flights = airlnproxy.findFlights(flight);
			if (flights != null) {
				request.setAttribute("sflights", flights);
				dispatch = request
						.getRequestDispatcher("Admin.jsp?task=searchFlightForAttrib");
			} else {
				message = "NO FLIGHTS FOUND MATCHING THE CRITERIA";
				dispatch = request.getRequestDispatcher("Admin.jsp?message="
						+ message);
			}
			dispatch.forward(request, response);
			break;
		case 12: // SEARCH Traveller
			Traveller traveller = new Traveller();
			if (request.getParameter("firstName") != null
					&& !"".equals(request.getParameter("firstName"))) {
				traveller.setFirstName(request.getParameter("firstName"));
			}
			if (!"".equals(request.getParameter("lastName"))) {
				traveller.setLastName(request.getParameter("lastName"));
			}
			if (!"".equals(request.getParameter("emailID"))) {
				traveller.setEmailID(request.getParameter("emailID"));
			}
			if (!"".equals(request.getParameter("passportNo"))) {
				traveller.setPassportNo(request.getParameter("passportNo"));
			}
			if (!"".equals(request.getParameter("travellerId"))) {
				traveller.setTravellerId(Integer.parseInt(request
						.getParameter("travellerId")));
			}
			if (!"".equals(request.getParameter("nationality"))) {
				traveller.setNationality(request.getParameter("nationality"));
			}
			Traveller[] travellers = airlnproxy.findTravellers(traveller);
			if (travellers != null) {
				request.setAttribute("straveller", travellers);
				dispatch = request
						.getRequestDispatcher("Admin.jsp?task=searchTravellerForAttrib");
			} else {
				message = "NO TRAVELLER FOUND MATCHING THE CRITERIA";
				dispatch = request.getRequestDispatcher("Admin.jsp?message="
						+ message);
			}
			dispatch.forward(request, response);
			break;
		default: // INVALID OPTION
			break;
		}
	}

}
