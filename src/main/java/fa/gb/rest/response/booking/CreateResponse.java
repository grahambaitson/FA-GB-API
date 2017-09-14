package fa.gb.rest.response.booking;

import fa.gb.rest.response.BaseCreateResponse;
import io.swagger.annotations.ApiModel;

@ApiModel
public class CreateResponse extends BaseCreateResponse {
	
	public CreateResponse(boolean successful, String responseMessage, String errorMessage) {
		super(successful, responseMessage, errorMessage);
	}
	
}

