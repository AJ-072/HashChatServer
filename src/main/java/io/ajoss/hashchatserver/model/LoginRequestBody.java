package io.ajoss.hashchatserver.model;

public class LoginRequestBody {
	private String username;
	private String password;

	
	public LoginRequestBody() {
		super();
	}

	public LoginRequestBody(String username, String password) {
		this.password = password;
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
