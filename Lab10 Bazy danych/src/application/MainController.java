package application;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.City;
import model.Role;
import model.User;
import model.UserInfo;
import model.UserInfo2;
import system.SystemDataBase;

public class MainController {

	Validation validation = new Validation();
	
	@FXML
	TextArea usersArea;
	@FXML
	TextField nickField;
	@FXML
	PasswordField passwordField;
	@FXML
	TableView <User> usersView;
	@FXML
	TableColumn <User,String> userIdColumn;
	@FXML
	TableColumn <User,String> userNickColumn;
	@FXML
	TextField updateNickField;
	@FXML
	Button updateNickButton;
	
	
	@FXML
	TextField roleField;
	@FXML
	TableView <Role> rolesView;
	@FXML
	TableColumn <Role,String> roleIdColumn;
	@FXML
	TableColumn <Role,String> roleNameColumn;
	@FXML
	Button addRoleButton;
	@FXML
	Button updateRoleButton;
	@FXML 
	Button deleteRoleButton;
	@FXML
	TextField updateRoleField;
	
	
	
	
	@FXML
	TextField cityField;
	@FXML
	TableView <City> citiesView;
	@FXML
	TableColumn <City,String> cityIdColumn;
	@FXML
	TableColumn <City,String> cityNameColumn;
	@FXML
	Button addCityButton;
	@FXML
	Button updateCityButton;
	@FXML 
	Button deleteCityButton;
	@FXML
	TextField updateCityField;
	
	
	
	@FXML
	TextField idUserField;
	@FXML
	TextField idRoleField;
	@FXML
	TextField idCityField;
	@FXML
	TableView <UserInfo> usersInfoView;
	@FXML
	TableColumn <UserInfo,String> userInfoIdColumn;
	@FXML
	TableColumn <UserInfo,String> userInfoIdNickColumn;
	@FXML
	TableColumn <UserInfo,String> userInfoIdRoleNameColumn;
	@FXML
	TableColumn <UserInfo,String> userInfoIdCityNameColumn;
	@FXML
	Button addUserInfoButton;

	
	@FXML
	TableView <UserInfo2> usersInfo2View;
	@FXML
	TableColumn <UserInfo2,String> userInfoId2Column;
	@FXML
	TableColumn <UserInfo2,String> userInfoIdNick2Column;
	@FXML
	TableColumn <UserInfo2,String> userInfoIdRoleName2Column;
	@FXML
	TableColumn <UserInfo2,String> userInfoIdCityName2Column;
	
	
	
	
	ObservableList<User> usersObservable = FXCollections.observableArrayList();
	ObservableList<Role> rolesObservable = FXCollections.observableArrayList();
	ObservableList<City> citiesObservable = FXCollections.observableArrayList();
	ObservableList<UserInfo> usersInfoObservable = FXCollections.observableArrayList();
	ObservableList<UserInfo2> usersInfo2Observable = FXCollections.observableArrayList();
	
	
	public MainController()
	{
	}
	
	@FXML
	Button addUserButton;
	
	 SystemDataBase systemDataBase;
	 List<User> users;
     List<Role> roles;
     List<City> cities;
	
	public void setSystem(SystemDataBase systemDataBase)
	{
		this.systemDataBase=systemDataBase;
		
		List<User> users = systemDataBase.selectUsers();
		for(User u: users)
			usersObservable.add(u);
		

		usersView.setItems(usersObservable);

		
		
		

		List<Role> roles = systemDataBase.selectRoles();
		for(Role r: roles)
			rolesObservable.add(r);
		

		
		rolesView.setItems(rolesObservable);

		
		
		List<City> cities = systemDataBase.selectCities();
		for(City c: cities)
			citiesObservable.add(c);
		

		
		citiesView.setItems(citiesObservable);
		
		
		
		List<UserInfo> usersInfo = systemDataBase.selectUsersInfo();
		for(UserInfo uInfo: usersInfo)
		{	
			usersInfoObservable.add(uInfo);
			int id = uInfo.getId();
			String nick = systemDataBase.getNickById(uInfo.getIdUser());
			String role = systemDataBase.getRoleById(uInfo.getIdRole());
			String city = systemDataBase.getCityById(uInfo.getIdCity());
			
			usersInfo2Observable.add(new UserInfo2(id, nick, role, city));
		}


		
		usersInfoView.setItems(usersInfoObservable);
		
		usersInfo2View.setItems(usersInfo2Observable);

	
	}
	
	@FXML 
	public void insertUser()
	{

		
		String nick = nickField.getText();
		String password = passwordField.getText();
		
		if(!nick.equals("") && !password.equals(""))
		{	if(validation.validateNick(nick) )
			{
			
				if( validation.validatePassword(password))
			
				{
			
					systemDataBase.insertUser(nick, password);
					
					usersObservable.add(systemDataBase.selectUser(nick));
					nickField.clear();
					passwordField.clear();
				}
			}
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText("Error while inserting data");
			alert.setContentText("All fields must be filled!");
			alert.showAndWait();
		}
			
	}
	
