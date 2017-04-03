package application;
	
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
	@Override
	public void start(Stage primaryStage) {


			try{
			

			 CustomTabPane customTabPane = new CustomTabPane();	 
			 Scene scene = new Scene(customTabPane,700,700);

			 primaryStage.setTitle("Anchor pane");
		     primaryStage.setScene(scene);
		     primaryStage.show();
		    
		    
		} catch(Exception e) {
			e.printStackTrace();
		 }
}
		 
	public static void main(String[] args) {
		launch(args);
	}
}
