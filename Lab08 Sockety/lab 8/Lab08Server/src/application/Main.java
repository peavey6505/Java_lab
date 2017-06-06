package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("ServerView.fxml"));
			AnchorPane serverAnchorPane=  loader.load();
        
       

        // Show the scene containing the root layout.
			Scene scene = new Scene(serverAnchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			ServerController serverController = loader.getController();
			serverController.start();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Nie zaladowano aplikacji");
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
