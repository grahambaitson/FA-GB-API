package fa.gb.rest.response;

import io.swagger.annotations.ApiModel;

@ApiModel
public class LoginResponse extends BaseCreateResponse {
	
	public LoginResponse(boolean successful, String responseMessage, String errorMessage) {
		super(successful, responseMessage, errorMessage);
	}
	
}

