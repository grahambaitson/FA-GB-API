package fa.gb.rest.response.guest;

import fa.gb.rest.response.BaseActivateResponse;
import io.swagger.annotations.ApiModel;

@ApiModel
public class ActivateResponse extends BaseActivateResponse {
	
	public ActivateResponse(boolean successful, String responseMessage, String errorMessage) {
		super(successful, responseMessage, errorMessage);
	}
	
}

