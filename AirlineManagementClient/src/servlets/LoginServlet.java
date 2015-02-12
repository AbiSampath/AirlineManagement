package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Employee;
import beans.Person;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{

		String message = null;
		System.out.println("Inside Post Method");
		try
		{
			airlineSystem.AirlineServerProxy proxy = new airlineSystem.AirlineServerProxy();

			if (request.getParameter("Login").equals("Register"))
			{
				String emailID = request.getParameter("emailID");
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				String password = request.getParameter("password");
				String address = request.getParameter("address");
				String city = request.getParameter("city");
				String zipCode = request.getParameter("zipCode");
				String state = request.getParameter("selectedState");
				String dateOfBirth = request.getParameter("DOB");

				Person person = new Person();

				person.setEmailID(emailID);
				person.setFirstName(firstName);
				person.setLastName(lastName);
				person.setPassword(password);
				person.setAddress(address);
				person.setCity(city);
				person.setZipCode(zipCode);
				person.setState(state);
				person.setDateOfBirth(dateOfBirth);
				person.setRoleID(2);

				message = proxy.registerCustomer(person);
				//
				// getJmsUpdate();
				request.setAttribute("message", message);

				request.getRequestDispatcher("Login.jsp").forward(request, response);

			}

			if (request.getParameter("Login").equals("Login"))
			{
				System.out.println("In Login");
				String userName = request.getParameter("userName");
				String password = request.getParameter("password");

				if (userName.length() == 0 || password.length() == 0)
				{
					message = "Invalid," + "Please enter User Name and Password";
				}
				else
				{
					message = proxy.login(userName, password);

					System.out.println("Message" + message);

					StringTokenizer tokenizer = new StringTokenizer(message, ",");
					String[] resultArray = new String[tokenizer.countTokens()];
					int count = 0;

					while (tokenizer.hasMoreTokens())
					{
						resultArray[count++] = tokenizer.nextToken();
					}

					if (resultArray[0].equalsIgnoreCase("Success"))
					{
						HttpSession session = request.getSession();

						String fName = resultArray[1];
						int roleID = Integer.parseInt(resultArray[2]);
						session.setAttribute("fName", fName);
						session.setAttribute("userName", userName);
						session.setAttribute("roleID", roleID);
						System.out.println("roleID " + roleID);
						response.sendRedirect("Home.jsp");
					}
					else
					{
						message = "Incorrect Credentials";
						request.setAttribute("message", message);
						request.getRequestDispatcher("Login.jsp").forward(request, response);
					}
				}
			}

			if (request.getParameter("Login").equals("Delete Employee"))
			{
				System.out.println("Remove");

				String email = request.getParameter("emailID");

				message = proxy.deleteCustomer(email, 1);
				System.out.println(message);

				request.setAttribute("message", message);
				request.getRequestDispatcher("Message.jsp").forward(request, response);

			}

			// Added by Darshan
			if (request.getParameter("Login").equals("Delete Customer"))
			{
				System.out.println("Remove");

				String email = request.getParameter("emailID");

				message = proxy.deleteCustomer(email, 2);
				System.out.println(message);

				request.setAttribute("message", message);
				request.getRequestDispatcher("Message.jsp").forward(request, response);

			}// End of code: Darshan

			if (request.getParameter("Login").equals("Add Employee"))
			{
				Employee emp = new Employee();

				try
				{
					String firstName = request.getParameter("firstName");
					String lastName = request.getParameter("lastName");
					String email = request.getParameter("email");
					String password = request.getParameter("password");
					String city = request.getParameter("city");
					String address = request.getParameter("address");
					String position = request.getParameter("position");
					String workDesc = request.getParameter("workDescription");

					System.out.println(workDesc);

					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = null;
					String strDate = request.getParameter("DOB");
					if (null != strDate)
					{
						try
						{
							cal = Calendar.getInstance();
							cal.setTime(dateFormat.parse(strDate));
						}
						catch (Exception e)
						{
							cal = null;
						}
					}

					System.out.println(cal);

					String zipCode = request.getParameter("zipCode");
					String state = request.getParameter("selectedState");

					emp.setAddress(address);
					emp.setCity(city);
					emp.setDateOfBirth("10/10/10");
					emp.setEmailID(email);
					emp.setFirstName(firstName);
					emp.setLastName(lastName);
					emp.setPassword(password);
					emp.setPosition(position);
					emp.setRoleID(1);
					emp.setState(state);
					emp.setWorkDescription(workDesc);
					emp.setZipCode(zipCode);

					message = proxy.addEmployee(emp);

					request.setAttribute("message", message);
					request.getRequestDispatcher("Message.jsp").forward(request, response);
				}
				catch (Exception e)
				{
					request.setAttribute("message",
							"Some error while Adding Employee. Please try again after some time.");
					request.getRequestDispatcher("Message.jsp").forward(request, response);
				}

			}

			if (request.getParameter("Login").equals("Search Employee"))
			{

				System.out.println("In Search");

				try
				{
					PrintWriter out = response.getWriter();
					System.out.println("First");
					int empID = 0;
					String strDate = request.getParameter("hireDate");

					String strEmpID = request.getParameter("empID");
					System.out.println(strEmpID);
					if (strEmpID != null && !strEmpID.isEmpty())
					{
						empID = Integer.parseInt(strEmpID);
					}
					String desc = request.getParameter("description");

					if (empID == 0 && desc == null && strDate == null)
					{
						out.println("No data Found");
					}

					System.out.println("Make a call");
					Object[] eArray = proxy.searchEmployeeForID(empID, request.getParameter("description"), strDate);

					if (eArray != null)
					{
						List eList = Arrays.asList(eArray);
						out.println("<table width=\"100%\"; cellpadding=\"5\">"
								+ "<col width=\"400\">"
								+ "<col width=\"400\">"
								+ "<col width=\"400\">"
								+ "<col width=\"400\">"
								+ "<tbody><tr>"
								+ "<th style=\"border-style:solid; border-width:thin; border-color: Black\">Employee ID</th>"
								+ "<th style=\"border-style:solid; border-width:thin; border-color: Black\">Work Description</th>"
								+ "<th style=\"border-style:solid; border-width:thin; border-color: Black\">Position</th>"
								+ "<th style=\"border-style:solid; border-width:thin; border-color: Black\">Hire Date</th>"
								+ "</tr></tbody>");
						for (Employee emp : (List<Employee>) eList)
						{
							out.println("<tr>"
									+ "<td style=\"border-style:solid; border-width:thin; border-color: Black\" align=\"center\">"
									+ emp.getEmployeeID()
									+ "</td>"
									+ "<td style=\"border-style:solid; border-width:thin; border-color: Black\" align=\"center\">"
									+ emp.getWorkDescription()
									+ "</td>"
									+ "<td style=\"border-style:solid; border-width:thin; border-color: Black\" align=\"center\">"
									+ emp.getPosition()
									+ "</td>"
									+ "<td style=\"border-style:solid; border-width:thin; border-color: Black\" align=\"center\">"
									+ emp.getHireDate() + "</td>" + "</tr>");
						}
						out.println("</table>");

					}
					else
					{
						out.println("No data Found");
					}

				}
				catch (Exception e)
				{
					e.printStackTrace();
					RequestDispatcher rd = request.getRequestDispatcher("Message.jsp");
					rd.forward(request, response);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// private String getJmsUpdate() {
	// javax.jms.Connection connection=null;
	// InitialContext jndi = null;
	// ConnectionFactory conFactory = null;
	// Destination destination = null;
	// Session session = null;
	// MessageConsumer consumer = null;
	// TextMessage message =null;
	//
	// Properties properties = new Properties();
	// properties.put(Context.INITIAL_CONTEXT_FACTORY,
	// "org.jnp.interfaces.NamingContextFactory");
	// properties.put(Context.URL_PKG_PREFIXES, "org.jnp.interfaces");
	// properties.put(Context.PROVIDER_URL, "localhost");
	//
	// try {
	// jndi = new InitialContext(properties);
	// conFactory = (ConnectionFactory)jndi.lookup("XAConnectionFactory");
	// destination = (Queue) jndi.lookup("queue1");
	// connection = conFactory.createConnection();
	// session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	// consumer = session.createConsumer(destination);
	// connection.start();
	// message = (TextMessage)consumer.receive();
	// if (message instanceof TextMessage) {
	// System.out.println(this.getClass().getName()
	// + "has received a message : " + message.getText());
	// }
	//
	// } catch (NamingException e) {
	//
	// e.printStackTrace();
	// } catch (JMSException e) {
	//
	// e.printStackTrace();
	// }
	// if (jndi != null) {
	// try {
	// jndi.close();
	// } catch (NamingException ex) {
	// ex.printStackTrace();
	// }
	// }
	//
	// if (connection != null) {
	// try {
	// connection.close();
	// } catch (JMSException ex) {
	// ex.printStackTrace();
	// }
	// }
	//
	// try {
	// return message.getText();
	// } catch (JMSException e) {
	// e.printStackTrace();
	// return null;
	// }
	//
	// }

}
