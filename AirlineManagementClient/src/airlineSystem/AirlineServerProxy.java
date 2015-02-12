package airlineSystem;

public class AirlineServerProxy implements airlineSystem.AirlineServer {
  private String _endpoint = null;
  private airlineSystem.AirlineServer airlineServer = null;
  
  public AirlineServerProxy() {
    _initAirlineServerProxy();
  }
  
  public AirlineServerProxy(String endpoint) {
    _endpoint = endpoint;
    _initAirlineServerProxy();
  }
  
  private void _initAirlineServerProxy() {
    try {
      airlineServer = (new airlineSystem.AirlineServerServiceLocator()).getAirlineServer();
      if (airlineServer != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)airlineServer)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)airlineServer)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (airlineServer != null)
      ((javax.xml.rpc.Stub)airlineServer)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public airlineSystem.AirlineServer getAirlineServer() {
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer;
  }
  
  public java.lang.String login(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.login(userName, password);
  }
  
  public void buildCache() throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    airlineServer.buildCache();
  }
  
  public beans.Traveller[] listAllCustomers() throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.listAllCustomers();
  }
  
  public beans.Employee[] listAllEmployees() throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.listAllEmployees();
  }
  
  public java.lang.String processPayement(java.lang.String cardID) throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.processPayement(cardID);
  }
  
  public beans.Reservation[] getBookedTickets(java.lang.String userID, int issued) throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.getBookedTickets(userID, issued);
  }
  
  public java.lang.String cancelReservation(java.lang.String userID, int reservationID) throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.cancelReservation(userID, reservationID);
  }
  
  public java.lang.String[] fetchStateList() throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.fetchStateList();
  }
  
  public java.lang.String registerCustomer(beans.Person person) throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.registerCustomer(person);
  }
  
  public java.lang.String deleteCustomer(java.lang.String emailID, int roleID) throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.deleteCustomer(emailID, roleID);
  }
  
  public java.lang.String createNewReservation(beans.FlightDetails journeyDetails, beans.Traveller travellerInfo) throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.createNewReservation(journeyDetails, travellerInfo);
  }
  
  public java.lang.String deleteEmployee(java.lang.String emailID) throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.deleteEmployee(emailID);
  }
  
  public beans.Reservation[] listAllReservation() throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.listAllReservation();
  }
  
  public beans.FlightDetails[] listAllFlights(java.lang.String source, java.lang.String destination, java.lang.String flightTime) throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.listAllFlights(source, destination, flightTime);
  }
  
  public java.lang.String updateTravellerInfo(beans.Traveller traveller) throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.updateTravellerInfo(traveller);
  }
  
  public java.lang.String updateEmployeeInfo(beans.Employee emp) throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.updateEmployeeInfo(emp);
  }
  
  public java.lang.String updateFlightDetails(beans.FlightDetails flight) throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.updateFlightDetails(flight);
  }
  
  public beans.Employee[] searchEmployeeForID(int empID, java.lang.String workDesc, java.lang.String hireDate) throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.searchEmployeeForID(empID, workDesc, hireDate);
  }
  
  public beans.Traveller[] findPassengersOnBoard(beans.FlightDetails flight) throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.findPassengersOnBoard(flight);
  }
  
  public beans.Traveller[] findTravellers(beans.Traveller traveller) throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.findTravellers(traveller);
  }
  
  public beans.Traveller[] getTravellersOnBoard(int flightNo) throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.getTravellersOnBoard(flightNo);
  }
  
  public beans.FlightDetails[] findAllFlights() throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.findAllFlights();
  }
  
  public beans.Employee findEmployee(beans.Employee emp) throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.findEmployee(emp);
  }
  
  public beans.FlightDetails[] findFlights(beans.FlightDetails flight) throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.findFlights(flight);
  }
  
  public java.lang.String addEmployee(beans.Employee employee) throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.addEmployee(employee);
  }
  
  public beans.Reservation issueTicket(java.lang.String userId, int reservationID) throws java.rmi.RemoteException{
    if (airlineServer == null)
      _initAirlineServerProxy();
    return airlineServer.issueTicket(userId, reservationID);
  }
  
  
}