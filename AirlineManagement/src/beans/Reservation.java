package beans;

import java.io.Serializable;

public class Reservation implements Serializable
{
	int flightNumber;
	int reservationId;
	String airlineName;
	String source;
	String destination;
	int numberOfSeats;
	String email;

	public int getFlightNumber()
	{
		return this.flightNumber;
	}

	public int getReservationId()
	{
		return this.reservationId;
	}

	public int getNumberOfSeats()
	{
		return this.numberOfSeats;
	}

	public String getAirlineName()
	{
		return this.airlineName;
	}

	public String getSource()
	{
		return this.source;
	}

	public String getDestination()
	{
		return this.destination;
	}

	public String getEmail()
	{
		return this.email;
	}

	public void setFlightNumber(int flightNumber)
	{
		this.flightNumber = flightNumber;
	}

	public void setReservationId(int reservationId)
	{
		this.reservationId = reservationId;
	}

	public void setNumberOfSeats(int numberOfSeats)
	{
		this.numberOfSeats = numberOfSeats;
	}

	public void setAirlineName(String airlineName)
	{
		this.airlineName = airlineName;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public void setDestination(String destination)
	{
		this.destination = destination;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
}
