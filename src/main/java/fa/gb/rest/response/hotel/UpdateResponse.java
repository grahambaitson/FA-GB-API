package fa.gb.rest.response.hotel;

import io.swagger.annotations.ApiModel;
import fa.gb.rest.response.BaseUpdateResponse;

@ApiModel
public class UpdateResponse extends BaseUpdateResponse {
	
	public UpdateResponse(boolean successful, String responseMessage, String errorMessage) {
		super(successful, responseMessage, errorMessage);
	}
	
}

