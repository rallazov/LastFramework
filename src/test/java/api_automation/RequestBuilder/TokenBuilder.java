package api_automation.RequestBuilder;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class TokenBuilder {
	
	@JsonPropertyOrder({"usernameOrEmailAddress","password"})
	private String usernameOrEmailAddress;
	private String password;
	
	public String getUsernameOrEmailAddress() {
		return usernameOrEmailAddress;
	}
	public void setUsernameOrEmailAddress(String usernameOrEmailAddress) {
		this.usernameOrEmailAddress = usernameOrEmailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


}
