import java.util.Random;

public class Main {
	public static void main(String[] args) {
		String firstNames[] = {"Kristie", "Cindy", "Alonzo", "Malory", "Hutt", "Richardson", "Hope", "Jimmy", "Carroll", "Elisa"};
		String lastNames[] = {"Voyager", "Ford", "Apple", "Dickinson", "Evans", "Pardie", "Saxon", "Patel", "Bull", "Johnson"};
		int max = 10;

		Employee []employees = new Employee[max];

		Random random = new Random();
		for (int i = 0; i < max; i++) {
			switch (random.nextInt(3)) {
				case 0: // SalaridEmployee
					SalaridEmployee se = new SalaridEmployee(firstNames[i], lastNames[i], i);
					se.setWeeklySalary(random.nextDouble()*100);
					employees[i] = se;
					break;
				case 1: // HourlyEmployee
					HourlyEmployee he = new HourlyEmployee(firstNames[i], lastNames[i], i);
					he.setWage(random.nextDouble()*80);
					he.setHours(random.nextInt(100));
					employees[i] = he;
					break;
				case 2: // CommissionEmployee
					CommissionEmployee ce = new CommissionEmployee(firstNames[i], lastNames[i], i);
					ce.setGrossSales(random.nextInt(100));
					ce.setCommissionRate(random.nextDouble()*80);
					employees[i] = ce;
					break;
				case 3: // BasePlusCommissionEmployee
					BasePlusCommissionEmployee bpce = new BasePlusCommissionEmployee(firstNames[i], lastNames[i], i);
					bpce.setGrossSales(random.nextInt(100));
					bpce.setCommissionRate(random.nextDouble()*80);
					bpce.setBaseSalary(random.nextInt(1000));
					employees[i] = bpce;
					break;
			}
		}

		for (int i = 0; i < max; i++) {
			System.out.println(employees[i]);
			System.out.println("Earning: "+employees[i].earning());
			System.out.println();
		}
	}
}