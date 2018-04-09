import java.util.Scanner;

public class Main {
	private static factory fact;

	private static void printEmployee(Employee emp) {
		System.out.println(emp);
		System.out.println("Earning: "+emp.earning());
		System.out.println();
	}

	private static void queryEmployee() {
		Scanner sc = new Scanner(System.in);

		System.out.print("请输入员工社会保险号：");
		int security_number = Integer.parseInt(sc.nextLine());
		
		Employee emp = fact.getEmployee(security_number);
		if (emp == null)
			System.out.println("我们没有这样的人。");
		else
			printEmployee(emp);
	}

	private static void modifyEmployee() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("请输入员工社会保险号：");
		int security_number = Integer.parseInt(sc.nextLine());

		Employee emp = fact.getEmployee(security_number);
		if (emp == null)
			System.out.println("我们没有这样的人。");
		else {
			// 输入新名字
			System.out.print("请输入名字：");
			String firstName = sc.nextLine();
			System.out.print("请输入姓氏：");
			String lastName = sc.nextLine();

			// 输入员工类型
			System.out.print("请输入员工类型（1.SalaridEmployee; 2.HourlyEmployee; 3.CommisionEmployee; 4.BasePlusCommisionEmployee）：");
			int type = Integer.parseInt(sc.nextLine());
			switch (type) {
				case 1: // SalaridEmployee
					SalaridEmployee se = new SalaridEmployee(firstName, lastName, security_number);
					System.out.print("请输入"+firstName+" "+lastName+"的周薪：");
					se.setWeeklySalary(Double.parseDouble(sc.nextLine()));
					fact.updateEmployee(security_number, se);
					break;
				case 2: // HourlyEmployee
					HourlyEmployee he = new HourlyEmployee(firstName, lastName, security_number);
					System.out.print("请输入"+firstName+" "+lastName+"的工资：");
					he.setWage(Double.parseDouble(sc.nextLine()));
					System.out.print("请输入"+firstName+" "+lastName+"的工时：");
					he.setHours(Integer.parseInt(sc.nextLine()));
					fact.updateEmployee(security_number, he);
					break;
				case 3: // CommisionEmployee
					CommissionEmployee ce = new CommissionEmployee(firstName, lastName, security_number);
					System.out.print("请输入"+firstName+" "+lastName+"的销售量：");
					ce.setGrossSales(Integer.parseInt(sc.nextLine()));
					System.out.print("请输入"+firstName+" "+lastName+"的提成：");
					ce.setCommissionRate(Double.parseDouble(sc.nextLine()));
					fact.updateEmployee(security_number, ce);
					break;
				case 4: // BasePlusCommissionEmployee
					BasePlusCommissionEmployee bpce = new BasePlusCommissionEmployee(firstName, lastName, security_number);
					System.out.print("请输入"+firstName+" "+lastName+"的销售量：");
					bpce.setGrossSales(Integer.parseInt(sc.nextLine()));
					System.out.print("请输入"+firstName+" "+lastName+"的提成：");
					bpce.setCommissionRate(Double.parseDouble(sc.nextLine()));
					System.out.print("请输入"+firstName+" "+lastName+"的底薪：");
					bpce.setBaseSalary(Integer.parseInt(sc.nextLine()));
					fact.updateEmployee(security_number, bpce);
					break;
				default:
					System.out.println("没有这种人。");
			}
		}
	}

	private static void removeEmployee() {
		Scanner sc = new Scanner(System.in);

		System.out.print("请输入员工社会保险号：");
		int security_number = Integer.parseInt(sc.nextLine());
		if (fact.deleteEmployee(security_number) == null)
			System.out.println("删除失败。员工不存在。");
		else
			System.out.println("删除成功。");
	}

	public static void main(String args[]) {
		fact = new factory();
		Scanner sc = new Scanner(System.in);

		while(true) {
			// 输出菜单
			System.out.println("        欢迎来到员工群体对象！      ");
			System.out.println("---------------------------------------");
			System.out.println("0. 退出                             ");
			System.out.println("1. 查询员工社会保险号/输出员工信息  ");
			System.out.println("2. 更新员工信息                     ");
			System.out.println("3. 删除员工                         ");
			System.out.println("4. 打印全部员工信息                 ");

			// 获取用户选择
			System.out.print("你的选择是：");
			int choose = Integer.parseInt(sc.nextLine());
			switch (choose) {
				case 0:
					System.out.println("再见！");
					System.exit(0);
					break;
				case 1:
					queryEmployee();
					break;
				case 2:
					modifyEmployee();
					break;
				case 3:
					removeEmployee();
					break;
				case 4:
					fact.printEmployees();
					break;
				default:
					System.out.println("没有这个选项。");
			}
		}
	}
}