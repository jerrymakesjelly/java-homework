import java.util.Scanner;

public class StudentTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入学生学号：");
		String num = sc.nextLine();
		System.out.print("请输入学生姓名：");
		String name = sc.nextLine();
		System.out.print("请输入学生三门课成绩（数学，英语，科学）：");
		int maths = sc.nextInt();
		int eng = sc.nextInt();
		int sci = sc.nextInt();
		
		// 创建对象
		Student stu = new Student(num, name);
		stu.enterMarks(maths, eng, sci);
		System.out.print(stu);
	}
}