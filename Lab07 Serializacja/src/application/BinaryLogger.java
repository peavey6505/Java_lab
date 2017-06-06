package application;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BinaryLogger {

	LoggedStudent loggedStudent; 
	List<LoggedStudent> loggedStudentsList = new ArrayList<LoggedStudent>();

	void log(Student student, Status status, long time)
	{
		loggedStudent = new LoggedStudent(student, status,time);
		
		 try {
	         FileOutputStream fileOut =
	         new FileOutputStream("src/application/binaryLog.bin");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(loggedStudent.getFirstName());
	         out.writeObject(loggedStudent.getLastName());
	         out.writeObject(loggedStudent.getAge());
	         out.writeObject(loggedStudent.getMark());
	         out.writeObject(loggedStudent.getTime());
	         out.writeObject(loggedStudent.getStatus());
	         

	     
	         out.close();
	         fileOut.close();
	  
	        
	        	 deserialize();
	         
	         System.out.println("Lista studentów zdeserializowanych BINARY: " + listStudents().toString() + "\n\n");
	      }catch(IOException i) {
	    	  System.out.println("Blad IO");
	         i.printStackTrace();
	         System.exit(0);
	      } catch (ClassNotFoundException e) {
			System.out.println("Nie odnaleziono wlascwiego obiektu");
			System.exit(0);
			e.printStackTrace();
		}
		
		
	}

	void deserialize()
	{
		LoggedStudent student = new LoggedStudent();
		
		
		try {
	         FileInputStream fileIn = new FileInputStream("src/application/binaryLog.bin");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         
	         
	         student.setFirstName((String)in.readObject());
	         student.setLastName((String)in.readObject());
	         student.setAge((Integer)in.readObject());
	         student.setMark((Double)in.readObject());
	         student.setTime((Long)in.readObject());
	         student.setStatus((Status)in.readObject());
	         loggedStudentsList.add(student);

	         in.close();
	         fileIn.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c) {
	    	  System.out.println("Nie odnaleziono wartosci danego typu");
	         c.printStackTrace();
	         return;
	      }

	}
	
	
	void deserialize(int x) throws IOException, ClassNotFoundException
	{
		LoggedStudent student = new LoggedStudent();
		   FileInputStream fileIn = new FileInputStream("src/application/binaryLog.bin");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
		
		while (true) {
	        try {
	            // Read the next object from the stream. If there is none, the
	            // EOFException will be thrown.
	            // This call might also throw a ClassNotFoundException, which can be passed
	            // up or handled here.
	        	student.setFirstName((String)in.readObject());
		         student.setLastName((String)in.readObject());
		         student.setAge((Integer)in.readObject());
		         student.setMark((Double)in.readObject());
		         student.setTime((Long)in.readObject());
		         student.setStatus((Status)in.readObject());
		         loggedStudentsList.add(student);

	           
	        } catch (EOFException e) {
	            // If there are no more objects to read, return what we have.
	            System.out.println("KONIEC PLIKU");
	        } finally {
	            // Close the stream.
	            in.close();
	        }
		
		
	
	 
	        
	      
	         
	         

		
		
		}
	}
	List<LoggedStudent> listStudents() throws ClassNotFoundException, IOException
	{
	
		
		return loggedStudentsList;
	}
}
