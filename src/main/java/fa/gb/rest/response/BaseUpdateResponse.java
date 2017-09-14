package fa.gb.rest.response;

import io.swagger.annotations.ApiModel;

@ApiModel
public class BaseUpdateResponse extends BaseResponse {
	
	public BaseUpdateResponse(boolean successful, String responseMessage, String errorMessage) {
		super(successful, responseMessage, errorMessage);
	}

}
