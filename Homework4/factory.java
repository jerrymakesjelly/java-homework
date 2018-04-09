import java.util.*;

public class factory {
	private Map<Integer, Employee> employees;

	// Random parameters for generating
	int id = 0;
	String firstNames[] = {"Kristie", "Cindy", "Alonzo", "Malory", "Hutt", "Richardson", "Hope", "Jimmy", "Carroll", "Elisa"};
	String lastNames[] = {"Voyager", "Ford", "Apple", "Dickinson", "Evans", "Pardie", "Saxon", "Patel", "Bull", "Johnson"};

	private Employee generateNewEmployee(int type) {
		Random random = new Random();
		switch (type) {
			case 0: // SalaridEmployee
				SalaridEmployee se = new SalaridEmployee(firstNames[id], lastNames[id], id);
				se.setWeeklySalary(random.nextDouble()*100);
				id++;
				return se;
			case 1: // HourlyEmployee
				HourlyEmployee he = new HourlyEmployee(firstNames[id], lastNames[id], id);
				he.setWage(random.nextDouble()*80);
				he.setHours(random.nextInt(100));
				id++;
				return he;
			case 2: // CommissionEmployee
				CommissionEmployee ce = new CommissionEmployee(firstNames[id], lastNames[id], id);
				ce.setGrossSales(random.nextInt(100));
				ce.setCommissionRate(random.nextDouble()*80);
				id++;
				return ce;
			case 3: // BasePlusCommissionEmployee
				BasePlusCommissionEmployee bpce = new BasePlusCommissionEmployee(firstNames[id], lastNames[id], id);
				bpce.setGrossSales(random.nextInt(100));
				bpce.setCommissionRate(random.nextDouble()*80);
				bpce.setBaseSalary(random.nextInt(1000));
				id++;
				return bpce;
			default:
				return null;
		}
	}

	public factory() {
		employees = new HashMap<>();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			Employee emp = generateNewEmployee(random.nextInt(3));
			employees.put(emp.getSocialSecurityNumber(), emp);
		}
	}

	public Employee getEmployee(int empSecNum) {
		return employees.get(empSecNum);
	}

	public Employee deleteEmployee(int empSecNum) {
		return employees.remove(empSecNum);
	}

	public Employee addEmployee(Employee stu) {
		if (employees.containsKey(stu.getSocialSecurityNumber()))
			return null;
		else
			return employees.put(stu.getSocialSecurityNumber(), stu);
	}

	public Employee updateEmployee(int empSecNum, Employee emp) {
		if (employees.containsKey(emp.getSocialSecurityNumber()))
			return employees.put(empSecNum, emp);
		else
			return null;
	}

	public void printEmployees() {
		for (Map.Entry<Integer, Employee> entry : employees.entrySet()) {
			System.out.println(entry.getValue());
			System.out.println("Earning: "+entry.getValue().earning());
			System.out.println();
		}
	}

	public static void main(String[] args) {
		factory f = new factory();
		f.printEmployees();
	}
}