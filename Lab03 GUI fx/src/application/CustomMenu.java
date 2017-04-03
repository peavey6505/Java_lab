package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class CustomMenu extends Menu{

	public CustomMenu(String name)
	{
		super();
		setText(name);
	
		MenuItem item;
		if(name == "Program")
		{	
			item = new MenuItem("Close ");
			item.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
			        System.exit(0);
			    }
			    
			});
			item.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
			
			//item.setMnemonicParsing(true);
			getItems().add(item);
		}
		if(name == "About")
		{
			item = new MenuItem("About");
			item.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
			    	 Alert alert = new Alert(AlertType.INFORMATION);
					 alert.setTitle("About");
					 alert.setHeaderText("Example program informations");
					 alert.setContentText("Author: Tomasz Lenart");
					 alert.showAndWait();
			    }
			    
			});
			getItems().add(item);
		}

		
	}
	
}
