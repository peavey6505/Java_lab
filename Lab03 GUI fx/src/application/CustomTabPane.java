package application;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CustomTabPane extends AnchorPane{

	
	Student tempStudent;
	Tab studentsTab;
	LogTab logTab;
	HistogramTab histogramTab;
	TabPane tabPane;
	CustomMenuBar customMenuBar;
	
	
	ObservableList<Student> studentsObservable = FXCollections.observableArrayList();
	
	Logger guiLogger;
	
	
	Button addButton;
	Button removeButton;
	
	VBox insertStudentBox;
	TextField insertFirstNameField;
	TextField insertLastNameField;
	TextField insertAgeField;
	TextField insertMarkField;
	
	VBox removeStudentBox;
	TextField removeIndex;

	
	private List<Student> studentsList;
	private Set<Student> studentsSet;
	
	
	
	
	
	
	
	public CustomTabPane() throws IOException, InterruptedException
	{

		studentsList = StudentsParser.parse(new File("C:\\Users\\Tomek1\\Desktop\\studenci1.txt"));
		
		
		initButtons();
	    initTabPane();
	    initMenuBar();
	    initTable();
	    initBoxes();
	    
	    histogramTab.updateHistogram(this);
			
	}
	
	public void addStudent() throws IOException, InterruptedException
	{
		boolean correct = false;
	     
		 if(!insertFirstNameField.getText().trim().isEmpty()  && !insertLastNameField.getText().trim().isEmpty() &&
					!insertAgeField.getText().trim().isEmpty() && !insertMarkField.getText().trim().isEmpty())
			 
			{
			 try{
				 Integer.parseInt(insertFirstNameField.getText());
				 
				 
				 
				 Alert alert = new Alert(AlertType.INFORMATION);
				 alert.setTitle("Error");
				 alert.setHeaderText("Insert student error.");
				 alert.setContentText("Insert correct type of value");
				 alert.showAndWait();
				 
				 
				 
			 }
			 catch ( NumberFormatException e){
				 try{
					 Integer.parseInt(insertLastNameField.getText());
					 
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
			 {	tempStudent = new Student(insertFirstNameField.getText(),insertLastNameField.getText(),Integer.parseInt(insertAgeField.getText()),
					Double.parseDouble(insertMarkField.getText()));
	          	
	          	studentsList.add(tempStudent);
	          	
	          	
	        	studentsObservable.add(tempStudent);
	        	studentsObservable.sort(new MarkComparatorDescending());
	        	logTab.log("ADDED", tempStudent);
	        	histogramTab.updateHistogram(this);
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
	
	public void removeStudent()
	{
		try{
			Integer.parseInt(removeIndex.getText());
			if(Integer.parseInt(removeIndex.getText()) >= studentsObservable.size()-1)
				throw new NumberFormatException();
			
			logTab.log("REMOVED", studentsObservable.get(Integer.parseInt(removeIndex.getText())));
			studentsObservable.remove(Integer.parseInt(removeIndex.getText()));
			histogramTab.updateHistogram(this);
			
		}catch(NumberFormatException e)
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			 alert.setTitle("Error!");
			 alert.setHeaderText("Remove student error.");
			 alert.setContentText("Insert correct type of value");
			 alert.showAndWait();
		}
	}
	
	public void initButtons()
	{
		 addButton = new Button("Add");
	     AnchorPane.setBottomAnchor(addButton, 10.0);
	     AnchorPane.setLeftAnchor(addButton, 180.0);
	     addButton.setOnAction(new EventHandler<ActionEvent>() {
	    	    @Override public void handle(ActionEvent e) {
	    	       try {
					addStudent();
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	    }
	    	});
	   
		
	    removeButton = new Button("Remove");
	    AnchorPane.setBottomAnchor(removeButton, 10.0);
	    AnchorPane.setLeftAnchor(removeButton, 500.0);
	    removeButton.setOnAction(new EventHandler<ActionEvent>() {
	    	    @Override public void handle(ActionEvent e) {
	    	       removeStudent();
	    	        
	    	    }
	    	});
	     
	     this.getChildren().addAll(addButton, removeButton);
	}
	
	public void initTabPane()
	{
		    tabPane = new TabPane();
			studentsTab = new StudentsTab();
			logTab = new LogTab();
			histogramTab = new HistogramTab();
			tabPane.getTabs().add(studentsTab);
			tabPane.getTabs().add(logTab);
			tabPane.getTabs().add(histogramTab);
			
			AnchorPane.setTopAnchor(tabPane, 25.0);
			AnchorPane.setLeftAnchor(tabPane, 0.0);
			AnchorPane.setRightAnchor(tabPane, 0.0);
			
			this.getChildren().add(tabPane);
	}
	
	public void initMenuBar()
	{
		customMenuBar = new CustomMenuBar();
		
		AnchorPane.setTopAnchor(customMenuBar, 1.0);
		AnchorPane.setLeftAnchor(customMenuBar, 0.0);
		AnchorPane.setRightAnchor(customMenuBar, 0.0);
		
		
		this.getChildren().add(customMenuBar);
	}
	
	public void initTable()
	{
		TableView<Student> table = new TableView<Student>();
		
		studentsList.sort(new MarkComparatorDescending());
		
		
		for(Student s : studentsList)
			studentsObservable.add(s);
		
		table.setItems(studentsObservable);
	//	table.setPrefWidth(USE_COMPUTED_SIZE);
	
		TableColumn<Student,String> markCol = new TableColumn<Student,String>("Marks");
		markCol.setCellValueFactory(new PropertyValueFactory("mark"));
		
		
		TableColumn<Student,String> firstNameCol = new TableColumn<Student,String>("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
		TableColumn<Student,String> lastNameCol = new TableColumn<Student,String>("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
		
		TableColumn<Student,String> ageCol = new TableColumn<Student,String>("Age");
		ageCol.setCellValueFactory(new PropertyValueFactory("age"));
		
		table.getColumns().setAll(markCol,firstNameCol,lastNameCol, ageCol);
		studentsTab.setContent(table);
	}
	
	public void initInsertStudentBox()
	{
		insertStudentBox = new VBox(5);
		insertFirstNameField = new TextField();
		insertLastNameField = new TextField();
		insertAgeField = new TextField();
		insertMarkField = new TextField();
		insertStudentBox.getChildren().addAll(new Label("ADD STUDENT"),new Label("First Name: "), insertFirstNameField, new Label("Last Name"), insertLastNameField,
				new Label("Age: "),insertAgeField, new Label("Mark: "), insertMarkField);
		AnchorPane.setBottomAnchor(insertStudentBox,10.0); 
		AnchorPane.setLeftAnchor(insertStudentBox,5.0); 
		
		this.getChildren().add(insertStudentBox);
	}
	
	public void initRemoveStudentBox()
	{
		removeStudentBox = new VBox(5);
		removeIndex = new TextField();
	
		removeStudentBox.getChildren().addAll(new Label("REMOVE STUDENT"), new Label("Indeks: (starting from 0)"), removeIndex);
		AnchorPane.setBottomAnchor(removeStudentBox, 10.0);
		AnchorPane.setLeftAnchor(removeStudentBox, 300.0);
		this.getChildren().add(removeStudentBox);
	}
	
	public void initBoxes()
	{
		initInsertStudentBox();
		initRemoveStudentBox();
	}
	
	public int howMany2()
	{
		int result=0;
		for(Student s : studentsObservable)
			if(s.getMark()==2.0)
				result++;
		return result;
	}
	
	public int howMany3andHalf()
	{
		int result=0;
		for(Student s : studentsObservable)
			if(s.getMark()==3.5)
				result++;
		return result;
	}
	public int howMany3()
	{
		int result=0;
		for(Student s : studentsObservable)
			if(s.getMark()==3.0)
				result++;
		return result;
	}
	public int howMany4()
	{
		int result=0;
		for(Student s : studentsObservable)
			if(s.getMark()==4.0)
				result++;
		return result;
	}
	public int howMany4andHalf()
	{
		int result=0;
		for(Student s : studentsObservable)
			if(s.getMark()==4.5)
				result++;
		return result;
	}
	public int howMany5()
	{
		int result=0;
		for(Student s : studentsObservable)
			if(s.getMark()==5.0)
				result++;
		return result;
	}
	
}
