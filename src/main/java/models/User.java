package models;

public class User {

	private String username;
	private String password;
	private String retype;
	
	public User() {}

	public User(String username, String password, String retype) {
		super();
		this.username = username;
		this.password = password;
		this.retype = retype;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRetype() {
		return retype;
	}

	public void setRetype(String retype) {
		this.retype = retype;
	}
}
