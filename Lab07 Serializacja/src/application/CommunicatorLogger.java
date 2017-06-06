package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class CommunicatorLogger implements Logger{
	
	Socket socket;
	PrintWriter out;

	public CommunicatorLogger()
	{
		boolean succeded = true;
		 try {
			socket = new Socket("localhost", 6665);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			succeded = false;
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			succeded = false;
			e.printStackTrace();
		}
		 //  socket = new Socket("192.168.1.1", 4321);
	     try {
			out = new PrintWriter(socket.getOutputStream(), 
			            true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			succeded = false;
			e.printStackTrace();
		}
	     if(succeded)
	    	 System.out.println("Nawiazano poloczenie z serwerem.");
	}
	
   
	
	@Override
	public void log(String status, Student student) throws FileNotFoundException {
		// TODO Auto-generated method stub
		out.println("CommunicatorLogger:\t " + status + student);
		out.flush();
		
	}

}
