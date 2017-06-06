package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;

public class MyThread extends Thread{

	Socket socket;
	PrintWriter out;
	BufferedReader in;
	ReadThread readThread;
	ArrayList<String> registeredUsers;
	ArrayList<String> loggedUsers;
	Main main;
	TextArea messageArea;
	boolean disconnected;
	boolean logged;
	WebEngine webEngine;
	Button sendButton;
	
	public MyThread(Socket socket, PrintWriter out, BufferedReader in, ReadThread readThread, ArrayList<String> registeredUsers, Main main, 
			TextArea messageArea, boolean disconnected,boolean logged,ArrayList<String> loggedUsers,WebEngine webEngine, Button sendButton)
	{
		this.socket = socket;
		this.out = out;
		this.in= in;
		this.readThread= readThread;
		this.registeredUsers = registeredUsers;
		this.main = main;
		this.messageArea = messageArea;
		this.disconnected = disconnected;
		this.logged = logged;
		this.loggedUsers=loggedUsers;
		this.webEngine = webEngine;
		this.sendButton = sendButton;
	}
			
			
			
			public void run()
			{
				try{
				     socket = new Socket("localhost", 6665);
					 //  socket = new Socket("192.168.1.1", 4321);
				      out = new PrintWriter(socket.getOutputStream(), 
				                 true);
				     in = new BufferedReader(new InputStreamReader(
				                socket.getInputStream()));
				    readThread = new ReadThread(messageArea,in,out,socket,disconnected,webEngine,sendButton);

				    sendConnectedMessage();
				    sendLoggedMessage();
				    sendRegisteredMessage();
				    
				    
				   } catch (UnknownHostException e) {
				     System.out.println("Unknown host: localhost");
				     return;
				   } catch  (IOException e) {
				     System.out.println("Nie udalo siê nawiazac polaczenia");
				     return;
				     
				   }
				   
					readThread.start();
					
			}
			
			public void sendConnectedMessage()
			{
				out.println("CONNECTED: " + main.name);
				out.flush();
				messageArea.appendText("CONNECTED: " + main.name +"\n");
				appendHtml("CONNECTED: " + main.name +"\n");
			}
			public void logOut()
			{
				out.println("DISCONNECTED: " + main.name);
				out.flush();
				messageArea.appendText("DISCONNECTED: " + main.name +"\n");
				appendHtml("DISCONNECTED: " + main.name +"\n");
				
		    	out.println("LOGGED OUT: " + main.name);
				out.flush();
				messageArea.appendText("LOGGED OUT: " + main.name +"\n");
				appendHtml("LOGGED OUT: " + main.name +"\n");
				
		    	readThread.stopped=true;
		    	logged = true;
		    	
		    	System.out.println("Roz³¹czono");
		    	
		    	
		    	try {
					Thread.sleep(20);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

		    	try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	out.close();
				
				try {
					main.changeSceneLogin();
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			public void sendRegisteredMessage()
			{
				for(String registeredName : registeredUsers)
				{
					
					out.println("REGISTERED: " + registeredName);
					out.flush();
					messageArea.appendText("REGISTERED: " + registeredName +"\n");
					appendHtml("REGISTERED: "+ registeredName + "\n");
				}
				registeredUsers.clear();
			}
			
			public void sendLoggedMessage()
			{
				for(String loggedName : loggedUsers)
				{
					
					out.println("LOGGED: " + loggedName);
					out.flush();
					messageArea.appendText("LOGGED: " + loggedName +"\n");
					appendHtml("REGISTERED: "+ loggedName + "\n");
				}
				loggedUsers.clear();
			}
			

			
			public void sendMessage(String message)
			{
				out.println(main.name + ": " +message);
				out.flush();
				messageArea.appendText("Klient: " + message +"\n");
				appendHtml(message);
			
			}
			public void disconnect()
			{
				out.println("DISCONNECTED: " + main.name);
				out.flush();
				messageArea.appendText("DISCONNECTED: " + main.name +"\n");
				appendHtml("DISCONNECTED: " + main.name +"\n");
				
		    	
		    	readThread.stopped=true;
		    	logged = false;
		    	
		    	System.out.println("Roz³¹czono");
		    	
		    	
		    	try {
					Thread.sleep(20);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

		    	try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	out.close();
		    	
			}
			
			public void appendHtml(String message)
			{
				try(FileWriter fw = new FileWriter("src/application/conversation.txt", true);
					    BufferedWriter bw = new BufferedWriter(fw);
					    PrintWriter out = new PrintWriter(bw))
					{
					    out.println("Klient: " + message + "\n");
					   
					    
					} catch (IOException e) {
					    //exception handling left as an exercise for the reader
					}
				
				
				
				
				Platform.runLater(new Runnable() {
		             @Override public void run() {

		            	 String FILENAME = "src/application/conversation.txt";
		            	 
					
							
							File file1 = new File(FILENAME);
							FileInputStream fis=null;
							try {
								fis = new FileInputStream(file1);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							byte[] data = new byte[(int) file1.length()];
							try {
								fis.read(data);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								fis.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							String fileToString=null;
							try {
								fileToString = new String(data, "UTF-8");
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							// webEngine.load(url.toString());
		                	webEngine.loadContent(fileToString);
						
		            	  
		            	 // file:/C:/test/a.html
		            	
		            	 
		             }
		         });
			}
}
