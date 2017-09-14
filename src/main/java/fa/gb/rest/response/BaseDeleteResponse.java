package fa.gb.rest.response;

import io.swagger.annotations.ApiModel;

@ApiModel
public class BaseDeleteResponse extends BaseResponse {
	
	public BaseDeleteResponse(boolean successful, String responseMessage, String errorMessage) {
		super(successful, responseMessage, errorMessage);
	}

}
