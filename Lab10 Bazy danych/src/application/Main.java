package application;
	
import java.sql.Date;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.City;
import model.Role;
import model.User;
import system.SystemDataBase;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	
	SystemDataBase systemDataBase = new SystemDataBase();
	
	Stage stage;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("MainView2.fxml"));
				AnchorPane clientAnchorPane=  loader.load();
				
				
			  
				 Scene scene2 = new Scene(clientAnchorPane,900,900);
				 stage.setScene(scene2);
			     stage.show();
			     
			     MainController mainController = loader.getController();
			     mainController.setSystem(systemDataBase);
			   
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
			
	}
	
	
	
}
