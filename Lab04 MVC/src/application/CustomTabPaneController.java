package application;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CustomTabPaneController {


	
	
	@FXML
	Tab studentsTab;
	
	@FXML
	Tab LogTab;
	
	@FXML
	Tab HistogramTab;
	
	@FXML
	ListView<String> logStudentListView;
	
	public ObservableList<String> logsStudentObservable = FXCollections.observableArrayList();
	
	
	@FXML
	public BarChart<String,Number> barChart;
	
	@FXML
    public TableView<Student> studentsTableView;
    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String > lastNameColumn;
    @FXML
    private TableColumn<Student, String > ageColumn;
    @FXML
    private TableColumn<Student, String > markColumn;
    
	
	
	ObservableList<Student> studentsObservable = FXCollections.observableArrayList();
	
	private Stage dialogStage;
	public List<Student> studentsList;
	public Set<Student> studentsSet;
	
	
	@FXML
	public void closeClick()
	{
		System.exit(0);
	}
	@FXML
	public void aboutClick()
	{
		 Alert alert = new Alert(AlertType.INFORMATION);
		 alert.setTitle("About");
		 alert.setHeaderText("Example program informations");
		 alert.setContentText("Author: Tomasz Lenart");
		 alert.showAndWait();
	}
	public CustomTabPaneController()
	{
		
		
		
	}
	
	public void updateTable(Student student)
	{
		//studentsObservable.add(student);
	}
	@FXML
	public void initialize()
	{
//		TableView<Student> table = new TableView<Student>();
		
		try {
			studentsList = StudentsParser.parse(new File("C:\\Users\\Tomek1\\Desktop\\studenci1.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		studentsList.sort(new MarkComparatorDescending());
	
		
		for(Student s : studentsList)
			studentsObservable.add(s);
		
//		table.setItems(studentsObservable);
////	//	table.setPrefWidth(USE_COMPUTED_SIZE);
////	
//		markColumn = new TableColumn<Student,String>("Marks");
		markColumn.setCellValueFactory(new PropertyValueFactory("mark"));
////		markCol.setPrefWidth(175);
////		
//		firstNameColumn= new TableColumn<Student,String>("First Name");
		firstNameColumn.setCellValueFactory(new PropertyValueFactory("firstName"));
////		firstNameCol.setPrefWidth(175);
////		
//		lastNameColumn = new TableColumn<Student,String>("Lastaaaa Name");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory("lastName"));
////		lastNameCol.setPrefWidth(175);
////		
//		ageColumn = new TableColumn<Student,String>("Ageeeee");
		ageColumn.setCellValueFactory(new PropertyValueFactory("age"));
////		ageCol.setPrefWidth(175);
////		
//		table.getColumns().setAll(markColumn,firstNameColumn,lastNameColumn, ageColumn);
//		table.setItems(studentsObservable);
//		studentsTableView = new TableView<Student>();
//		studentsTableView.getColumns().setAll(markColumn,firstNameColumn,lastNameColumn, ageColumn);
//		studentsTableView.setItems(studentsObservable);
		
		for(Student s: studentsObservable)
			System.out.println(s);
		
		

	//	logsStudentObservable.add(new Student().toString());
		logStudentListView.setItems(logsStudentObservable);
		
		updateHistogram();
		
//	}
	}
	private Main main;
	
	public void setMainApp(Main mainApp) {
        this.main = mainApp;

        // Add observable list data to the table
        studentsTableView.setItems(studentsObservable);
        
        
    }
	
	
	public String log(String status, Student student)
	{
		String result= "";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);
		 // 12/01/2011 4:48:16 PM
		
		result += formattedDate;
		if(status=="ADDED")
			result += ":     ["+status+"]  " + "  Mark: " + student.getMark() + "  Name: " + student.getFirstName() +" "+
		student.getLastName() +"   Age: " + student.getAge();
		
		else if(status == "REMOVED")
			result += ":     ["+status+"]  " + "  Mark: " + student.getMark() + "  Name: " + student.getFirstName() +" "+
					student.getLastName() +"   Age: " + student.getAge();
		return result;
	}
	
	
	public void updateHistogram()
	{
		XYChart.Series series1 = new XYChart.Series();
		series1.getData().add(new XYChart.Data("2.0",howMany2()));
		series1.getData().add(new XYChart.Data("3.0",howMany3()));
		series1.getData().add(new XYChart.Data("3.5",howMany3andHalf()));
		series1.getData().add(new XYChart.Data("4.0",howMany4()));
		series1.getData().add(new XYChart.Data("4.5",howMany4andHalf()));
		series1.getData().add(new XYChart.Data("5.0",howMany5()));
		
		
		
		barChart.getData().clear();
		barChart.getData().add(series1);
		
	}
//*************************************COUNTING MARKS****************************************************	
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
