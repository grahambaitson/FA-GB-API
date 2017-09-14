package fa.gb.rest.response;

import java.util.List;

import io.swagger.annotations.ApiModel;

@ApiModel
public class BaseReadAllResponse <T> extends BaseResponse {
	
	private List<T> content;
	
	public BaseReadAllResponse(boolean successful, String responseMessage, String errorMessage,
			List<T> content) {
		super(successful, responseMessage, errorMessage);
		
		this.content = content;
	}
	
	public List<T> getContent() {
		return this.content;
	}

}
