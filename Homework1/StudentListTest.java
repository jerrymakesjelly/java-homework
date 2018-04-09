import java.util.Scanner;

public class StudentListTest {
	private static StudentList stulist;

	public static void addStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入学生信息：");
		System.out.print("学号：");
		String num = sc.nextLine();
		System.out.print("姓名：");
		String name = sc.nextLine();
		System.out.print("数学成绩：");
		int math = sc.nextInt();
		System.out.print("英语成绩：");
		int eng = sc.nextInt();
		System.out.print("科学成绩：");
		int sci = sc.nextInt();
		Student stu = new Student(num, name);
		stu.enterMarks(math, eng, sci);
		if(stulist.add(stu))
			System.out.println("添加成功！");
		else
			System.out.println("添加失败。");
	}

	public static void removeStudentByNumber() {
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入要删除的学号：");
		if (stulist.remove(new Student(sc.nextLine(), null)))
			System.out.println("删除成功！");
		else
			System.out.println("删除失败。");
	}

	public static void removeStudentByPosition() {
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入要删除的序号：");
		if (stulist.remove(Integer.parseInt(sc.nextLine())))
			System.out.println("删除成功！");
		else
			System.out.println("删除失败。");
	}

	public static void checkEmpty() {
		if (stulist.getTotal() == 0)
			System.out.println("列表为空。");
		else
			System.out.println("列表不为空。");
	}

	public static void getStudentByPosition() {
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入要显示的学生的序号：");
		System.out.print(stulist.getItem(Integer.parseInt(sc.nextLine())));
	}

	public static void getStudentByNumber() {
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入要显示的学生的学号：");
		System.out.print(stulist.getItem(new Student(sc.nextLine(), null)));
	}

	public static void printAll() {
		for(int i = 0; i < stulist.getTotal(); i++)
			System.out.print(stulist.getItem(i));
	}

	public static void quit() {
		System.exit(0);
	}
		
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入学生人数：");
		stulist = new StudentList(Integer.parseInt(sc.nextLine()));
		while(true) {
			System.out.println("菜单如下，请输入 1~8 代表您要执行的操作：");
			System.out.println("1.增加1个学生 2.根据学号删除学生 3.根据位置删除学生");
			System.out.println("4.判断是否为空 5.根据位置返回学生 6.根据学号返回学生");
			System.out.println("7.输出全部学生信息 8.退出程序");
			System.out.print("请输入您的操作：");
			int action = Integer.parseInt(sc.nextLine());
			
			switch(action) {
				case 1:
					addStudent();
					System.out.println("---目前有"+stulist.getTotal()+"个学生，信息为---");
					printAll();
					break;
				case 2:
					removeStudentByNumber();
					System.out.println("---目前有"+stulist.getTotal()+"个学生，信息为---");
					printAll();
					break;
				case 3:
					removeStudentByPosition();
					System.out.println("---目前有"+stulist.getTotal()+"个学生，信息为---");
					printAll();
					break;
				case 4:
					checkEmpty();
					break;
				case 5:
					getStudentByPosition();
					break;
				case 6:
					getStudentByNumber();
					break;
				case 7:
					printAll();
					break;
				case 8:
					quit();
					break;
				default:
					System.out.println("无效的输入。");

				System.out.println("");
			}
		}
	}
}