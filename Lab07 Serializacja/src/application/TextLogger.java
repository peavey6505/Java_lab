package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextLogger implements Logger{

	public String FILENAME = "src/application/Log.txt";
	String contentLog = null;

	

	
	@Override
 public void log(String status, Student student) {
		
		// TODO Auto-generated method stub
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);
		 // 12/01/2011 4:48:16 PM
		
		
		
		if(status=="ADDED")
			contentLog = formattedDate + ":     ["+status+"]  " + "  Mark: " + student.getMark() + "  Name: " + student.getFirstName() +" "+
		student.getLastName() +"   Age: " + student.getAge();
		
		else if(status == "REMOVED")
			contentLog = formattedDate + ":     ["+status+"]  " + "  Mark: " + student.getMark() + "  Name: " + student.getFirstName() +" "+
					student.getLastName() +"   Age: " + student.getAge() +"\n";
		


			try{
		
			    PrintWriter writer = new PrintWriter(new FileOutputStream(
			    	    new File(FILENAME), 
			    	    true /* append = true */)); 
			    
			    writer.print(contentLog+"\n");
			    
			    writer.close();
			} catch (IOException e) {
			   // do something
				System.out.println("ERROR");
				System.exit(0);
			}
		
		

		
	}

}
