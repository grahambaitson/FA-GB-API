package fa.gb.rest.response.hotel;

import io.swagger.annotations.ApiModel;
import fa.gb.rest.response.BaseActivateResponse;

@ApiModel
public class ActivateResponse extends BaseActivateResponse {
	
	public ActivateResponse(boolean successful, String responseMessage, String errorMessage) {
		super(successful, responseMessage, errorMessage);
	}
	
}

