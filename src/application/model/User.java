package application.model;

public class User {
	int id;
	String name;
	String password;
	String email;
	String address;
	String role;
	
	
	
	public User() {
		
	}
	public User(String name, String password, String email, String address,String role) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.address=address;
		this.role = role;
	}

	public User(int id, String name, String password, String email, String address, String role) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.address = address;
		this.role = role;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
