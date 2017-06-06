package system;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import model.City;
import model.Role;
import model.User;
import model.UserInfo;




public class SystemDataBase {

    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:biblioteka.db";
 
    private Connection conn;
    private Statement stat;
    
    public SystemDataBase() {
        try {
            Class.forName(SystemDataBase.DRIVER);
        } catch (ClassNotFoundException e) {
        	
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }
 
        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
 
        createTables();
    }
    
    
    public boolean createTables()  {
        String createUsers = "CREATE TABLE IF NOT EXISTS users (id_user INTEGER PRIMARY KEY AUTOINCREMENT, nick varchar(255), password varchar(255))";
        String createRoles = "CREATE TABLE IF NOT EXISTS roles (id_role INTEGER PRIMARY KEY AUTOINCREMENT, role_name varchar(255))";
        String createCities = "CREATE TABLE IF NOT EXISTS cities (id_city INTEGER PRIMARY KEY AUTOINCREMENT, city_name varchar(255))";
        String createUsersInfo = "CREATE TABLE IF NOT EXISTS users_info (id_user_info INTEGER PRIMARY KEY AUTOINCREMENT, id_user int, id_role int, id_city int)";
        try {
            stat.execute(createUsers);
            stat.execute(createRoles);
            stat.execute(createCities);
            stat.execute(createUsersInfo);
        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean insertUser(String nick, String password) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into users values (NULL, ?, ?);");
            prepStmt.setString(1, nick);
            prepStmt.setString(2, password);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu czytelnika");
            e.printStackTrace();
            return false;
        }
        return true;

    }
    
