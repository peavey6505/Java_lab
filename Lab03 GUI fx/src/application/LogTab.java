package application;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

public class LogTab extends Tab implements Logger{


	ObservableList<String> logsStudent = FXCollections.observableArrayList();
	
	public LogTab()
	{
		super();
		setText("Log");

		ListView<String> list = new ListView<String>();

		logsStudent.add("Nowy student");
		list.setItems(logsStudent);
		setContent(list);
	}

	@Override
	public void log(String status, Student student) {
		
		// TODO Auto-generated method stub
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);
		 // 12/01/2011 4:48:16 PM
		
		if(status=="ADDED")
			logsStudent.add(formattedDate + ":     ["+status+"]  " + "  Mark: " + student.getMark() + "  Name: " + student.getFirstName() +" "+
		student.getLastName() +"   Age: " + student.getAge());
		
		else if(status == "REMOVED")
			logsStudent.add(formattedDate + ":     ["+status+"]  " + "  Mark: " + student.getMark() + "  Name: " + student.getFirstName() +" "+
					student.getLastName() +"   Age: " + student.getAge());
	}
}
