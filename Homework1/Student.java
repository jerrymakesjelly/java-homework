public class Student {
	private String studentNumber;
	private String studentName;
	private int markForMaths;
	private int markForEnglish;
	private int markForScience;

	// 构造函数
	Student(String number, String name) {
		this.studentNumber = number;
		this.studentName = name;
	}
	
	tudent() {
		// Nothing to do
	}

	// 获取学号
	public String getNumber() {
		return this.studentNumber;
	}

	// 获取名字
	public String getName() {
		return this.studentName;
	}

	// 录入成绩
	public void enterMarks(int markForMaths, int markForEnglish, int markForScience) {
		this.markForMaths = markForMaths;
		this.markForEnglish = markForEnglish;
		this.markForScience = markForScience;
	}

	// 获取数学成绩
	public int getMathsMark() {
		return this.markForMaths;
	}

	// 获取英语成绩
	public int getEnglishMark() {
		return this.markForEnglish;
	}

	// 获取科学成绩
	public int getScienceMark() {
		return this.markForScience;
	}

	// 计算平均成绩
	public double calculateAverage() {
		return (this.markForMaths+this.markForEnglish+this.markForScience) / 3.0;
	}

	// 输出学生信息
	public String toString() {
		String str = "";
		str += "学号："+getNumber()+"\n";
		str += "姓名："+getName()+"\n";
		str += "数学成绩："+getMathsMark()+"\n";
		str += "英语成绩："+getEnglishMark()+"\n";
		str += "科学成绩："+getScienceMark()+"\n";
		str += "平均成绩:"+calculateAverage()+"\n";
		return str;
	}
}