package io.ajoss.hashchatserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HashUser {

	@Id
	private String userID;
	@Column(nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	private String email;
	private Long phoneNumber;
	@Column(nullable = false)
	private String firstName;
	private String lastName;
	
	
	public HashUser() {
		super();
	}
	public HashUser(String userID, String username, String password, String email, Long phoneNumber, String firstName,
			String lastName) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public String getUserID() {
		return userID;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setPassword(String password) {
		this.password =password;
	}
	
	

}