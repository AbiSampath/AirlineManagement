package beans;

import java.io.Serializable;

public class Employee extends Person implements Serializable
{

	int employeeID;
	int uniqueID;
	String workDescription;
	String position;
	String hireDate;

	public String getHireDate()
	{
		return hireDate;
	}

	public void setHireDate(String hireDate)
	{
		this.hireDate = hireDate;
	}

	public int getEmployeeID()
	{
		return employeeID;
	}

	public void setEmployeeID(int employeeID)
	{
		this.employeeID = employeeID;
	}

	public int getUniqueID()
	{
		return uniqueID;
	}

	public void setUniqueID(int uniqueID)
	{
		this.uniqueID = uniqueID;
	}

	public String getWorkDescription()
	{
		return workDescription;
	}

	public void setWorkDescription(String workDescription)
	{
		this.workDescription = workDescription;
	}

	public String getPosition()
	{
		return position;
	}

	public void setPosition(String position)
	{
		this.position = position;
	}

}
