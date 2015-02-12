/**
 * AirlineServer.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package airlineSystem;

public interface AirlineServer extends java.rmi.Remote {
    public java.lang.String login(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException;
    public void buildCache() throws java.rmi.RemoteException;
    public beans.Traveller[] listAllCustomers() throws java.rmi.RemoteException;
    public beans.Employee[] listAllEmployees() throws java.rmi.RemoteException;
    public java.lang.String processPayement(java.lang.String cardID) throws java.rmi.RemoteException;
    public beans.Reservation[] getBookedTickets(java.lang.String userID, int issued) throws java.rmi.RemoteException;
    public java.lang.String cancelReservation(java.lang.String userID, int reservationID) throws java.rmi.RemoteException;
    public java.lang.String[] fetchStateList() throws java.rmi.RemoteException;
    public java.lang.String registerCustomer(beans.Person person) throws java.rmi.RemoteException;
    public java.lang.String deleteCustomer(java.lang.String emailID, int roleID) throws java.rmi.RemoteException;
    public java.lang.String createNewReservation(beans.FlightDetails journeyDetails, beans.Traveller travellerInfo) throws java.rmi.RemoteException;
    public java.lang.String deleteEmployee(java.lang.String emailID) throws java.rmi.RemoteException;
    public beans.Reservation[] listAllReservation() throws java.rmi.RemoteException;
    public beans.FlightDetails[] listAllFlights(java.lang.String source, java.lang.String destination, java.lang.String flightTime) throws java.rmi.RemoteException;
    public java.lang.String updateTravellerInfo(beans.Traveller traveller) throws java.rmi.RemoteException;
    public java.lang.String updateEmployeeInfo(beans.Employee emp) throws java.rmi.RemoteException;
    public java.lang.String updateFlightDetails(beans.FlightDetails flight) throws java.rmi.RemoteException;
    public beans.Employee[] searchEmployeeForID(int empID, java.lang.String workDesc, java.lang.String hireDate) throws java.rmi.RemoteException;
    public beans.Traveller[] findPassengersOnBoard(beans.FlightDetails flight) throws java.rmi.RemoteException;
    public beans.Traveller[] findTravellers(beans.Traveller traveller) throws java.rmi.RemoteException;
    public beans.Traveller[] getTravellersOnBoard(int flightNo) throws java.rmi.RemoteException;
    public beans.FlightDetails[] findAllFlights() throws java.rmi.RemoteException;
    public beans.Employee findEmployee(beans.Employee emp) throws java.rmi.RemoteException;
    public beans.FlightDetails[] findFlights(beans.FlightDetails flight) throws java.rmi.RemoteException;
    public java.lang.String addEmployee(beans.Employee employee) throws java.rmi.RemoteException;
    public beans.Reservation issueTicket(java.lang.String userId, int reservationID) throws java.rmi.RemoteException;
}
