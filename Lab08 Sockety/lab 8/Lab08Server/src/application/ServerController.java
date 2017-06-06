package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ServerController extends Thread {

	public ServerSocket server;
	public Socket client;
	BufferedReader in;
	PrintWriter out;
	BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
	
	ArrayList<ClientWorker> workers = new ArrayList<ClientWorker>();
	@FXML
	Button sendButton;
	@FXML
	TextField messageField;
	@FXML
	TextArea messageArea;
	@FXML
	Button writeMessage;
	@FXML
	HTMLEditor sendHtml;
	@FXML
	WebView browser;
	@FXML
	Button startButton;
	@FXML
	Button stopButton;
	
	
	
	WebEngine webEngine;
	
	ReadThread readThread;

	
	boolean stopSerwer = false;
	boolean stopListen = true;
	
	public ServerController()
	{
		super();
		
	}

	
	
	public void listenSocket(){
		webEngine = browser.getEngine();
		
		

		boolean firstTime = true;
		while(!stopSerwer)
		{
			


		
			
			
			while(!stopListen)
			{
				
				if(firstTime)
				{try{
				    server = new ServerSocket(6665); 
				   
				  } catch (IOException e) {
				    System.out.println("Could not listen on port 4321");
				    System.exit(-1);
				  }
				firstTime = false;
				}
				
				
				try{
			    client = server.accept();
			    System.out.println("Poloczono z klientem: " + client.getInetAddress());
			    ClientWorker clientWorker = new ClientWorker(messageField,messageArea,client,workers,webEngine);
			    workers.add(clientWorker);
			    clientWorker.start();
			} catch (IOException e) {
			    System.out.println("Accept failed: 4321");
			    System.exit(-1);
			  }





			
		 }
		}
	}
	@FXML
	public void send()
	{
		//String message = messageField.getText();
		String message = sendHtml.getHtmlText();

		
	for(ClientWorker worker : workers)
		{
			try {
				out = new PrintWriter( worker.client.getOutputStream());
			
			} catch (IOException e) {
				System.out.println("Nie udalo sie wyslac wiadomosci");
				
				e.printStackTrace();
				return;
			}
			
			out.println("Server" + message);
			out.flush();
			
		}
		

	messageArea.appendText("Server: " + message + "\n");
	appendHtml("Server" + message);


		
	}
	
	
	
	public void run()
	{
		listenSocket();
		
	}
	
	@FXML
	public synchronized void startServer()
	{
		stopListen = false;
		sendButton.setDisable(false);
	}
	
	@FXML
	public synchronized void stopServer()
	{
		for(ClientWorker worker : workers)
		{
			try {
				out = new PrintWriter( worker.client.getOutputStream());
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.println("DISCONNECTED");
			out.flush();
			
			
			sendButton.setDisable(true);
		}
		messageArea.appendText("Server: " + "STOPPED" + "\n");
		
		
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
