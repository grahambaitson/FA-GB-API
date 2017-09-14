package fa.gb.rest.response.booking;

import fa.gb.rest.response.BaseUpdateResponse;
import io.swagger.annotations.ApiModel;

@ApiModel
public class UpdateResponse extends BaseUpdateResponse {
	
	public UpdateResponse(boolean successful, String responseMessage, String errorMessage) {
		super(successful, responseMessage, errorMessage);
	}
	
}

