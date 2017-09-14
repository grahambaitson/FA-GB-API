package fa.gb.rest.response;

import java.util.Date;

import io.swagger.annotations.ApiModel;

@ApiModel
public class BaseResponse {
	
	private final boolean successful;
	private final String responseMessage;
	private final String errorMessage;
	private final Date date;
	
	public BaseResponse(boolean successful, String responseMessage, String errorMessage) {
		this.successful = successful;
		this.responseMessage = responseMessage;
		this.errorMessage = errorMessage;
		this.date = new Date();
	}
	
	public boolean getSuccessful() {
		return this.successful;
	}
	
	public String getResponseMessage() {
		return this.responseMessage;
	}
	
	public String getErrorMessage() {
		return this.errorMessage;
	}
	
	public Date getDate() {
		return this.date;
	}
}
