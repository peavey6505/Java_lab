package model;

public class Role {
    private int id;
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	private String roleName;
 
   
    public Role() {}

 
    public Role(int id, String roleName) {
		super();
		this.id = id;
		this.roleName = roleName;
	}
	@Override
    public String toString() {
        return "["+id+"] - "+roleName;
    }
}
