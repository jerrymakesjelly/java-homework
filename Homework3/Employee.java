public abstract class Employee {
	protected String firstName, lastName;
	protected int socialSecurityNumber;
	
	public Employee(String firstName, String lastName, int socialSecurityNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public abstract double earning();

	public String toString() {
		return "Name: "+firstName+" "+lastName+"\nSocial Security Number: "+socialSecurityNumber;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getSocialSecurityNumber() {
		return this.socialSecurityNumber;
	}
}