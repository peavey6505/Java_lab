package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class RemoveStudentBoxController {

	@FXML
	private Button removeStudentButton;
	
	
	public CustomTabPaneController tabPaneController;
	
	
	@FXML
    private void handleDeleteProduct(){
		
		
		int index = tabPaneController.studentsTableView.getSelectionModel().getSelectedIndex();
        
        if (index >= 0) {
        	Student tempStudent = tabPaneController.studentsTableView.getItems().get(index);
        	tabPaneController.logsStudentObservable.add(tabPaneController.log("REMOVED", tempStudent));
            tabPaneController.studentsTableView.getItems().remove(index);
            tabPaneController.updateHistogram();
            
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
           // alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a product in the table.");

            alert.showAndWait();
        }
    }
	
}
