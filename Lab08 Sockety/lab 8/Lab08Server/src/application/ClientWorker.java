package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ClientWorker extends Thread {

	TextField messageField;
	TextArea messageArea;
	Socket client;
	WebView browser;
	WebEngine webEngine;
	
	
	ArrayList<ClientWorker> workers;

	ReadThread readThread;
	
	BufferedReader in;
	PrintWriter out;
	
	public ClientWorker(TextField messageField,TextArea messageArea, Socket client,ArrayList<ClientWorker> workers, WebEngine webEngine){
		this.messageArea=messageArea;
		this.messageField = messageField;
		this.client = client;
		this.workers = workers;
		this.webEngine= webEngine;
		
		
		
	}
	
	
	public void run()
	{
		 try{
			   
			  in = new BufferedReader(new InputStreamReader(
		                           client.getInputStream()));
		   out = new PrintWriter(client.getOutputStream(), 
		                         true);

	
		   readThread = new ReadThread(messageArea,in,workers,this,webEngine);
		 
		  } catch (IOException e) {
		    System.out.println("Reading failed");
		    System.exit(-1);
		  }
		  readThread.start();
	}

}
