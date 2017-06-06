package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private ObservableList<Student> studentsObservable= FXCollections.observableArrayList();
	
	private Stage primaryStage;
    private BorderPane layout;
    private AnchorPane logInFormAnchorPane;
	
    CustomTabPaneController customTabPaneController;
    LogInFormController logInController;
    
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        
        initLayout();
        initTabPane();
        initBoxes();
	}

	
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	 public void initLayout() {
	        try {
	            // Load root layout from fxml file.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("Layout1.fxml"));
	            layout= (BorderPane) loader.load();

	            // Show the scene containing the root layout.
	            Scene scene = new Scene(layout);
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public void initLogForm()
	 {
	
			 try {
		            // Load root layout from fxml file.
		            FXMLLoader loader = new FXMLLoader();
		            loader.setLocation(Main.class.getResource("LogInForm.fxml"));
		            AnchorPane logInFormAnchorPane=  loader.load();
		            
		           

		            // Show the scene containing the root layout.
		            Scene scene = new Scene(logInFormAnchorPane);
		            primaryStage.setScene(scene);
		            primaryStage.show();
		            
		           	logInController = loader.getController();
		           	logInController.setMain(this);
		            
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
	 }
	 public void initTabPane() {
	        try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("CustomTabPane.fxml"));
	            AnchorPane tabPane = (AnchorPane) loader.load();

	            // Set person overview into the center of root layout.
	            layout.setTop(tabPane);
	            customTabPaneController = loader.getController();
	            customTabPaneController.setMainApp(this);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public void initBoxes()
	 { 
		 try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("InsertStudentBox.fxml"));
	            AnchorPane insertStudentBox = (AnchorPane) loader.load();

	            // Set person overview into the center of root layout.
	            
	            layout.setBottom(insertStudentBox);
	            InsertStudentBoxController  controller = loader.getController();
	       
	            controller.tabPaneController = customTabPaneController;
	    

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 
		 try {
	         // Load person overview.
	         FXMLLoader loader = new FXMLLoader();
	         loader.setLocation(Main.class.getResource("RemoveStudentBox.fxml"));
	         AnchorPane removeStudentBox = (AnchorPane) loader.load();

	      
	         
	         layout.setCenter(removeStudentBox);
	         RemoveStudentBoxController removeStudentBoxController = loader.getController();
	         removeStudentBoxController.tabPaneController = customTabPaneController;
	      

	     } catch (IOException e) {
	         e.printStackTrace();
	     }

	 }		
}
