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
import java.net.SocketException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ReadThread extends Thread{

	TextArea messageArea;
	BufferedReader in;
	ArrayList<ClientWorker> workers;
	public boolean stopped=false;
	PrintWriter out;
	ClientWorker thisWorker;
	WebView browser;
	WebEngine webEngine;
	

	
	public ReadThread(TextArea messageArea, BufferedReader in, ArrayList<ClientWorker> workers, ClientWorker thisWorker,WebEngine webEngine)
	{
		this.messageArea = messageArea;
		this.in = in;
		this.workers = workers;
		this.thisWorker = thisWorker;
		this.webEngine = webEngine;
		

	}



	@Override
	public void run()
	{
		while(!stopped)
		{
			try {
				
				String message = null;
				
				try{ message = in.readLine(); }
				catch(SocketException e)
				{
					in.close();
					System.out.println("Blad gniazda. Zamykanie gniazda");
				}
				
				
				
				if(message == null)
					stopped = true;
				
				if(message!=null)
				{	messageArea.appendText("Klient: " +message + "\n");
				

				appendHtml(message);
				
				for(ClientWorker worker : workers)
					{if(worker != thisWorker) {
					out = new PrintWriter(worker.client.getOutputStream(), 
                        true);
					out.println(message);
					out.flush();
					}
					}
				}
				
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				stopped = true;
				e.printStackTrace();
			}
		}
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
