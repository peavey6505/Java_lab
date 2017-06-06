package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Validation {

	

	public boolean validateNick(String nickToCheck)
	{
		
		String regex = "^[a-zA-Z0-9]+$";
		
		if(nickToCheck.length() >3 && nickToCheck.length()<10 )
		{
			if (nickToCheck.matches(regex)) 
		     return true;
		
			else 
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("ERROR");
				alert.setHeaderText("Error while inserting data");
				alert.setContentText("Your nick must contain only letters and numbers");
	
				alert.showAndWait();
				return false;
			}
		}
		else 
		{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("ERROR");
		alert.setHeaderText("Error while inserting data");
	
		if(nickToCheck.length() <3)
			{alert.setContentText("Your nick is too short");
			alert.showAndWait();
			}
		else if(nickToCheck.length()>10)
			{alert.setContentText("Your nick is too long");
			alert.showAndWait();
			}
		
		
		return false;
		}
	}
	
	public boolean validatePassword(String passwordToCheck)
	{
		String regex = "^[a-zA-Z0-9]+$";
		
		if(passwordToCheck.length() >3 && passwordToCheck.length()<15 )
		{
			if (passwordToCheck.matches(regex)) 
		     return true;
		
			else 
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("ERROR");
				alert.setHeaderText("Error while inserting data");
				alert.setContentText("Your nick must contain only letters and numbers");
	
				alert.showAndWait();
				return false;
			}
		}
		else 
		{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("ERROR");
		alert.setHeaderText("Error while inserting data");
	
		if(passwordToCheck.length() <3)
			{alert.setContentText("Your password is too short");
			alert.showAndWait();
			}
		else if(passwordToCheck.length()>15)
			{alert.setContentText("Your password is too long");
			alert.showAndWait();
			}
		
		
		return false;
		}
	}
	
	public boolean validateCity(String cityToCheck)
	{
		String regex = "^[a-zA-Z]+$";
		
		if(cityToCheck.length() >3 && cityToCheck.length()<15 )
		{
			if (cityToCheck.matches(regex)) 
		     return true;
		
			else 
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("ERROR");
				alert.setHeaderText("Error while inserting data");
				alert.setContentText("Your City must contain only letters!");
	
				alert.showAndWait();
				return false;
			}
		}
		else 
		{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("ERROR");
		alert.setHeaderText("Error while inserting data");
	
		if(cityToCheck.length() <3)
			{alert.setContentText("Your city is too short");
			alert.showAndWait();
			}
		else if(cityToCheck.length()>15)
			{alert.setContentText("Your city is too long");
			alert.showAndWait();
			}
		
		
		return false;
		}
	}
	
	public boolean validateRole(String roleToCheck)
	{
		String regex = "^[A-Z]+$";
		
		if(roleToCheck.length() >3 && roleToCheck.length()<15 )
		{
			if (roleToCheck.matches(regex)) 
		     return true;
		
			else 
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("ERROR");
				alert.setHeaderText("Error while inserting data");
				alert.setContentText("Your role must contain only capital letters!");
	
				alert.showAndWait();
				return false;
			}
		}
		else 
		{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("ERROR");
		alert.setHeaderText("Error while inserting data");
	
		if(roleToCheck.length() <3)
			{alert.setContentText("Your role is too short");
			alert.showAndWait();
			return false;
			}
		else if(roleToCheck.length()>15)
			{alert.setContentText("Your role is too long");
			alert.showAndWait();
			return false;
			}
		
		
		return false;
		}
	}
	
}
