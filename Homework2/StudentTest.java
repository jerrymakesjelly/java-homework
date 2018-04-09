import java.util.Scanner;

public class StudentTest {
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.print("请输入学生学号：");
			String num = sc.nextLine();
			System.out.print("请输入学生姓名：");
			String name = sc.nextLine();
			Student stu = new Student(num, name);

			System.out.print("请输入学生三门课成绩（数学，英语，科学）：");
			int maths = sc.nextInt();
			int eng = sc.nextInt();
			int sci = sc.nextInt();
			stu.enterMarks(maths, eng, sci);

			System.out.print(stu);
		} catch (ScoreException e) {
			System.out.println("学生的成绩必须在0~100分之间。");
		} catch (StudentNumberException e) {
			System.out.println("学号不符合要求。学号为10位，第1位为2，第2位为0，其余位为数字0~9。");
		}
	}
}