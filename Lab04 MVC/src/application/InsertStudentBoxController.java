package application;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class InsertStudentBoxController {


	@FXML
	public Label firstNameLabel;
	@FXML
	public Label lastNameLabel;
	@FXML
	public Label ageLabel;
	@FXML
	public Label markLabel;
	
	
	@FXML
	public TextField insertFirstNameField;
	@FXML
	public TextField insertLastNameField;
	@FXML
	public TextField insertAgeField;
	@FXML
	public TextField insertMarkField;

	public CustomTabPaneController tabPaneController;
	
	
	@FXML
	public void addStudent() throws IOException, InterruptedException
	{
		boolean correct = false;
	     
		 if(!insertFirstNameField.getText().trim().isEmpty()  && !insertLastNameField.getText().trim().isEmpty() &&
					!insertAgeField.getText().trim().isEmpty() && !insertMarkField.getText().trim().isEmpty())
			 
			{
			 try{
				 Float.parseFloat(insertFirstNameField.getText());
				 

				 Alert alert = new Alert(AlertType.INFORMATION);
				 alert.setTitle("Error");
				 alert.setHeaderText("Insert student error.");
				 alert.setContentText("Insert correct type of value");
				 alert.showAndWait();
				 	 
			 }
			 catch ( NumberFormatException e){
				 try{
					 Float.parseFloat(insertLastNameField.getText());
					 
					 Alert alert = new Alert(AlertType.INFORMATION);
					 alert.setTitle("Error!");
					 alert.setHeaderText("Insert student error.");
					 alert.setContentText("Insert correct type of value");
					 alert.showAndWait();
				 }catch (NumberFormatException e2){
					 try{
						 
						 
						 Integer.parseInt(insertAgeField.getText());
						 
						 Double.parseDouble(insertMarkField.getText());
						 
						 
						 
						 correct=true;
					 }catch(NumberFormatException e3){
						 Alert alert = new Alert(AlertType.INFORMATION);
						 alert.setTitle("Error!");
						 alert.setHeaderText("Insert student error.");
						 alert.setContentText("Insert correct type of value");
						 alert.showAndWait();
					 }
				 }
				 
			 }
			 	
			 if(correct)
			 {	
				 
				Student tempStudent = new Student(insertFirstNameField.getText(),insertLastNameField.getText(),Integer.parseInt(insertAgeField.getText()),
					Double.parseDouble(insertMarkField.getText()));
	          	
				 correct=true;
			
				
				 for(Student s:tabPaneController.studentsObservable)
				 {
					 
					 if(s.getFirstName().equals( tempStudent.getFirstName()) && s.getLastName().equals(tempStudent.getLastName()))
					 { Alert alert = new Alert(AlertType.INFORMATION);
					 alert.setTitle("Error!");
					 alert.setHeaderText("Insert student error.");
					 alert.setContentText("Student already exists");
					 alert.showAndWait();
					 correct=false;
					 }
				 }
				 
	          	if(correct)
	          	{	
	          		//tabPaneController.studentsSet.add(tempStudent);
	          		tabPaneController.studentsObservable.add(tempStudent);
	          		tabPaneController.studentsObservable.sort(new MarkComparatorDescending());
		        	tabPaneController.logsStudentObservable.add(tabPaneController.log("ADDED",tempStudent));
	          		//logTab.log("ADDED", tempStudent);
		        	tabPaneController.updateHistogram(
		        			);
	          	}
	          	
			}
			
		
			}
		 else
		 {
			 Alert alert = new Alert(AlertType.INFORMATION);
			 alert.setTitle("Error!");
			 alert.setHeaderText("Insert student error.");
			 alert.setContentText("All text fields must be filled!");
			 alert.showAndWait();
		 }
	}
	
	
	
}
