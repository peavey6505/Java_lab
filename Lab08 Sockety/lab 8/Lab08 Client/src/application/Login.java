package application;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Login {
 

	
	
    public static boolean authenticate(String username, String password) {
       
    	String usernameProperties=null;
    	Properties properties = new Properties();
    	try {
    	  properties.load(new FileInputStream("src/application/data.properties"));
    	 
    	  
    	  
    	} catch (IOException e) {
    	  System.out.println("Failed to load");
    	}
    	
    	if(properties.getProperty(username+"Username") == null){
    		System.out.println("Error");
    	}
    	else
    	{	
    		usernameProperties = properties.getProperty(username+"Username");
    	}
    		
    	String passwordProperties = properties.getProperty(username +"Password");
   	    
        if (username.equals(usernameProperties) && password.equals(passwordProperties)) {
            return true;
        }
        System.out.println("Login failed");
        return false;
    }
}