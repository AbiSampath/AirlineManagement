package beans;

public class FlightTicket {
	String flightName;
	int bookedSeats;
	String passengerName;
	String Source;
	String destination;
	public synchronized String getFlightName() {
		return flightName;
	}
	public synchronized void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public synchronized int getBookedSeats() {
		return bookedSeats;
	}
	public synchronized void setBookedSeats(int bookedSeats) {
		this.bookedSeats = bookedSeats;
	}
	public synchronized String getPassengerName() {
		return passengerName;
	}
	public synchronized void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public synchronized String getSource() {
		return Source;
	}
	public synchronized void setSource(String source) {
		Source = source;
	}
	public synchronized String getDestination() {
		return destination;
	}
	public synchronized void setDestination(String destination) {
		this.destination = destination;
	}
	
}