	@FXML
	public void initialize()
	{
		userIdColumn.setCellValueFactory(
			    new PropertyValueFactory<User,String>("id")
			);
		userNickColumn.setCellValueFactory(
			    new PropertyValueFactory<User,String>("nick")
			);
		
		roleIdColumn.setCellValueFactory(
			    new PropertyValueFactory<Role,String>("id")
			);
		roleNameColumn.setCellValueFactory(
			    new PropertyValueFactory<Role,String>("roleName")
			);
		
		cityIdColumn.setCellValueFactory(
			    new PropertyValueFactory<City,String>("id")
			);
		cityNameColumn.setCellValueFactory(
			    new PropertyValueFactory<City,String>("cityName")
			);
		
		userInfoIdColumn.setCellValueFactory(
			    new PropertyValueFactory<UserInfo,String>("id")
			);
		
		userInfoIdNickColumn.setCellValueFactory(
			    new PropertyValueFactory<UserInfo,String>("idUser")
			);
		userInfoIdRoleNameColumn.setCellValueFactory(
			    new PropertyValueFactory<UserInfo,String>("idRole")
			);
		userInfoIdCityNameColumn.setCellValueFactory(
			    new PropertyValueFactory<UserInfo,String>("idCity")
			);
		
		
		userInfoId2Column.setCellValueFactory(
			    new PropertyValueFactory<UserInfo2,String>("id")
			);
		
		userInfoIdNick2Column.setCellValueFactory(
			    new PropertyValueFactory<UserInfo2,String>("nick")
			);
		userInfoIdRoleName2Column.setCellValueFactory(
			    new PropertyValueFactory<UserInfo2,String>("roleName")
			);
		userInfoIdCityName2Column.setCellValueFactory(
			    new PropertyValueFactory<UserInfo2,String>("cityName")
			);
		
		
	}
	@FXML
	public void selectUsers()
	{
		usersArea.clear();
		String text;
		users = systemDataBase.selectUsers();
		 System.out.println("Lista u¿ytkowników: ");
		 text="Lista u¿ytkowników: \n";
		 
	        for(User u: users)
	        {   System.out.println(u);
	        text+=u+"\n";
	        }
	        
	   usersArea.setText(text);
	}
	
