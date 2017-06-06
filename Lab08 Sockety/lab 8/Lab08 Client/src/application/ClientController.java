package application;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ClientController extends Thread {
	
	

	
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
	Button connectButton;
	@FXML
	Button disconnectButton;
	@FXML
	Button logOutButton;
	
	
	WebEngine webEngine;
	
	
	Main main;
	
	ArrayList<String> registeredUsers;
	ArrayList<String> loggedUsers;
	
	Socket socket;
	PrintWriter  out;
	BufferedReader in;
	boolean logged = true;
	
	
	ReadThread readThread;
	MyThread myThread;

	boolean makeConnection = false;
	boolean stopClient = false;
	
	boolean disconnected= false;
	
	public void setMain(Main main)
	{
		this.main=main;
	}
	public ClientController()
	{
		super();
		//sendButton.setDisable(true);
		
	}
	

	
	@FXML
	public synchronized void send()
	{

		String message = messageField.getText();
		String messageHTML = sendHtml.getHtmlText();
		
		//myThread.sendMessage(message);
		myThread.sendMessage(messageHTML);
	}

	

    @FXML 
    public synchronized void connect()
    {
    	webEngine = browser.getEngine();
    	myThread = new MyThread(socket,out,in,readThread,registeredUsers,main,messageArea,disconnected,logged,loggedUsers,webEngine,sendButton);
    	myThread.start();
    	sendButton.setDisable(false);
    	
    	
    }
    
    public synchronized void disconnect()
    {
    	if(!disconnected)
    	{	myThread.disconnect();
    	sendButton.setDisable(true);
    	}
    	
    	else
    		System.out.println("Already disconnected");

    	

    }

    public void logOut()
    {
    	myThread.logOut();

    }
}
