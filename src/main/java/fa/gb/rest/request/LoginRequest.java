package fa.gb.rest.request;

import io.swagger.annotations.ApiModel;

@ApiModel
public class LoginRequest{

	private final String grant_type;
	private final String username;
	private final String password;
	
	public LoginRequest() {
		this.grant_type = null;
		this.username = null;
		this.password = null;
	}
	
	public LoginRequest(String grant_type, String username, String password) {
		this.grant_type = grant_type;
		this.username = username;
		this.password = password;
	}
	
	public String getGrantType() {
		return this.grant_type;
	}
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}		
}