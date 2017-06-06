package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ReadThread extends Thread{

	TextArea messageArea;
	BufferedReader in;
	WebView browser;
	WebEngine webEngine;
	boolean disconnected;
	Button sendButton;
	
	public boolean stopped=false;
	
	public ReadThread(TextArea messageArea, BufferedReader in, PrintWriter out, Socket socket, boolean disconnected, WebEngine webEngine,
			Button sendButton)
	{
		this.messageArea = messageArea;
		this.in = in;
		this.disconnected =disconnected;
		this.webEngine = webEngine;
		this.sendButton = sendButton;
	

	}
	
	
	@Override
	public void run()
	{
		while(!stopped)
		{
			
			try {
				
				
				
				String message = in.readLine();
				
				
					
				if(message!=null)	
				{
					if(message.equals("DISCONNECTED"))
						{
							stopped = true;
							sendButton.setDisable(true);
						}
					
					
					messageArea.appendText("Server: " + message + "\n");
					appendHtml(message);
					
	
				}
			
		
			}catch(SocketException e)
			{
				System.out.println("Blad gniazda. Zamykanie gniazda");
				stopped = true;
//				try {
//					in.close();
//				} catch (IOException e1) {
//					System.out.println("Blad przy zamykaniu gniazda. Zamykanie aplikacji.");
//					e1.printStackTrace();
//				}
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("B³¹d IO");
				stopped = true;
				e.printStackTrace();
			}
		}
		disconnected = true;
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
