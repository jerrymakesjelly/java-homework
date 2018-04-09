public class SalaridEmployee extends Employee {
	protected double weeklySalary;
	
	public SalaridEmployee(String firstName, String lastName, int socialSecurityNumber) {
		super(firstName, lastName, socialSecurityNumber);
	}

	public double earning() {
		return weeklySalary*4;
	}

	public double getWeeklySalary() {
		return this.weeklySalary;
	}

	public void setWeeklySalary(double weeklySalary) {
		this.weeklySalary = weeklySalary;
	}

	public static void main(String[] args) {
		SalaridEmployee se = new SalaridEmployee("Jack", "Smith", 0);
		se.setWeeklySalary(1.00);
		System.out.println(se);
	}
}