package beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public class JourneyDetails implements Serializable
{
	int FlightNo;
	String destination;
	String boardingpoint;

	public int getFlightNo()
	{
		return FlightNo;
	}

	public void setFlightNo(int flightNo)
	{
		FlightNo = flightNo;
	}

	public String getDestination()
	{
		return destination;
	}

	public void setDestination(String destination)
	{
		this.destination = destination;
	}

	public String getBoardingpoint()
	{
		return boardingpoint;
	}

	public void setBoardingpoint(String boardingpoint)
	{
		this.boardingpoint = boardingpoint;
	}
}