	@FXML
	public void updateNick()
	{
		User userToUpdate = usersView.getSelectionModel().getSelectedItem();
		
		if(userToUpdate!=null)
		{
			String newNick = updateNickField.getText();
			
			if(!newNick.equals(""))
			{
					if(validation.validateNick(newNick))
					{
						
							
							systemDataBase.updateNick(newNick, userToUpdate.getNick());
							
							usersObservable.remove(userToUpdate);
							
							System.out.println(systemDataBase.selectUser("tomek"));
							
							
							System.out.println(systemDataBase.selectUser(newNick));
							
							usersObservable.add(systemDataBase.selectUser(newNick));
							updateNickField.clear();
					}
					else
					{
						return;
					}
			}
			else
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("ERROR");
				alert.setHeaderText("Error while updating user");
				alert.setContentText("Update nick field must be filled!");

				alert.showAndWait();
			}
		
			
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText("Error while updating user");
			alert.setContentText("Select user you want to update.");

			alert.showAndWait();
		}
	}
	
	@FXML
	public void deleteUser()
	{
		User userToRemove = usersView.getSelectionModel().getSelectedItem();
		
		if(userToRemove!=null)
		{
			String nickToRemove = userToRemove.getNick();
			systemDataBase.deleteUser(nickToRemove);
			
	
			
			User tempUser = systemDataBase.selectUser(nickToRemove);
			if(tempUser==null)
				{
				usersObservable.remove(userToRemove);
				return;
				}
			else
				System.out.println("Blad podczas usuwania uzytkownika " + nickToRemove);
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText("Error while deleting user");
			alert.setContentText("Select user you want to remove.");

			alert.showAndWait();
		}
	}
	
	
	@FXML 
	public void insertRole()
	{
		String roleName = roleField.getText();
		
		if(!roleName.equals(""))
		{
			if(validation.validateRole(roleName))
			{
				systemDataBase.insertRole(roleName);
				
				rolesObservable.add(systemDataBase.selectRole(roleName));
				roleField.clear();
			}	
			else
				return;
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText("Error while inserting user");
			alert.setContentText("Role field must be filled!");

			alert.showAndWait();
		}
		
	}
	
	@FXML
	public void insertUserInfo()
	{
		int idNick, idRole, idCity;
		if(idUserField.getText().equals("") ||idRoleField.getText().equals("") || idCityField.getText().equals("") )
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText("Error while inserting data");
			alert.setContentText("ID fields must be filled!");

			alert.showAndWait();
			return;
		}
		try{
		 idNick = Integer.parseInt(idUserField.getText());
		 idRole = Integer.parseInt(idRoleField.getText());
		 idCity = Integer.parseInt(idCityField.getText());
		}catch(NumberFormatException e){

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText("Error while inserting data");
			alert.setContentText("ID fields must contain only numbers!");

			alert.showAndWait();
			return;
		}
		
		
		
		
		systemDataBase.insertUserInfo(idNick, idRole, idCity);
		
		UserInfo tempUserInfo = systemDataBase.selectUserInfo(idNick);
		usersInfoObservable.add(tempUserInfo);
		
		int id = tempUserInfo.getId();
		String nick = systemDataBase.getNickById(tempUserInfo.getIdUser());
		String role = systemDataBase.getRoleById(tempUserInfo.getIdRole());
		String city = systemDataBase.getCityById(tempUserInfo.getIdCity());
		
		idCityField.clear();
		idRoleField.clear();
		idUserField.clear();
		usersInfo2Observable.add(new UserInfo2(id, nick, role, city));
		
	}
	@FXML
	public void deleteRole()
	{
		Role roleToRemove = rolesView.getSelectionModel().getSelectedItem();
		if(roleToRemove!=null)
		{
			String roleNameToRemove = roleToRemove.getRoleName();
			systemDataBase.deleteRole(roleNameToRemove);
			
	
			
			User tempUser = systemDataBase.selectUser(roleNameToRemove);
			if(tempUser==null)
				{
				rolesObservable.remove(roleToRemove);
				return;
				}
			else
				System.out.println("Blad podczas usuwania roli" + roleNameToRemove);
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText("Error while deleting user");
			alert.setContentText("Select role you want to delete.");

			alert.showAndWait();
		}
		
		
		
	}
	
	public void updateRole()
	{
		Role roleToUpdate = rolesView.getSelectionModel().getSelectedItem();
		
		if(roleToUpdate!=null)
		{	String newRoleName = updateRoleField.getText();
			
			if(validation.validateRole(newRoleName))
				{systemDataBase.updateRole(newRoleName, roleToUpdate.getRoleName());
				
				rolesObservable.remove(roleToUpdate);
		
				rolesObservable.add(systemDataBase.selectRole(newRoleName));
				}
			else
				return;
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText("Error while updating user");
			alert.setContentText("Select role you want to update.");

			alert.showAndWait();
		}
	}
	
	
	@FXML 
	public void insertCity()
	{
		String cityName = cityField.getText();
		
		if(!cityName.equals(""))
		{
			if(validation.validateCity(cityName))
			{
				systemDataBase.insertCity(cityName);
				
				citiesObservable.add(systemDataBase.selectCity(cityName));
				cityField.clear();
			}
			else
				return;
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText("Error while deleting user");
			alert.setContentText("City field must be filled!");

			alert.showAndWait();
		}
	}
	
	@FXML
	public void deleteCity()
	{
		City cityToRemove = citiesView.getSelectionModel().getSelectedItem();
		
		if(cityToRemove!=null)
		{
			String cityNameToRemove = cityToRemove.getCityName();
			systemDataBase.deleteCity(cityNameToRemove);
			
	
			
			City tempCity = systemDataBase.selectCity(cityNameToRemove);
			if(tempCity==null)
			{
				citiesObservable.remove(cityToRemove);
				return;
			}
			else
				System.out.println("Blad podczas usuwania miasta" + cityNameToRemove);
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText("Error while deleting city");
			alert.setContentText("Select city you want to remove.");

			alert.showAndWait();
		}
		
	}
	
	@FXML
	public void deleteUserInformation()
	{
		UserInfo tempUserInfo = usersInfoView.getSelectionModel().getSelectedItem();
		
		if(tempUserInfo!=null)
		{
			int userInfoIdToRemove = tempUserInfo.getId();
			systemDataBase.deleteUserInfo(userInfoIdToRemove);
			
			usersInfoObservable.remove(tempUserInfo);
			
			int id = tempUserInfo.getId();
			for(UserInfo2 ui2 : usersInfo2Observable)
				if(ui2.getId() == id)
				{	usersInfo2Observable.remove(ui2);
					break;
				}
			
//			UserInfo2 tempUserInfo2 = new UserInfo2(id, nick, role, city);
//			
//			usersInfo2Observable.remove(tempUserInfo2);
			
			
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText("Error while deleting city");
			alert.setContentText("Select city you want to remove.");

			alert.showAndWait();
		}
		
	}
	public void updateCity()
	{
		City cityToUpdate = citiesView.getSelectionModel().getSelectedItem();
		
		if(cityToUpdate!=null)
		{
			String newCityName = updateCityField.getText();
		
			if(validation.validateCity(newCityName))
			{systemDataBase.updateCity(newCityName, cityToUpdate.getCityName());
			
			citiesObservable.remove(cityToUpdate);
	
			citiesObservable.add(systemDataBase.selectCity(newCityName));
			}
			else
				return;
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText("Error while updating city");
			alert.setContentText("Select city you want to update.");

			alert.showAndWait();
		}
	}
}
