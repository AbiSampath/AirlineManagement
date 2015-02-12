package beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FlightDetails implements Serializable
{
	int flightNumber;
	String airlineName;
	String source;
	String destination;
	int numberOfSeats;
	String flightTime;
	// used as foreign key references crew details (crewID)
	// Flight : Employee :: 1: 1 cardinality
	int crewId;

	public int getFlightNumber()
	{
		return flightNumber;
	}

	public String getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(String flightTime) {
		this.flightTime = flightTime;
	}

	public void setFlightNumber(int flightNumber)
	{
		this.flightNumber = flightNumber;
	}

	public String getAirlineName()
	{
		return airlineName;
	}

	public void setAirlineName(String airlineName)
	{
		this.airlineName = airlineName;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public String getDestination()
	{
		return destination;
	}

	public void setDestination(String destination)
	{
		this.destination = destination;
	}

	public int getNumberOfSeats()
	{
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats)
	{
		this.numberOfSeats = numberOfSeats;
	}

	public int getCrewId()
	{
		return crewId;
	}

	public void setCrewId(int crewId)
	{
		this.crewId = crewId;
	}

}
