package application;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class CustomMenuBar extends MenuBar{
	
	Menu program;
	Menu about;
	
	
	public CustomMenuBar()
	{
		program = new CustomMenu("Program");
		about = new CustomMenu("About");
		this.getMenus().add(program);
		this.getMenus().add(about);
		
	}
	

}
