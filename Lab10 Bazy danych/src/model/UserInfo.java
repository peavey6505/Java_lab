package model;

public class UserInfo {

	public int id;
	public int idUser;
	public int idRole;
	public int idCity;


	
	public UserInfo(int id, int idUser, int idRole, int idCity) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.idRole = idRole;
		this.idCity = idCity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdCity() {
		return idCity;
	}
	public void setIdCity(int idCity) {
		this.idCity = idCity;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdRole() {
		return idRole;
	}
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
}
