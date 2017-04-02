

import java.util.Comparator;

public class MarkComparatorAscending implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		// TODO Auto-generated method stub
		return Double.compare(s1.getMark(), s2.getMark());
	
	}

}
