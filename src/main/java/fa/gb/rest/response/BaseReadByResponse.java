package fa.gb.rest.response;

import io.swagger.annotations.ApiModel;

@ApiModel
public class BaseReadByResponse <T> extends BaseResponse {
	
	private T content;
	
	public BaseReadByResponse(boolean successful, String responseMessage, String errorMessage,
			T content) {
		super(successful, responseMessage, errorMessage);
		
		this.content = content;
	}
	
	public T getT() {
		return this.content;
	}

}
