package application.model;

public class User {
	int id;
	int chartId;
	
	String name;
	String password;
	String email;
	String address;
	String role;
	
	
	
	public int getChartId() {
		return chartId;
	}
	public void setChartId(int chartId) {
		this.chartId = chartId;
	}
	public User() {
		
	}
	public User(String name, String password, String email, String role,String address) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.address=address;
		this.role= role;
		this.chartId = -1;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public User(int id, String name, String password, String email, String role,String address) {
		
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.address = address;
		this.role= role;
		this.chartId = -1;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
