package beans;

import java.io.Serializable;

public class Person implements Serializable
{

	private static final long serialVersionUID = 1L;

	String firstName;
	String lastName;
	String address;
	String city;
	String zipCode;
	String dateOfBirth;
	int roleID;
	String emailID;
	String password;
	String state;

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getZipCode()
	{
		return zipCode;
	}

	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}

	public String getDateOfBirth()
	{
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	public int getRoleID()
	{
		return roleID;
	}

	public void setRoleID(int roleID)
	{
		this.roleID = roleID;
	}

	public String getEmailID()
	{
		return emailID;
	}

	public void setEmailID(String emailID)
	{
		this.emailID = emailID;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

}
