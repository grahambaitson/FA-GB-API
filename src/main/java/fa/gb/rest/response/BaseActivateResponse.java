package fa.gb.rest.response;

import io.swagger.annotations.ApiModel;

@ApiModel
public class BaseActivateResponse extends BaseResponse {
	
	public BaseActivateResponse(boolean successful, String responseMessage, String errorMessage) {
		super(successful, responseMessage, errorMessage);
	}

}
