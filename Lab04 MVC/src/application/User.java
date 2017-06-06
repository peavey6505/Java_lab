package application;

public class User {

	private String login;
	private String password;
	private String address;
	private int age;
	private String sex;
	
	public User(String Login, String pass, String Add, int Age, String Sex)
	{
		this.login = Login;
		this.password = pass;
		this.address = Add;
		this.age = Age;
		this.sex=Sex;
	}
	
	public void setLogin(String login)
	{
		this.login=login;
	}
	public String getLogin()
	{
		return this.login;
	}
	
	public void setPassword(String Password)
	{
		this.password = Password;
	}
	public String getPassword()
	{
		return this.password;
	}
}
