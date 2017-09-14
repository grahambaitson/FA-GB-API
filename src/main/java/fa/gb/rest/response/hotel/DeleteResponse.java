package fa.gb.rest.response.hotel;

import fa.gb.rest.response.BaseCreateResponse;
import io.swagger.annotations.ApiModel;

import java.util.UUID;

@ApiModel
public class DeleteResponse extends BaseCreateResponse {
	
	private final UUID id;
	
	public DeleteResponse(boolean successful, String responseMessage, String errorMessage) {
		super(successful, responseMessage, errorMessage);
		
		this.id = null;
	}
	
	public DeleteResponse(boolean successful, String responseMessage, String errorMessage,
			UUID id) {
		super(successful, responseMessage, errorMessage);
		
		this.id = id;
	}
	
	public UUID getId() {
		return this.id;
	}
	
}