    public boolean insertRole(String roleName) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into roles values (NULL, ?);");
            prepStmt.setString(1, roleName);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wypozyczaniu");
            return false;
        }
        return true;
    }
    
    public boolean insertCity(String cityName) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into cities values (NULL, ?);");
            prepStmt.setString(1, cityName);
           
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wypozyczaniu");
            return false;
        }
        return true;
    }
    
    
    public boolean insertUserInfo(int idUser, int idRole, int idCity) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into users_info values (NULL, ?,?,?);");
            prepStmt.setInt(1, idUser);
            prepStmt.setInt(2, idRole);
            prepStmt.setInt(3, idCity);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wypozyczaniu");
            return false;
        }
        return true;
    }
    
    
    public List<User> selectUsers() {
        List<User> users = new LinkedList<User>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM users");
            int id;
            String nick,password;
            while(result.next()) {
                id = result.getInt("id_user");
                nick = result.getString("nick");
                password = result.getString("password");
                
                users.add(new User(id, nick, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return users;
    }
    
    
    public List<UserInfo> selectUsersInfo() {
        List<UserInfo> usersInfo = new LinkedList<UserInfo>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM users_info");
            int id, idUser, idRole, idCity;
 //String createUsersInfo = "CREATE TABLE IF NOT EXISTS users_info (id_user_info INTEGER PRIMARY KEY AUTOINCREMENT, id_user int, id_role int, id_city int)";
                     
            while(result.next()) {
                id = result.getInt("id_user_info");
                idUser = result.getInt("id_user");
                idRole = result.getInt("id_role");
                idCity = result.getInt("id_city");
                usersInfo.add(new UserInfo(id, idUser,idRole,idCity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return usersInfo;
    }
    
    
    
    public User selectUser(String nickToFind)
    {
    	try {
            ResultSet result = stat.executeQuery("SELECT * FROM users WHERE nick='"+nickToFind+"';");
            int id;
            String nick,password;
           
                id = result.getInt("id_user");
                nick = result.getString("nick");
                password = result.getString("password");
                
                return new User(id, nick, password);
            
        } catch (SQLException e) {
            System.out.println("Uzytkownik nie istnieje");
            
            return null;
        }
    }
    
    public UserInfo selectUserInfo(int idNickToFind)
    {
    	try {
            ResultSet result = stat.executeQuery("SELECT * FROM users_info WHERE id_user="+idNickToFind+";");
            int id,  idNick, idRole,idCity;
            
           
           
                id = result.getInt("id_user_info");
                idNick = result.getInt("id_user");
                idRole = result.getInt("id_role");
                idCity = result.getInt("id_city");
              
                
                return new UserInfo(id, idNick, idRole,idCity);
            
        } catch (SQLException e) {
            System.out.println("Uzytkownik nie istnieje");
            
            return null;
        }
    }
    
    public List<Role> selectRoles() {
        List<Role> roles = new LinkedList<Role>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM roles");
            int id;
            String roleName;
            while(result.next()) {
                id = result.getInt("id_role");
                roleName = result.getString("role_name");
             
                
                roles.add(new Role(id, roleName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return roles;
    }
    
    public List<City> selectCities() {
        List<City> cities = new LinkedList<City>();
        try {
     //       ResultSet result = stat.executeQuery("SELECT * FROM login_histories");
           ResultSet result= conn.createStatement().executeQuery("SELECT * FROM cities");
            int id;
            String cityName;
            while(result.next()) {
                id = result.getInt("id_city");
                cityName  = result.getString("city_name");
                
             
                
                cities.add(new City(id, cityName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return cities;
    }
    
    
    public void updateNick(String nickToUpdate, String oldNick)
    {
        try {
		
			conn.createStatement().executeUpdate("UPDATE users SET nick='"+nickToUpdate+"' WHERE nick='"+oldNick+"';");
//			ResultSet result = stat.executeQuery("SELECT * FROM users WHERE nick ='tomek';");
//			 result = stat.executeQuery("SELECT * FROM users WHERE nick ='"+nickToUpdate+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    public void deleteUser(String nickToRemove)
    {
    	 try {
    			
 			conn.createStatement().executeUpdate("Delete FROM users WHERE nick='"+nickToRemove+"';");

 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    }
    
    
    public Role selectRole(String roleToFind)
    {
    	try {
            ResultSet result = conn.createStatement().executeQuery("SELECT * FROM roles WHERE role_name='"+roleToFind+"';");
            int id;
            String roleName;
            
                id = result.getInt("id_role");
                roleName = result.getString("role_name");

                
                return new Role(id, roleName);
            
        } catch (SQLException e) {
            System.out.println("Rola nie istnieje");
            
            return null;
        }
    }
    
    public void deleteRole(String roleToRemove)
    {
    	 try {
    			
 			conn.createStatement().executeUpdate("Delete FROM roles WHERE role_name='"+roleToRemove+"';");

 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    }
    
    
    public void deleteUserInfo(int idToRemove)
    {
    	 try {
    			
 			conn.createStatement().executeUpdate("Delete FROM users_info WHERE id_user_info="+idToRemove+";");

 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    }
    
    public void updateRole(String roleToUpdate, String oldRoleName)
    {
        try {
		
			conn.createStatement().executeUpdate("UPDATE roles SET role_name='"+roleToUpdate+"' WHERE role_name='"+oldRoleName+"';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    public void deleteCity(String cityToRemove)
    {
    	 try {
    			
 			conn.createStatement().executeUpdate("Delete FROM cities WHERE city_name='"+cityToRemove+"';");

 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    }
    
    public City selectCity(String cityToFind)
    {
    	try {
            ResultSet result = conn.createStatement().executeQuery("SELECT * FROM cities WHERE city_name='"+cityToFind+"';");
            int id;
            String cityName;
            
                id = result.getInt("id_city");
                cityName = result.getString("city_name");

                
                return new City(id, cityName);
            
        } catch (SQLException e) {
            System.out.println("Miasto nie istnieje");
            
            return null;
        }
    }
    
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
        }
    }

    public void updateCity(String cityToUpdate, String oldCityName)
    {
        try {
		
			conn.createStatement().executeUpdate("UPDATE cities SET city_name='"+cityToUpdate+"' WHERE city_name='"+oldCityName+"';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public String getNickById(int idToFind)
    {
    	try {
            ResultSet result = stat.executeQuery("SELECT * FROM users WHERE id_user="+idToFind+";");
            int id;
            String nick,password;
           
                id = result.getInt("id_user");
                nick = result.getString("nick");
                password = result.getString("password");
                
                return nick;
            
        } catch (SQLException e) {
            System.out.println("Uzytkownik nie istnieje");
            
            return null;
        }
    	
    }
    
    public String getRoleById(int idToFind)
    {
    	try {
            ResultSet result = stat.executeQuery("SELECT * FROM roles WHERE id_role="+idToFind+";");
            int id;
            String roleName;
           
                id = result.getInt("id_role");
                roleName = result.getString("role_name");
            
                
                return roleName;
            
        } catch (SQLException e) {
            System.out.println("Uzytkownik nie istnieje");
            
            return null;
        }
    	
    }
    
    public String getCityById(int idToFind)
    {
    	try {
            ResultSet result = stat.executeQuery("SELECT * FROM cities WHERE id_city="+idToFind+";");
            int id;
            String cityName;
           
                id = result.getInt("id_city");
                cityName = result.getString("city_name");
            
                
                return cityName;
            
        } catch (SQLException e) {
            System.out.println("Uzytkownik nie istnieje");
            
            return null;
        }
    	
    }
	
}
