package com.recipe.userservices;

public class UserDTO {

	private long id;
	private String fistName;
	private String lastName;
	private String email;
	private String password;

	public UserDTO() {
		super();
	}

	public UserDTO(long id, String fistName, String lastName, String email, String password) {
		this.id = id;
		this.fistName = fistName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFistName() {
		return fistName;
	}

	public void setFistName(String fistName) {
		this.fistName = fistName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
