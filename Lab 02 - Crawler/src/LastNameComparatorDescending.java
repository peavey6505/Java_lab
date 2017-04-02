

import java.util.Comparator;

public class LastNameComparatorDescending implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		return s2.getLastName().compareTo(s1.getLastName());
		
	}


}
