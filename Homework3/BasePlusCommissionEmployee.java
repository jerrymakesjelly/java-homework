public class BasePlusCommissionEmployee extends CommissionEmployee {
	protected double baseSalary;

	public BasePlusCommissionEmployee(String firstName, String lastName, int socialSecurityNumber) {
		super(firstName, lastName, socialSecurityNumber);
	}

	public double earning() {
		return grossSales*commissionRate+baseSalary;
	}

	public double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public static void main(String[] args) {
		BasePlusCommissionEmployee bpce = new BasePlusCommissionEmployee("123", "456", 4);
		bpce.setGrossSales(10);
		bpce.setCommissionRate(5);
		bpce.setBaseSalary(1000);
		System.out.println(bpce);
	}
}