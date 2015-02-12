package servlets;

import java.io.IOException;
import java.rmi.RemoteException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import airlineSystem.AirlineServerProxy;
import beans.FlightDetails;
import beans.Reservation;
import beans.Traveller;

/**
 * Servlet implementation class TravelServlet THIS SERVLET HANDLES REQUEST
 * RELATED TO TRAVEL SUCH AS RESERVING TICKET,ISSUING,SEARCHING TRAVELLERS AND
 * RESERVATIONS
 */
@WebServlet("/TravelServlet")
public class TravelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TravelServlet() {
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
		String task = request.getParameter("task");
		AirlineServerProxy airlnproxy = new AirlineServerProxy();
		String userId = (String) session.getAttribute("userName");
		String message = null;
		if ("cancel".equals(task)) {
			session.setAttribute("reservations",
					getReservations(session, userId, airlnproxy));
			RequestDispatcher dispatch = request
					.getRequestDispatcher("MyAccount.jsp?task=cancel");
			dispatch.forward(request, response);
		} else if ("ticket".equals(task)) {
			String reservID = request.getParameter("reservID");
			session.setAttribute("reservID", reservID);
			RequestDispatcher dispatch = request
					.getRequestDispatcher("MyAccount.jsp?task=issueTicket");
			dispatch.forward(request, response);
		}

		else if ("searchTraveller".equals(task)) {
			// To display seach div
			RequestDispatcher dispatch = request
					.getRequestDispatcher("Admin.jsp?task=searchTraveller");
			dispatch.forward(request, response);
		} else if ("getTickets".equals(task)) {
			Reservation[] reservation = airlnproxy.getBookedTickets(userId, 1);
			request.setAttribute("tickets", reservation);
			RequestDispatcher dispatch = null;
			if (reservation != null) {
				dispatch = request
						.getRequestDispatcher("MyAccount.jsp?task=showReservations");
			} else {
				message = "No booked tickets found for the user";
				dispatch = request
						.getRequestDispatcher("MyAccount.jsp?message="
								+ message);
			}
			dispatch.forward(request, response);
		} else if ("getReservations".equals(task)) {
			Reservation[] reservation = airlnproxy.getBookedTickets(userId, 0);
			RequestDispatcher dispatch = null;
			request.setAttribute("tickets", reservation);
			if (reservation != null) {
				dispatch = request
						.getRequestDispatcher("MyAccount.jsp?task=showReservations&toBeIssued=true");
			} else {
				message = "No reservations found for the user";
				dispatch = request
						.getRequestDispatcher("MyAccount.jsp?message="
								+ message);
			}
			dispatch.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String task = request.getParameter("task");
		String userId = (String) session.getAttribute("userName");

		AirlineServerProxy airlnproxy = new AirlineServerProxy();
		if ("search".equals(task)) {
			String source = request.getParameter("source");
			String destination = request.getParameter("destination");
			String flightTime = request.getParameter("time");
			int numberOfSeats = Integer.parseInt(request
					.getParameter("noOfSeats"));
			session.setAttribute("noOfSeats", numberOfSeats);
			FlightDetails[] flights = airlnproxy.listAllFlights(source,
					destination, flightTime);
			session.setAttribute("flights", flights);
			RequestDispatcher dispatch = request
					.getRequestDispatcher("Home.jsp?task=search");
			dispatch.forward(request, response);
		} else if ("reserve".equals(task)) {
			// Reserve Ticekt
			if (session.getAttribute("userName") != null) {

				FlightDetails[] flights = (FlightDetails[]) session
						.getAttribute("flights");
				int flightCnt = Integer.parseInt(request
						.getParameter("flightCnt"));
				FlightDetails flight = flights[flightCnt];
				int seatsCnt = (Integer) session.getAttribute("noOfSeats");
				if (seatsCnt > flight.getNumberOfSeats()) {
					String message = "Sorry customer,Numbers of seats exceeds the availability";
					request.setAttribute("message", message);
					RequestDispatcher dispatch = request
							.getRequestDispatcher("Message.jsp");
					dispatch.forward(request, response);
				} else {
					flight.setNumberOfSeats(seatsCnt);
					session.setAttribute("reservedFlight", flight);
					RequestDispatcher dispatch = request
							.getRequestDispatcher("Home.jsp?task=confirmReservaton");
					dispatch.forward(request, response);
				}

			} else {
				response.sendRedirect("Login.jsp");
			}
		} else if ("confirmReservation".equals(task)) {
			FlightDetails flight = (FlightDetails) session
					.getAttribute("reservedFlight");
			Traveller travellerInfo = new Traveller();
			travellerInfo.setNationality(request.getParameter("nationality"));
			travellerInfo.setPassportNo(request.getParameter("passport"));
			travellerInfo.setEmailID(userId);
			String success = airlnproxy.createNewReservation(flight,
					travellerInfo);
			RequestDispatcher dispatch = request
					.getRequestDispatcher("MyAccount.jsp?message=" + success);
			dispatch.forward(request, response);
		} else if ("cancelTicket".equals(task)) {
			int reservationID = Integer.parseInt((String) request
					.getParameter("reservID"));
			String message = airlnproxy
					.cancelReservation(userId, reservationID);
			RequestDispatcher dispatch = request
					.getRequestDispatcher("MyAccount.jsp?message=" + message);
			dispatch.forward(request, response);
		} else if ("issueTicket".equals(task)) {
			int reservationID = Integer.parseInt((String) session
					.getAttribute("reservID"));
			Reservation ticket = airlnproxy.issueTicket(userId, reservationID);
			request.setAttribute("ticket", ticket);
			RequestDispatcher dispatch = request
					.getRequestDispatcher("MyAccount.jsp?task=showTicket");
			dispatch.forward(request, response);
		}
	}

	/**
	 * @param session
	 * @param userId
	 * @param airlnproxy
	 * @throws RemoteException
	 */
	private Reservation[] getReservations(HttpSession session, String userId,
			AirlineServerProxy airlnproxy) throws RemoteException {
		Reservation[] reservations = airlnproxy.getBookedTickets(userId, 0);
		return reservations;
	}

}
