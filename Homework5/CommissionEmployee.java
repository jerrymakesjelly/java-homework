public class CommissionEmployee extends Employee {
	protected int grossSales;
	protected double commissionRate;
	
	public CommissionEmployee(String firstName, String lastName, int socialSecurityNumber) {
		super(firstName, lastName, socialSecurityNumber);
	}

	public double earning() {
		return grossSales*commissionRate;
	}

	public int getGrossSales() {
		return this.grossSales;
	}

	public void setGrossSales(int grossSales) {
		this.grossSales = grossSales;
	}

	public double getCommissionRate() {
		return this.commissionRate;
	}

	public void setCommissionRate(double commissionRate) {
		this.commissionRate = commissionRate;
	}

	public static void main(String args[]) {
		CommissionEmployee ce = new CommissionEmployee("Amy", "Green", 2);
		ce.setGrossSales(6);
		ce.setCommissionRate(7);
		System.out.println(ce);
	}
}