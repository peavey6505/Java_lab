package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LogInTabPane extends AnchorPane{

	public Login login;
	public Main main;
	Button logInButton;
	    private TextField tfUsername;
	    private PasswordField pfPassword;
	    private Label lbUsername;
	    private Label lbPassword;
	    private Button btnLogin;
	    private Button btnCancel;
	    private Button btnAddUser;
	public boolean succeeded;
	
	ArrayList<String> loggedUsers;
	
//	LoginDialog loginDialog;
	
	public LogInTabPane(Main main, ArrayList<String> loggedUsers){
		this.main = main;
		this.loggedUsers = loggedUsers;
		
		logInButton = new Button("Log In");
		AnchorPane.setBottomAnchor(logInButton, 50.0);
		
		logInButton.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	     
    	     try {
				main.changeSceneMain();
			} catch (IOException | InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	    }
    	});
	//	this.getChildren().add(logInButton);
		
		btnAddUser = new Button("Add user");
		AnchorPane.setBottomAnchor(btnAddUser, 35.0);
		AnchorPane.setRightAnchor(btnAddUser, 40.0);
		
		btnAddUser.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	     try {
				main.changeSceneRegister();
			} catch (IOException | InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	    }
    	});
		this.getChildren().add(btnAddUser);
		
	 
	        lbUsername = new Label("Username: ");
	        AnchorPane.setTopAnchor(lbUsername,20.0);
	        AnchorPane.setLeftAnchor(lbUsername,20.0);
	        this.getChildren().add(lbUsername);
	        
	        lbPassword = new Label("lbPassword: ");
	        AnchorPane.setTopAnchor(lbPassword,45.0);
	        AnchorPane.setLeftAnchor(lbPassword,20.0);
	        this.getChildren().add(lbPassword);
	        
	        
	        tfUsername = new TextField();
	        AnchorPane.setTopAnchor(tfUsername,20.0);
	        AnchorPane.setLeftAnchor(tfUsername,100.0);
	        this.getChildren().add(tfUsername);
	        
	        pfPassword = new PasswordField();
	        AnchorPane.setTopAnchor(pfPassword,45.0);
	        AnchorPane.setLeftAnchor(pfPassword,100.0);
	        this.getChildren().add(pfPassword);
	      
	        
	        btnLogin = new Button("Login");
	        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
	    	    @Override public void handle(ActionEvent e) {
	    	     
	    	    	  if (Login.authenticate(tfUsername.getText(), pfPassword.getText())) {
//		                   
		                    succeeded = true;
		                   
		                } else {
//		                
		                    succeeded = false;
		                }
	    	    	
	    	    if(succeeded)
	    	    {
	    	    try {
	    	    	main.name = tfUsername.getText();
	    	    	loggedUsers.add(main.name);
					main.changeSceneMain();
				} catch (IOException | InterruptedException e1) {
					System.out.println("Nie wczytano aplikacji.");
					e1.printStackTrace();
				}
	    	    }
	    	    }
	    	});
	        AnchorPane.setTopAnchor(btnLogin,80.0);
	        this.getChildren().add(btnLogin);
	       
	        
		
	}
}
