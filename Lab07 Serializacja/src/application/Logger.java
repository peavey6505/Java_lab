package application;

import java.io.FileNotFoundException;

import javafx.scene.control.Tab;

public interface Logger {

	void log(String status, Student student) throws FileNotFoundException;
}
