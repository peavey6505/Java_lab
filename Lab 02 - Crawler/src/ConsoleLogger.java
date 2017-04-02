

public class ConsoleLogger implements Logger {

	
	@Override
	public void log(String status, Student student) {
		if(status == "ADDED")
			System.out.println("ADDED: " + student);
		
		if(status =="REMOVED")
			System.out.println("REMOVED: " + student);
		
	}

}
