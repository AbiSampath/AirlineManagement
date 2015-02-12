package airlineSystem;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import sun.misc.BASE64Encoder;
import beans.Employee;
import beans.FlightDetails;
import beans.MessageConstants;
import beans.Person;
import beans.Reservation;
import beans.Traveller;
import connection.EstablishConnection;

public class ModelController {
	static ModelController instance = new ModelController();
	CustomCache myCache = new CustomCache();

	private ModelController() {
	}

	public static ModelController getInstance() {
		return instance;
	}

	public boolean buildFlightCache() {
		boolean result = false;

		EstablishConnection connectionClass = null;
		Connection connection = null;

		System.out.println("Starting to build Cache");
		try {
			connectionClass = new EstablishConnection();
			connection = connectionClass.getConnection();
			connection.setAutoCommit(false);

			String query = "Select * from flight_details";

			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt.executeQuery(query);

			while (resultSet.next()) {
				FlightDetails flightObject = new FlightDetails();

				flightObject.setFlightNumber(resultSet.getInt("flightNumber"));
				flightObject.setAirlineName(resultSet.getString("airlineName"));
				;
				flightObject.setCrewId(resultSet.getInt("crewID"));
				flightObject.setDestination(resultSet.getString("destination"));
				flightObject.setNumberOfSeats(resultSet.getInt("noOfSeats"));
				flightObject.setSource(resultSet.getString("source"));
				flightObject.setFlightTime(resultSet.getString("flightTime"));

				String flightKey = resultSet.getString("source") + "-"
						+ resultSet.getString("destination") + "-"
						+ resultSet.getString("flightTime");

				System.out.println("flightKey " + flightKey);
				String flightValue = myCache.getFlightKeyMap().get(flightKey);

				if (flightValue != null) {
					flightValue = flightValue + ","
							+ resultSet.getInt("flightNumber");
					System.out.println("flightValue " + flightValue);
				} else {
					flightValue = resultSet.getInt("flightNumber") + "";
				}

				myCache.getFlightKeyMap().put(flightKey, flightValue);
				myCache.getFlightCache().put(
						resultSet.getInt("flightNumber") + "", flightObject);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public void updateCache(int flightNo) {
		EstablishConnection connectionClass = null;
		Connection connection = null;

		System.out.println("Update Flight Cache");

		try {
			connectionClass = new EstablishConnection();
			connection = connectionClass.getConnection();
			connection.setAutoCommit(false);

			String query = "Select * from flight_details where flightNumber="
					+ flightNo;
			Statement stmt = connection.prepareStatement(query);

			ResultSet result = stmt.executeQuery(query);

			String flightIDs = null;
			String mapKey = null;
			while (result.next()) {
				mapKey = result.getString("source") + "-"
						+ result.getString("destination") + "-"
						+ result.getString("flightTime");
				FlightDetails flightObject = new FlightDetails();

				flightObject.setFlightNumber(result.getInt("flightNumber"));
				flightObject.setAirlineName(result.getString("airlineName"));
				;
				flightObject.setCrewId(result.getInt("crewID"));
				flightObject.setDestination(result.getString("destination"));
				flightObject.setNumberOfSeats(result.getInt("noOfSeats"));
				flightObject.setSource(result.getString("source"));
				flightObject.setFlightTime(result.getString("flightTime"));

				if (flightIDs == null) {
					flightIDs = result.getInt("flightNumber") + "";
				} else {
					flightIDs += result.getInt("flightNumber") + "";

				}

				myCache.flightCache.put(result.getInt("flightNumber") + "",
						flightObject);
			}

			myCache.flightKeyMap.put(mapKey, flightIDs);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void populateFlightDetails() {
		EstablishConnection connectionClass = null;
		Connection connection = null;

		System.out.println("Populating flight details");
		try {
			connectionClass = new EstablishConnection();
			connection = connectionClass.getConnection();
			connection.setAutoCommit(false);

			String query = "insert into flight_details(airlineName, source, destination, noOfSeats, flightTime,crewId) values(?,?,?,?,?,?)";

			PreparedStatement stmt = connection.prepareStatement(query);

			Random random = new Random();

			for (int i = 1; i < 5001; i++) {
				int hr = random.nextInt(24);
				String time = "";
				if (hr < 10) {
					time += "0";
				}
				time += hr + ":00";
				stmt.setString(1, "flight" + i);
				stmt.setString(2, "source" + random.nextInt(20));
				stmt.setString(3, "destination" + random.nextInt(20));
				stmt.setInt(4, 100);
				stmt.setString(5, time);
				stmt.setInt(6, 25);
				// stmt.addBatch();

				// if (i % 200 == 0)
				stmt.execute();
			}

			connectionClass.endConnection(connection);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Added by Darshan -- Encrypts the input String (Password)
	 */
	public static synchronized String encrypt(String plaintext)
			throws Exception {
		MessageDigest msgDigest = null;
		String algorithm = "SHA";
		String encoding = "UTF-8";
		String hashValue = null;
		try {
			msgDigest = MessageDigest.getInstance(algorithm);
			msgDigest.update(plaintext.getBytes(encoding));
			byte rawByte[] = msgDigest.digest();
			hashValue = (new BASE64Encoder()).encode(rawByte);

		} catch (NoSuchAlgorithmException e) {
			System.out.println("No Such Algorithm Exists");
		} catch (UnsupportedEncodingException e) {
			System.out.println("The Encoding Is Not Supported");
		}
		return hashValue;
	}

	/*
	 * Added by Darshan -- Returns the list of valid states
	 */
	public String[] fetchStateList() {
		String[] stateList = null;
		EstablishConnection connectionClass = null;
		Connection connection = null;

		try {
			connectionClass = new EstablishConnection();
			connection = connectionClass.getConnection();
			connection.setAutoCommit(false);

			String query = "SELECT stateName from state_info";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			resultSet.last();
			int count = resultSet.getRow();
			resultSet.beforeFirst();

			if (count > 0) {
				stateList = new String[count];
				int i = 0;

				while (resultSet.next()) {
					stateList[i++] = resultSet.getString(1);
				}

			}

			return stateList;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stateList;
	}

	/*
	 * Added by Darshan -- Performs the Login operation
	 */
	public String login(String userName, String password) {
		String message = null;

		EstablishConnection connectionClass = null;
		Connection connection = null;

		try {
			connectionClass = new EstablishConnection();
			connection = connectionClass.getConnection();
			connection.setAutoCommit(false);

			String query = "SELECT PASSWORD, ROLEID, FIRSTNAME FROM PERSON WHERE EMAILID = ?";

			PreparedStatement prepStmt = connection.prepareStatement(query);

			prepStmt.setString(1, userName);
			ResultSet result = prepStmt.executeQuery();

			if (!result.next()) {
				message = "Invalid Login";
				return message;
			} else {
				String encryptedPassword = encrypt(password);
				String savedPassword = result.getString("password");
				String firstName = result.getString("firstName");
				int roleID = result.getInt("roleID");

				if (encryptedPassword.equals(savedPassword)) {
					message = "Success," + firstName + "," + roleID;
					connectionClass.endConnection(connection);
					System.out.println("message " + message);
					return message;
				} else {
					message = "Invalid Login";
					connectionClass.endConnection(connection);
					return message;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return message;
	}

	/*
	 * Added by Darshan -- Registers a new customer
	 */
	public String registerCustomer(Person person) {
		String message = null;

		EstablishConnection connectionClass = null;
		Connection connection = null;

		try {
			connectionClass = new EstablishConnection();
			connection = connectionClass.getConnection();
			connection.setAutoCommit(false);

			String emailID = person.getEmailID();

			String query1 = "Select count(1) from person where emailID = ?";
			PreparedStatement preparedStmt = connection
					.prepareStatement(query1);

			preparedStmt.setString(1, emailID);
			ResultSet result1 = preparedStmt.executeQuery();

			while (result1.next()) {
				if (result1.getInt(1) > 0) {
					message = "This email ID already exists. ";
					return message;
				}
			}

			String query2 = "Insert into person (firstName, lastName, address, city, zipCode, dateOfBirth, roleID, emailID, password, state) values (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStat2 = connection
					.prepareStatement(query2);

			preparedStat2.setString(1, person.getFirstName());
			preparedStat2.setString(2, person.getLastName());
			preparedStat2.setString(3, person.getAddress());
			preparedStat2.setString(4, person.getCity());
			preparedStat2.setString(5, person.getZipCode());
			preparedStat2.setString(6, person.getDateOfBirth());
			preparedStat2.setInt(7, person.getRoleID());

			preparedStat2.setString(8, person.getEmailID());
			preparedStat2.setString(9, encrypt(person.getPassword()));
			preparedStat2.setString(10, person.getState());

			preparedStat2.executeUpdate();
			message = "Successfully Registered!";
			connectionClass.endConnection(connection);

		} catch (Exception e) {
			e.printStackTrace();
			message = "Some error while registering Customer!";
		}

		return message;
	}

	/*
	 * Added by Darshan -- Deletes an existing user
	 */
	public String deleteCustomer(String emailID, int roleID) {
		String message = null;

		EstablishConnection connectionClass = null;
		Connection connection = null;

		try {
			connectionClass = new EstablishConnection();
			connection = connectionClass.getConnection();
			connection.setAutoCommit(false);
			String query = null;

			String query1 = "Select count(1) from person where emailID = ?";
			PreparedStatement statement2 = connection.prepareStatement(query1);
			statement2.setString(1, emailID);
			ResultSet result = statement2.executeQuery();

			while (result.next()) {
				// Added by Darshan
				int count = result.getInt(1);
				if (count == 0) {
					message = "The email ID does not exist.";
					return message;
				} else if (count == 1) {
					if (roleID == 2) {
						query = "DELETE PERSON, TRAVELLER from PERSON, TRAVELLER WHERE PERSON.emailID = ? and PERSON.uniqueID = TRAVELLER.uniqueID ";
					} else {
						query = "DELETE PERSON, EMPLOYEE from PERSON, EMPLOYEE WHERE PERSON.emailID = ? and PERSON.uniqueID = EMPLOYEE.uniqueID ";
					}

					PreparedStatement prepStmt = connection
							.prepareStatement(query);

					prepStmt.setString(1, emailID);

					int count2 = prepStmt.executeUpdate();

					if (count2 > 0)
						message = "Customer Deleted!!";
					else
						message = "Customer could not be deleted. Please try again.";
				}
				// End of changes by Darshan
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return message;
	}

	/*
	 * Code added by Pradyumna Get all reservation of USER OR THE SYSTEM
	 */
	public Reservation[] getAllReservations(String userId, int issued) {
		EstablishConnection myConnection = new EstablishConnection();
		Connection con = myConnection.getConnection();
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		Reservation[] tickets = null;

		String CountQuery = "select count(*) from reservation ";
		if (userId != null) {
			CountQuery += " where emailId=?";
			CountQuery += " and issued=" + issued + ";";
		} else {
			CountQuery += ";";
		}

		String qry = "select * from reservation ";
		if (userId != null) {
			qry += " where emailId=?";
			qry += " and issued=" + issued + ";";
		} else {
			qry += ";";
		}
		try {
			pStmt = con.prepareStatement(CountQuery);
			if (userId != null) {
				pStmt.setString(1, userId);
			}
			rs = pStmt.executeQuery();
			if (rs.next() && rs.getInt(1) > 0) {
				tickets = new Reservation[rs.getInt(1)];
				// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
				pStmt = con.prepareStatement(qry);
				pStmt.setString(1, userId);
				rs = pStmt.executeQuery();
				int i = 0;
				while (rs.next()) {
					Reservation ticket = new Reservation();
					ticket.setReservationId(rs.getInt(1));
					ticket.setFlightNumber(rs.getInt(2));
					ticket.setAirlineName(rs.getString(3));
					ticket.setSource(rs.getString(4));
					ticket.setDestination(rs.getString(5));
					ticket.setNumberOfSeats(rs.getInt(6));
					tickets[i] = ticket;
					i++;
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return tickets;
	}

	/*
	 * Added by Pradyumna-- Reserves the flight ticket
	 */
	public String reserveTicket(FlightDetails jDetails, Traveller travellerInfo) {
		EstablishConnection myConnection = new EstablishConnection();
		Connection con = myConnection.getConnection();
		PreparedStatement pStmt = null;

		String updateFlightQry = "update flight_details set noOfSeats=noOfSeats-? where flightNumber=?;";
		String sql = "insert into  reservation(flightNumber,airlineName,source,destination,noOfSeats,emailId,issued) values(?,?,?,?,?,?,?);";
		String updateTravellerQuery = "update traveller t, person p set t.nationality=?,t.passportId=? where t.uniqueID=p.uniqueID and p.emailid=?;";
		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, jDetails.getFlightNumber());
			pStmt.setString(2, jDetails.getAirlineName());
			pStmt.setString(3, jDetails.getSource());
			pStmt.setString(4, jDetails.getDestination());
			pStmt.setInt(5, jDetails.getNumberOfSeats());
			pStmt.setString(6, travellerInfo.getEmailID());
			pStmt.setInt(7, 0);
			pStmt.execute();

			pStmt = con.prepareStatement(updateTravellerQuery);
			pStmt.setString(1, travellerInfo.getNationality());
			pStmt.setString(2, travellerInfo.getPassportNo());
			pStmt.setString(3, travellerInfo.getEmailID());
			pStmt.executeUpdate();

			pStmt = con.prepareStatement(updateFlightQry);
			pStmt.setInt(1, jDetails.getNumberOfSeats());
			pStmt.setInt(2, jDetails.getFlightNumber());
			pStmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		}

		updateCache(jDetails.getFlightNumber());
		return MessageConstants.RESERVED_SUCCESSFULLY;
	}

	/**
	 * @Author : Pradyumna
	 * @param reservationId
	 * @return
	 */
	public String cancelTicket(String UserId, int reservationId) {
		EstablishConnection myConnection = new EstablishConnection();
		Connection con = myConnection.getConnection();
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		String updateFlightQry = "update flight_details set noOfSeats=noOfSeats+? where flightNumber=?;";
		String getSeatsCnt = " select flightNumber , noOfSeats , source , destination from reservation where emailid = ? and reservationid = ?;";
		String query = "delete from reservation where emailid=? and reservationid=?;";
		try {
			pStmt = con.prepareStatement(getSeatsCnt);
			pStmt.setString(1, UserId);
			pStmt.setInt(2, reservationId);
			rs = pStmt.executeQuery();
			if (rs.next()) {

				pStmt = con.prepareStatement(query);
				pStmt.setString(1, UserId);
				pStmt.setInt(2, reservationId);
				pStmt.executeUpdate();

				pStmt = con.prepareStatement(updateFlightQry);
				pStmt.setInt(1, rs.getInt(2));
				pStmt.setInt(2, rs.getInt(1));
				pStmt.executeUpdate();

				updateCache(rs.getInt(1));

			}
		} catch (SQLException se) {
			se.printStackTrace();

		}

		return MessageConstants.CANCELED_SUCCESSFULLY;
	}

	public FlightDetails[] searchFlight(FlightDetails flight) {

		EstablishConnection myConnection = new EstablishConnection();
		Connection con = myConnection.getConnection();
		Statement stmt = null;
		ResultSet result = null;
		boolean where = false;
		String countPartOfQry = "Select count(*) ";
		String selectPartOfQry = "Select * ";

		String searchFlightQry = "from flight_details ";
		FlightDetails[] resultFlights = null;

		if (flight.getFlightNumber() > 0) {
			searchFlightQry += "where flightNumber=" + flight.getFlightNumber();
			where = true;
		}
		if (flight.getAirlineName() != null) {
			if (!where) {
				searchFlightQry += "where airlineName='"
						+ flight.getAirlineName() + "'";
				where = true;
			} else {
				searchFlightQry += "and airlineName='"
						+ flight.getAirlineName() + "'";
			}
		}

		if (flight.getSource() != null) {
			if (!where) {
				searchFlightQry += "where source='" + flight.getSource() + "'";
				where = true;
			} else {
				searchFlightQry += "and source='" + flight.getSource() + "'";
			}
		}

		if (flight.getDestination() != null) {
			if (!where) {
				searchFlightQry += "where destination='"
						+ flight.getDestination() + "'";
				where = true;
			} else {
				searchFlightQry += "and destination='"
						+ flight.getDestination() + "'";
			}
		}

		if (flight.getFlightTime() != null) {
			if (!where) {
				searchFlightQry += "where flightTime='"
						+ flight.getFlightTime() + "'";
				where = true;
			} else {
				searchFlightQry += "and flightTime='" + flight.getFlightTime()
						+ "'";
			}
		}
		searchFlightQry += ";";

		try {
			// First get the count of the matching flights
			stmt = con.createStatement();
			result = stmt.executeQuery(countPartOfQry + searchFlightQry);
			int i = 0;
			if (result.next() && result.getInt(1) > 0) {
				resultFlights = new FlightDetails[result.getInt(1)];

				// Now get the entire flight search results
				stmt = con.createStatement();
				result = stmt.executeQuery(selectPartOfQry + searchFlightQry);
				while (result.next()) {
					FlightDetails newFlight = new FlightDetails();
					newFlight.setFlightNumber(result.getInt(1));
					newFlight.setAirlineName(result.getString(2));
					newFlight.setSource(result.getString(3));
					newFlight.setDestination(result.getString(4));
					newFlight.setNumberOfSeats(result.getInt(5));
					newFlight.setFlightTime(result.getString(6));
					resultFlights[i] = newFlight;
					i++;
				}
				// CREW ID NEED NOT BE SHOWN
			}

		} catch (SQLException se) {
			return resultFlights;
		}

		return resultFlights;
	}

	/*
	 * Added by Pradyumna -- Retruns the available flights for source and
	 * destination
	 */
	public FlightDetails[] searchFlightForSourceAndDest(String source,
			String destination, String flightTime) {
		FlightDetails[] flightArray = null;

		try {
			flightArray = myCache.getFlightFromCache(source + "-" + destination
					+ "-" + flightTime);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// EstablishConnection myConnection = new EstablishConnection();
		// Connection con = myConnection.getConnection();
		// PreparedStatement pStmt = null;
		// String flighSearchQry = null;
		// String countQuery = null;
		// ResultSet result = null;
		// FlightDetails[] flights = null;
		//
		// if (source == null || destination == null || flightTime == null)
		// {
		// countQuery = " select count(*) from flight_details;";
		// flighSearchQry = "select * from flight_details;";
		// }
		// else
		// {
		// countQuery =
		// " select count(*) from flight_details where source=? and destination=? and flightTime=?;";
		// flighSearchQry =
		// "select * from flight_details where source=? and destination=? and flightTime=?;";
		// }
		//
		// try
		// {
		//
		// pStmt = con.prepareStatement(countQuery);
		// if (source != null && destination != null && flightTime != null)
		// {
		// pStmt.setString(1, source);
		// pStmt.setString(2, destination);
		// pStmt.setString(3, flightTime);
		// }
		// result = pStmt.executeQuery();
		// if (result.next())
		// {
		// flights = new FlightDetails[result.getInt(1)];
		// //
		// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4
		//
		// pStmt = con.prepareStatement(flighSearchQry);
		// if (source != null && destination != null && flightTime != null)
		// {
		// pStmt.setString(1, source);
		// pStmt.setString(2, destination);
		// pStmt.setString(3, flightTime);
		// }
		// result = pStmt.executeQuery();
		// int i = 0;
		// while (result.next())
		// {
		// FlightDetails flight = new FlightDetails();
		// flight.setFlightNumber(result.getInt(1));
		// flight.setAirlineName(result.getString(2));
		// flight.setSource(result.getString(3));
		// flight.setDestination(result.getString(4));
		// flight.setNumberOfSeats(result.getInt(5));
		// flight.setCrewId(result.getInt(6));
		// flights[i] = flight;
		// i++;
		// }
		// }
		//
		// }
		// catch (SQLException se)
		// {
		// se.printStackTrace();
		// }
		return flightArray;
	}

	/**
	 * @Author : Pradyumna
	 * @param reservationID
	 * @return
	 */
	public Reservation issueTicket(String userId, int reservationID) {
		EstablishConnection myConnection = new EstablishConnection();
		Connection con = myConnection.getConnection();
		PreparedStatement pStmt = null;
		Statement stmt = null;
		ResultSet result = null;

		Reservation ticket = null;
		String updateissueTicket = "update reservation set issued=1 where reservationID="
				+ reservationID;
		String qry = "select * from reservation where emailId=? and reservationID=?";
		try {
			pStmt = con.prepareStatement(qry);
			pStmt.setString(1, userId);
			pStmt.setInt(2, reservationID);
			result = pStmt.executeQuery();
			if (result.next()) {
				ticket = new Reservation();
				ticket.setReservationId(result.getInt(1));
				ticket.setFlightNumber(result.getInt(2));
				ticket.setAirlineName(result.getString(3));
				ticket.setSource(result.getString(4));
				ticket.setDestination(result.getString(5));
				ticket.setNumberOfSeats(result.getInt(6));

				stmt = con.createStatement();
				stmt.execute(updateissueTicket);

			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return ticket;
	}

	// lIST ALL THE CUSTOMERS ON BOARD :PRADYUMNA
	public Traveller[] listAllCustomersOnBoard(int flightNo) {
		EstablishConnection myConnection = new EstablishConnection();
		Connection con = myConnection.getConnection();
		Statement stmt = null;
		ResultSet result = null;
		ResultSet rs = null;
		Traveller[] resultTravellers = null;

		String countQuery = "select count(*) from reservation where flightNumber="
				+ flightNo + ";";
		String query = "select emailid from reservation where flightNumber="
				+ flightNo + ";";
		try {
			// GET COUNT OF CUTOMERS ON BOARD
			stmt = con.createStatement();
			result = stmt.executeQuery(countQuery);
			int i = 0;
			if (result.next() && result.getInt(1) > 0) {

				resultTravellers = new Traveller[result.getInt(1)];
				// get email id of those customers
				stmt = con.createStatement();
				result = stmt.executeQuery(query);

				while (result.next()) {

					String selectPartOfQry = "Select  person.firstName,person.lastName, person.emailID,"
							+ "traveller.passportID, traveller.travelerID, traveller.nationality ";

					String searchTravellerQry = " From (person JOIN traveller ON person.uniqueID=traveller.uniqueID)";
					searchTravellerQry += " where person.emailid='"
							+ result.getString("emailid") + "';";

					stmt = con.createStatement();
					rs = stmt
							.executeQuery(selectPartOfQry + searchTravellerQry);

					while (rs.next()) {
						Traveller traveller = new Traveller();
						traveller.setFirstName(rs.getString(1));
						traveller.setLastName(rs.getString(2));
						traveller.setEmailID(rs.getString(3));
						traveller.setPassportNo(rs.getString(4));
						traveller.setTravellerId(rs.getInt(5));
						traveller.setNationality(rs.getString(6));

						resultTravellers[i] = traveller;
						i++;
					}
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();

		}

		return resultTravellers;
	}

	/*
	 * Added by **** Returns the travellers matching the search criteria
	 */
	public Traveller[] searchTraveller(Traveller traveller) {
		EstablishConnection myConnection = new EstablishConnection();
		Connection con = myConnection.getConnection();
		Statement stmt = null;
		ResultSet result = null;
		boolean where = false;
		String countPartOfQry = "Select count(*) ";
		String selectPartOfQry = "Select  person.firstName,person.lastName, person.emailID,"
				+ "traveller.passportID, traveller.travelerID, traveller.nationality ";

		String searchTravellerQry = "From (person JOIN traveller ON person.uniqueID=traveller.uniqueID)";
		Traveller[] resultTravellers = null;

		if (traveller != null) {
			if (traveller.getFirstName() != null) {
				searchTravellerQry += "where person.firstName='"
						+ traveller.getFirstName() + "'";
				where = true;
			}
			if (traveller.getLastName() != null) {
				if (where == false) {
					searchTravellerQry += "where person.lastName='"
							+ traveller.getLastName() + "'";
					where = true;
				} else {
					searchTravellerQry += "and person.getLastName='"
							+ traveller.getLastName() + "'";
				}
			}
			if (traveller.getEmailID() != null) {
				if (where == false) {
					searchTravellerQry += "where person.emailID='"
							+ traveller.getEmailID() + "'";
					where = true;
				} else {
					searchTravellerQry += "and person.emailID='"
							+ traveller.getEmailID() + "'";
				}
			}
			if (traveller.getPassportNo() != null) {
				if (where == false) {
					searchTravellerQry += "where traveller.passportID='"
							+ traveller.getPassportNo() + "'";
					where = true;
				} else {
					searchTravellerQry += "and traveller.passportID='"
							+ traveller.getPassportNo() + "'";
				}
			}
			if (traveller.getTravellerId() != 0) {
				if (where == false) {
					searchTravellerQry += "where traveller.travelerID='"
							+ traveller.getTravellerId() + "'";
					where = true;
				} else {
					searchTravellerQry += "and traveller.travelerID='"
							+ traveller.getTravellerId() + "'";
				}
			}
			if (traveller.getNationality() != null) {
				if (where == false) {
					searchTravellerQry += "where traveller.nationality='"
							+ traveller.getNationality() + "'";
					where = true;
				} else {
					System.out.println("entering searchquery2 else part");
					searchTravellerQry += "and traveller.nationality='"
							+ traveller.getNationality() + "'";
				}
			}
		}

		searchTravellerQry += ";";
		try { // First get the count of the matching traveller
			stmt = con.createStatement();
			result = stmt.executeQuery(countPartOfQry + searchTravellerQry);
			int i = 0;
			if (result.next() && result.getInt(1) > 0) {
				resultTravellers = new Traveller[result.getInt(1)];
				// Now get the entire flight search results
				stmt = con.createStatement();
				result = stmt
						.executeQuery(selectPartOfQry + searchTravellerQry);
				while (result.next()) {
					Traveller newTraveller = new Traveller();
					newTraveller.setFirstName(result.getString(1));
					newTraveller.setLastName(result.getString(2));
					newTraveller.setEmailID(result.getString(3));
					newTraveller.setPassportNo(result.getString(4));
					newTraveller.setTravellerId(result.getInt(5));
					newTraveller.setNationality(result.getString(6));
					resultTravellers[i] = newTraveller;
					i++;
				}
			}
		} catch (SQLException se) {
			return resultTravellers;
		}
		return resultTravellers;
	}

	// List all employees 09-12-

	public Employee[] listAllEmployees() {
		EstablishConnection myConnection = new EstablishConnection();
		Connection con = myConnection.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		Employee[] empArray = null;

		String countQuery = "Select count(*) from (person p join employee e on p.uniqueID=e.uniqueID);";
		String query = "select p.firstName,p.lastName,p.dateOfBirth,p.address,e.employeeID,e.workDescription,e.position,e.hireDate"
				+ " from (person p join employee e on p.uniqueID=e.uniqueID);";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(countQuery);

			if (rs.next() && rs.getInt(1) > 0) {

				empArray = new Employee[rs.getInt(1)];
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				int i = 0;
				while (rs.next()) {
					Employee emp = new Employee();
					emp.setFirstName(rs.getString(1));
					emp.setLastName(rs.getString(2));
					emp.setDateOfBirth(rs.getString(3));
					emp.setAddress(rs.getString(4));
					emp.setEmployeeID(rs.getInt(5));
					emp.setWorkDescription(rs.getString(6));
					emp.setPosition(rs.getString(7));
					emp.setHireDate(rs.getString(8));

					empArray[i] = emp;
					i++;
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}

		return empArray;
	}

	/*
	 * Added by Abinaya -- Returns the list of employees matching the search
	 * criteria
	 */
	public Employee[] searchEmployeeForID(int empID, String workDesc,
			String hireDate) {
		EstablishConnection myConnection = new EstablishConnection();
		Connection con = myConnection.getConnection();
		PreparedStatement pStmt = null;
		Employee[] empArray = new Employee[1];
		String employeeSearchQueryID;
		String employeeSearchQueryDesc;
		String employeeSearchQueryDate;

		try {
			if (empID != 0) {
				employeeSearchQueryID = "select * from employee where employeeID=?;";
				pStmt = con.prepareStatement(employeeSearchQueryID);
				pStmt.setInt(1, empID);

			} else if (null != workDesc) {
				employeeSearchQueryDesc = "select * from employee where workDescription=?;";
				pStmt = con.prepareStatement(employeeSearchQueryDesc);
				pStmt.setString(1, workDesc);
			} else if (null != hireDate) {
				employeeSearchQueryDate = "select * from employee where hireDate=?;";
				pStmt = con.prepareStatement(employeeSearchQueryDate);
				pStmt.setString(1, hireDate);
			}
			ResultSet result = null;

			result = pStmt.executeQuery();

			if (result.last()) {
				empArray = new Employee[result.getRow()];
				result.beforeFirst();
			}

			int i = 0;
			while (result.next()) {
				empArray[i] = new Employee();
				empArray[i].setEmployeeID(result.getInt(1));
				empArray[i].setUniqueID(result.getInt(2));
				empArray[i].setWorkDescription(result.getString(3));
				empArray[i].setPosition(result.getString(4));
				empArray[i].setHireDate(result.getString(5));
				i++;
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return empArray;
	}

	/*
	 * Added by Darshan -- Adds a new employee to the system
	 */
	public String addEmployee(Employee employee) {
		String message = null;

		EstablishConnection connectionClass = null;
		Connection connection = null;

		try {
			connectionClass = new EstablishConnection();
			connection = connectionClass.getConnection();
			connection.setAutoCommit(false);

			Person person = new Person();
			person.setFirstName(employee.getFirstName());
			person.setLastName(employee.getLastName());
			person.setAddress(employee.getAddress());
			person.setCity(employee.getCity());
			person.setDateOfBirth(employee.getDateOfBirth());
			person.setEmailID(employee.getEmailID());
			person.setPassword(employee.getPassword());
			person.setState(employee.getState());
			person.setZipCode(employee.getZipCode());
			person.setRoleID(employee.getRoleID());
			String emailID = person.getEmailID();

			String query1 = "Select count(1) from person where emailID = ?";
			PreparedStatement preparedStmt = connection
					.prepareStatement(query1);

			preparedStmt.setString(1, emailID);
			ResultSet result1 = preparedStmt.executeQuery();

			while (result1.next()) {
				if (result1.getInt(1) > 0) {
					message = "This email ID already exists. ";
					return message;
				}
			}

			String query2 = "Insert into person (firstName, lastName, address, city, zipCode, dateOfBirth, roleID, emailID, password, state) values (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStat2 = connection
					.prepareStatement(query2);

			preparedStat2.setString(1, person.getFirstName());
			preparedStat2.setString(2, person.getLastName());
			preparedStat2.setString(3, person.getAddress());
			preparedStat2.setString(4, person.getCity());
			preparedStat2.setString(5, person.getZipCode());
			preparedStat2.setString(6, person.getDateOfBirth());
			preparedStat2.setInt(7, person.getRoleID());

			preparedStat2.setString(8, person.getEmailID());
			preparedStat2.setString(9, encrypt(person.getPassword()));
			preparedStat2.setString(10, person.getState());

			preparedStat2.executeUpdate();

			try {
				String query = "update employee , person set workDescription = ? , position = ? , hiredate= ? where emailID = ? and employee.uniqueID = person.uniqueID";

				System.out.println("In MC " + employee.getWorkDescription());
				PreparedStatement prepStmt = connection.prepareStatement(query);
				prepStmt.setString(1, employee.getWorkDescription());
				prepStmt.setString(2, employee.getPosition());

				prepStmt.setString(3, "10/10/10");
				prepStmt.setString(4, employee.getEmailID());

				int count = prepStmt.executeUpdate();
				if (count > 0)
					message = "Employee added successfully to system";
				else
					message = "Error while adding a new employee!";
			} catch (Exception e) {
				e.printStackTrace();
				connection.rollback();
				message = "Some error while registering Customer!";
			}

			connectionClass.endConnection(connection);

		} catch (Exception e) {
			e.printStackTrace();
			message = "Some error while registering Customer!";
		}
		return message;
	}

	/**
	 * @function: listAllReservations
	 * @description: Lists all the reservations known by the system
	 * @author: Rushabh Jain
	 * @param: None
	 * @return: List of Reservation
	 */
	public Reservation[] listAllReservations() {
		EstablishConnection myConnection = new EstablishConnection();
		Connection con = myConnection.getConnection();
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		Reservation[] entries = null;

		String CountQuery = "select count(*) from reservation;";

		String qry = "select * from reservation;";
		try {
			pStmt = con.prepareStatement(CountQuery);
			rs = pStmt.executeQuery();
			if (rs.next()) {
				entries = new Reservation[rs.getInt(1)];
				pStmt = con.prepareStatement(qry);
				rs = pStmt.executeQuery();
				int i = 0;
				while (rs.next()) {
					Reservation entry = new Reservation();
					entry.setReservationId(rs.getInt(1));
					entry.setFlightNumber(rs.getInt(2));
					entry.setAirlineName(rs.getString(3));
					entry.setSource(rs.getString(4));
					entry.setDestination(rs.getString(5));
					entry.setNumberOfSeats(rs.getInt(6));
					entry.setEmail(rs.getString(7));
					entries[i] = entry;
					i++;
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return entries;
	}

	public String updateFlightDetails(FlightDetails flightDetails) {
		EstablishConnection myConnection = new EstablishConnection();
		Connection con = myConnection.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String message = MessageConstants.FLIGHT_NOT_UPDATED;

		String checkFlightQry = "Select airlineName from flight_details where flightNumber="
				+ flightDetails.getFlightNumber();
		boolean flightExists = false;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(checkFlightQry);
			if (rs.next()) {
				flightExists = true;
			} else {
				message = MessageConstants.FLIGHT_DOESNOT_EXIST;
			}

			String updateQuery = "update flight_details set ";

			if (flightExists) {
				if (flightDetails.getAirlineName() != null) {
					updateQuery += "airlineName='"
							+ flightDetails.getAirlineName() + "'";
				} else {
					updateQuery += "airlineName='" + rs.getString(1) + "'";
				}
				if (flightDetails.getSource() != null
						&& "".equals(flightDetails.getSource())) {
					updateQuery += ",source='" + flightDetails.getSource()
							+ "'";
				}
				if (flightDetails.getDestination() != null
						&& "".equals(flightDetails.getDestination())) {
					updateQuery += ",destination='"
							+ flightDetails.getDestination() + "'";
				}
				if (flightDetails.getNumberOfSeats() > 0) {
					updateQuery += ",noOfSeats="
							+ flightDetails.getNumberOfSeats();
				}
				if (flightDetails.getFlightTime() != null
						&& !"".equals(flightDetails.getFlightTime())) {
					updateQuery += ",flightTime='"
							+ flightDetails.getFlightTime() + "'";
				}
				updateQuery += " where flightNumber="
						+ flightDetails.getFlightNumber() + ";";

				stmt = con.createStatement();
				stmt.executeUpdate(updateQuery);
				message = MessageConstants.FLIGHT_UPDATE_SUCCESS;

				updateCache(flightDetails.getFlightNumber());
			}
		} catch (SQLException e) {
			message = MessageConstants.FLIGHT_UPDATE_FAILURE;
			e.printStackTrace();

		}
		return message;

	}

	// ///////////////////listAllReservations//////////////////////

	/**
	 * @function: updateEmployeeInfo
	 * @description: Updates the information for the Employee
	 * @author: Rushabh Jain
	 * @param: Employee
	 * @return: String
	 */
	public String updateEmployeeInfo(Employee emp) {
		String message = null;

		EstablishConnection connectionClass = null;
		Connection connection = null;

		try {
			connectionClass = new EstablishConnection();
			connection = connectionClass.getConnection();
			connection.setAutoCommit(false);

			int empId = emp.getEmployeeID();
			PreparedStatement preparedStmt1 = null;

			Statement stmt = null;

			// Check the Employee Table for the Employee
			// The check would be made based on the Employee ID
			String queryP_1 = "Select count(1) from employee where employeeID = ?";
			preparedStmt1 = connection.prepareStatement(queryP_1);
			preparedStmt1.setInt(1, empId);

			ResultSet result1 = preparedStmt1.executeQuery();
			System.out.println("Execute q1");
			// Ensure that the given employee exists in Employee table
			while (result1.next()) {
				if (result1.getInt(1) <= 0) {
					System.out.println("does not exist");
					message = MessageConstants.TRAVELLER_NOT_FOUND;
					return message;
				}
			}

			String query = " Update employee, person set ";
			String whereClause = "where employee.uniqueID = person.uniqueID and employee.employeeID = "
					+ empId;

			String fname = emp.getFirstName();
			String lname = emp.getLastName();
			String address = emp.getAddress();
			String wDesc = emp.getWorkDescription();
			String position = emp.getPosition();
			String city = emp.getCity();
			String zip = emp.getZipCode();
			String email = emp.getEmailID();

			query += " employee.employeeID = " + empId;

			if (!"".equals(fname))
				query += " , person.firstName = '" + fname + "' ";
			if (!"".equals(lname))
				query += " , person.lastName = '" + lname + "' ";
			if (!"".equals(address))
				query += " , person.address = '" + address + "' ";
			if (!"".equals(wDesc))
				query += " , employee.workDescription = '" + wDesc + "' ";
			if (!"".equals(position))
				query += " , employee.position = '" + position + "' ";
			if (!"".equals(city))
				query += " , person.city = '" + city + "' ";
			if (!"".equals(zip))
				query += " , person.zipCode = '" + zip + "' ";
			if (!"".equals(emp.getDateOfBirth()))
				query += " , person.dateOfBirth = '" + emp.getDateOfBirth()
						+ "' ";
			if (!"".equals(email))
				query += " , person.emailID = '" + email + "' ";
			if (!"".equals(emp.getState()))
				query += " , person.state = '" + emp.getState() + "' ";

			query += whereClause;
			System.out.println("set to update " + query);

			stmt = connection.createStatement();
			stmt.executeUpdate(query);
			message = "Employee Successfully updated";
			connectionClass.endConnection(connection);

		} catch (Exception e) {
			e.printStackTrace();
			message = MessageConstants.EMPLOYEE_INFO_UPDATE_FAILURE;
		}

		return message;
	}

	// ///////////////////updateEmployeeInfo//////////////////////

	/**
	 * @function: updateTravellerInfo
	 * @description: Updates the information for the Traveller
	 * @author: Rushabh Jain
	 * @param: Traveller
	 * @return: String
	 */
	public String updateTravellerInfo(Traveller cand) {
		String message = null;
		EstablishConnection myConnection = new EstablishConnection();
		Connection con = myConnection.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String searchTravellerQuery = "select * from traveller t, person p where t.uniqueID=p.uniqueID and p.emailid='"
				+ cand.getEmailID() + "';";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(searchTravellerQuery);
			if (rs != null && rs.next()) {
				String updateTraveller = "update traveller t, person p set ";

				if (cand.getEmailID() != null) {
					updateTraveller += " p.emailid='" + cand.getEmailID() + "'";
				}
				if (cand.getFirstName() != null) {
					updateTraveller += ",p.firstName='" + cand.getFirstName()
							+ "'";
				}
				if (cand.getLastName() != null) {
					updateTraveller += ",p.lastName='" + cand.getLastName()
							+ "'";
				}
				if (cand.getAddress() != null) {
					updateTraveller += ",p.address='" + cand.getAddress() + "'";
				}
				if (cand.getCity() != null) {
					updateTraveller += ",p.city='" + cand.getCity() + "'";
				}
				if (cand.getZipCode() != null) {
					updateTraveller += ",p.zipcode='" + cand.getZipCode() + "'";
				}
				if (cand.getDateOfBirth() != null) {
					updateTraveller += ",p.dateOfBirth='"
							+ cand.getDateOfBirth() + "'";
				}
				if (cand.getNationality() != null) {
					updateTraveller += ",t.nationality='"
							+ cand.getNationality() + "'";
				}
				if (cand.getPassportNo() != null) {
					updateTraveller += ",t.passport='" + cand.getPassportNo()
							+ "'";
				}
				if (cand.getState() != null) {
					updateTraveller += ",p.state='" + cand.getState() + "'";
				}

				updateTraveller += " where t.uniqueID=p.uniqueID and p.emailid='"
						+ cand.getEmailID() + "';";

				stmt = con.createStatement();
				stmt.executeUpdate(updateTraveller);
				myConnection.endConnection(con);
				message = MessageConstants.CUSTOMER_UPDATE_SUCCESS;
			} else {
				message = MessageConstants.CUSTOMER_DOESNOT_EXIST;
			}
		} catch (SQLException se) {
			se.printStackTrace();
			message = MessageConstants.CUSTOMER_UPDATE_FAILURE;
		}
		return message;
	}

	// ///////////////////updateTravellerInfo//////////////////////
	public void updateCustomerAboutFlight() {
		JMSUpdates jmsUpdate = new JMSUpdates();
		jmsUpdate.sendUpdate();
	}
	
	boolean validateTravellerResult(ResultSet travelRs){
		boolean validated = false;
		try {
		while(travelRs.next()){
		String fname = travelRs.getString("firstNAme");
		String lname = travelRs.getString("lastNAme");
		String address = travelRs.getString("address");
		String dateofBirth = travelRs.getString("dateOfBirth");
		String city = travelRs.getString("city");
		if(fname==null || lname==null||address==null||dateofBirth==null || city!=null){
		validated =false;
		}
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return validated;
		}
		public boolean validateEmployee(ResultSet empRs){
		boolean validated = false;
		try {
		while(empRs.next()){
		String fname = empRs.getString("firstNAme");
		String lname = empRs.getString("lastNAme");
		String address = empRs.getString("address");
		String dateofBirth = empRs.getString("dateOfBirth");
		String city = empRs.getString("city");
		String workDescription = empRs.getString("workdescription");
		String position = empRs.getString("position");
		String hireDate = empRs.getString("hireDate");
		if(fname==null || lname==null||address==null||dateofBirth==null || city==null || hireDate==null || workDescription==null || position==null){
		validated =false;
		}
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return validated;
		}
}
