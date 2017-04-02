

import java.util.Comparator;

public class AgeComparatorDescending implements Comparator<Student>{

	@Override
	public int compare(Student s1, Student s2) {
		
		return s2.getAge() - s1.getAge();
	}

}
