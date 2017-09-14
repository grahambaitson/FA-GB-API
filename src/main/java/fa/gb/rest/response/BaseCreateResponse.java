package fa.gb.rest.response;

import io.swagger.annotations.ApiModel;

@ApiModel
public class BaseCreateResponse extends BaseResponse {
	
	public BaseCreateResponse(boolean successful, String responseMessage, String errorMessage) {
		super(successful, responseMessage, errorMessage);
	}

}
