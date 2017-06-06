package model;

public class UserInfo2 {

	public int id;
	public String nick;
	public String roleName;
	public String cityName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public UserInfo2(int id, String nick, String roleName, String cityName) {
		super();
		this.id = id;
		this.nick = nick;
		this.roleName = roleName;
		this.cityName = cityName;
	}
	
	
}
