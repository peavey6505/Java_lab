package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Main extends Application {
	
	
	Scene scene1;
	Scene scene2;
	Stage stage;
	
	
	@Override
	public void start(Stage primaryStage) {


			try{
			
			 stage = primaryStage;
			
			
			 LogInTabPane logInTabPane = new LogInTabPane(this);
			 
			 
			 Scene scene1 = new Scene(logInTabPane,300,300);
			
			 
			 
					 
			 primaryStage.setTitle("Anchor pane");
		     primaryStage.setScene(scene1);
		     primaryStage.show();
		     
		     
		    
		    
		} catch(Exception e) {
			e.printStackTrace();
		 }
}
		 
	public static void main(String[] args) {
		launch(args);
	}
	
	public void changeSceneMain() throws IOException, InterruptedException
	{    CustomTabPane customTabPane = new CustomTabPane();	  
		 Scene scene2 = new Scene(customTabPane,700,700);
		 stage.setScene(scene2);
	     stage.show();
	}
	
	public void changeSceneRegister() throws IOException, InterruptedException
	{    
		 RegisterTabPane registerTabPane = new RegisterTabPane(this);
		 
		 Scene scene3 = new Scene(registerTabPane,300,300);
		 stage.setScene(scene3);
	     stage.show();
	     
	     
	}
	
	public void changeSceneLogin() throws IOException, InterruptedException
	{    
		 
		 LogInTabPane logInTabPane = new LogInTabPane(this);
		 Scene scene1 = new Scene(logInTabPane,700,700);
		 stage.setScene(scene1);
	     stage.show();
	     
	     
	}
}
