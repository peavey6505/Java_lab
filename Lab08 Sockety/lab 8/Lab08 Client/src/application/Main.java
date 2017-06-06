package application;
	
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	
	public String name;
	
	Scene scene1;
	Scene scene2;
	Stage stage;
	
	ArrayList<String> registeredUsers = new ArrayList<String>();
	ArrayList<String> loggedUsers = new ArrayList<String>();
	@Override
	public void start(Stage primaryStage) {
		try { 
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(Main.class.getResource("ClientView.fxml"));
//			AnchorPane clientAnchorPane=  loader.load();
//        
//       
//
//        // Show the scene containing the root layout.
//			Scene scene = new Scene(clientAnchorPane);
//			primaryStage.setScene(scene);
//			primaryStage.show();
//			
//			ClientController clientController = loader.getController();
//		//	clientController.start();

			
			stage = primaryStage;
			
			
			 LogInTabPane logInTabPane = new LogInTabPane(this,loggedUsers);
			 
			 
			 Scene scene1 = new Scene(logInTabPane,300,300);
			
			 
			 
					 
			 primaryStage.setTitle("Anchor pane");
		     primaryStage.setScene(scene1);
		     primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Nie zaladowano aplikacji");
			System.exit(0);
		}
	}
	
	

	
	public void changeSceneMain() throws IOException, InterruptedException
	{   FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("ClientView.fxml"));
		AnchorPane clientAnchorPane=  loader.load();
		
		
	  
		 Scene scene2 = new Scene(clientAnchorPane,700,700);
		 stage.setScene(scene2);
	     stage.show();
	     
	    ClientController clientController = loader.getController();
		clientController.registeredUsers = registeredUsers;
		clientController.loggedUsers = loggedUsers;
		clientController.setMain(this);
	//	clientController.start();
	}
	public void changeSceneLogin() throws IOException, InterruptedException
	{    
		 
		 LogInTabPane logInTabPane = new LogInTabPane(this,loggedUsers);
		 Scene scene1 = new Scene(logInTabPane,700,700);
		 stage.setScene(scene1);
	     stage.show();
	     
	     
	}
	public void changeSceneRegister() throws IOException, InterruptedException
	{    
		 RegisterTabPane registerTabPane = new RegisterTabPane(this,registeredUsers);
		 
		 Scene scene3 = new Scene(registerTabPane,300,300);
		 stage.setScene(scene3);
	     stage.show();
	     
	     
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
