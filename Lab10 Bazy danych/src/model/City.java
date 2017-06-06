package model;



public class City {

	private int id;
	private String cityName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public City(int id, String cityName) {
		super();
		this.id = id;
		this.cityName = cityName;
	}
	
	
	

	
	
}
