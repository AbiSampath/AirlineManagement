package beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Traveller extends Person implements Serializable
{
	String customerId;
	String passportNo;
	String nationality;
	int travellerId;
	
	public int getTravellerId() {
		return travellerId;
	}

	public void setTravellerId(int travellerId) {
		this.travellerId = travellerId;
	}

	public String getCustomerId()
	{
		return customerId;
	}

	public void setCustomerId(String customerId)
	{
		this.customerId = customerId;
	}

	public String getPassportNo()
	{
		return passportNo;
	}

	public void setPassportNo(String passportNo)
	{
		this.passportNo = passportNo;
	}

	public String getNationality()
	{
		return nationality;
	}

	public void setNationality(String nationality)
	{
		this.nationality = nationality;
	}

}
