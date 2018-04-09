public class StudentList {
	private Student[] list; // list存储学生对象
	private int total; // 学生总人数
	
	// 构造函数
	StudentList(int length) {
		list = new Student[length];
	}

	// 增加stu到数组中
	public boolean add(Student stu) {
		if (total >= list.length)
			return false;
		else {
			list[total] = stu;
			total++;
			return true;
		}
	}

	// 删除第 no 个数组元素
	public boolean remove(int no) {
		if (no < total) {
			for (int i = no; i < total - 1; i++)
				list[i] = list[i+1];
			total--;
			return true;
		}
		else
			return false;
	}

	// 删除学号为 number 的学生
	public boolean remove(Student number) {
		int i;
		boolean found = false;
		for (i = 0; i < total; i++) {
			if (list[i].getNumber().equals(number.getNumber())) {
				found = true;
				break;
			}
		}
		if (!found)
			return false;
		else {
			for (; i < total - 1; i++)
				list[i] = list[i+1];
			total--;
			return true;
		}		
	}

	// 判断数组是否为空
	public boolean isEmpty() {
		return total == 0;
	}

	// 返回第 no 个学生
	public Student getItem(int no) {
		if(no < total)
			return list[no];
		else
			return null;
	}
	
	// 返回学号为number的学生
	public Student getItem(Student number) {
		for (int i = 0; i < total; i++)
			if (list[i].getNumber().equals(number.getNumber()))
				return list[i];
		return null;
	}

	// 返回学生总人数
	public int getTotal() {
		return total;
	}	
}