

import java.util.Comparator;

public class MarkComparatorDescending implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		// TODO Auto-generated method stub
		return Double.compare(s2.getMark(), s1.getMark());
	}

}
