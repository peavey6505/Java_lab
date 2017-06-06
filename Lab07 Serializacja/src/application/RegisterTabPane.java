package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RegisterTabPane extends AnchorPane{
	public Main main;
	Button logInButton;
	    private TextField tfUsername;
	    private PasswordField pfPassword;
	    private Label lbUsername;
	    private Label lbPassword;
	    private Button btnLogin;
	    private Button btnCancel;
	    private Button btnAddUser;
	    
	    
	public RegisterTabPane(Main main)
	{
		 lbUsername = new Label("Username: ");
	        AnchorPane.setTopAnchor(lbUsername,30.0);
	        AnchorPane.setLeftAnchor(lbUsername,30.0);
	        this.getChildren().add(lbUsername);
	        
	        lbPassword = new Label("lbPassword: ");
	        AnchorPane.setTopAnchor(lbPassword,60.0);
	        AnchorPane.setLeftAnchor(lbPassword,30.0);
	        this.getChildren().add(lbPassword);
	        
	        
	        tfUsername = new TextField();
	        AnchorPane.setTopAnchor(tfUsername,30.0);
	        AnchorPane.setLeftAnchor(tfUsername,100.0);
	        this.getChildren().add(tfUsername);
	        
	        pfPassword = new PasswordField();
	        AnchorPane.setTopAnchor(pfPassword,60.0);
	        AnchorPane.setLeftAnchor(pfPassword,100.0);
	        this.getChildren().add(pfPassword);
	      
	        
	    
	       
	     
			
			btnAddUser = new Button("Register");
			AnchorPane.setTopAnchor(btnAddUser, 100.0);
			AnchorPane.setLeftAnchor(btnAddUser, 100.0);
			
			btnAddUser.setOnAction(new EventHandler<ActionEvent>() {
	    	    @Override public void handle(ActionEvent e) {
	    	     
	    	    	Register();
	    	    	System.out.println("Rejestruje uzytkownika");
	    	    	tfUsername.clear();
	    	    	pfPassword.clear();
	    	   
	    	    }
	    	});
			this.getChildren().add(btnAddUser);
			
			btnCancel = new Button("Cancel");
			AnchorPane.setTopAnchor(btnAddUser, 100.0);
			AnchorPane.setLeftAnchor(btnAddUser, 10.0);
			btnCancel.setOnAction(new EventHandler<ActionEvent>() {
	    	    @Override public void handle(ActionEvent e) {
	    	     
	    	    
	    	    	try {
					main.changeSceneLogin();
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	    }
	    	});
			this.getChildren().add(btnCancel);
		
	}
	
	public void Register(){
    	String username = tfUsername.getText();
    	String password = pfPassword.getText();
    	
    	Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);
		
    	Properties properties = new Properties();
    	try { 
    		FileOutputStream output = new FileOutputStream("src/application/data3.properties",true);
    	     properties.load(new FileInputStream("src/data.properties"));
    	    properties.setProperty(username+"Username", username);
        	properties.setProperty(username+"Password", password);
        	properties.store(output, formattedDate);

    	} catch (IOException e) {
    	  System.out.println("Failed to load");
    	}
	}
}
