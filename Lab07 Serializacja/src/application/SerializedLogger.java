package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.util.ArrayList;
import java.util.List;

public class SerializedLogger  {
	
	
	LoggedStudent loggedStudent; 
	List<LoggedStudent> loggedStudentsList = new ArrayList<LoggedStudent>();
	
	void log(Student student, Status status, long time)
	{
		loggedStudent = new LoggedStudent(student, status,time);
		
		 try {
	         FileOutputStream fileOut =
	         new FileOutputStream("src/application/serializedLog.bin");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(loggedStudent);
	         out.close();
	         fileOut.close();

	         deserialize();
	         
	       System.out.println("Lista zdeserializowanych SERIALIZED: " + listStudents().toString()+"\n\n");
	      }catch(IOException i) {
	         i.printStackTrace();
	      } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	void deserialize()
	{
		LoggedStudent student = null;
		
		
		try {
	         FileInputStream fileIn = new FileInputStream("src/application/serializedLog.bin");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         student = (LoggedStudent) in.readObject();
	         loggedStudentsList.add(student);
	        
	         in.close();
	         fileIn.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	         System.out.println("Blad IO");
	         System.exit(0);
	         return;
	      }catch(ClassNotFoundException c) {
	         System.out.println("Student not found");
	         c.printStackTrace();
	         System.exit(0);
	         return;
	      }

		
		
		
	}
	
	List<LoggedStudent> listStudents() throws ClassNotFoundException, IOException
	{
	
		
		return loggedStudentsList;
	}
}
