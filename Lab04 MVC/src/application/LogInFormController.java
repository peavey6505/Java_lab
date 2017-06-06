package application;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogInFormController {

	private ArrayList<User> users = new ArrayList<User>();
	public Main main;
	public boolean logged = false;
	
	@FXML
	private Button logInButton;
	@FXML
	private TextField loginField;
	@FXML
	private PasswordField passwordField;
	@FXML
	public void logIn()
	{
		if(loginField.getText().equals(users.get(0).getLogin()) && passwordField.getText().equals(users.get(0).getPassword()))
				logged=true;
	}
	
	public LogInFormController(Main main)
	{
		users.add(new User("projektjava","projektjava", "B5", 20,"Man"));
		
	}
	public void setMain(Main main)
	{
		this.main = main;
	}
	@FXML
	public void start1()
	{
		 this.main.initLayout();
	   this.main.initTabPane();
		        this.main.initBoxes();
	}
}
