public class HourlyEmployee extends Employee {
	protected double wage;
	protected int hours;

	public HourlyEmployee(String firstName, String lastName, int socialSecurityNumber) {
		super(firstName, lastName, socialSecurityNumber);
	}

	public double earning() {
		return wage*hours;
	}

	public double getWage() {
		return this.wage;
	}

	public void setWage(double wage) {
		this.wage = wage;
	}

	public int getHours() {
		return this.hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public static void main(String[] args) {
		HourlyEmployee he = new HourlyEmployee("John", "Smith", 1);
		he.setWage(2.5);
		he.setHours(5);
		System.out.println(he);
	}
}