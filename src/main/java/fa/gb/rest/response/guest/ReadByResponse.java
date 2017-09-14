package fa.gb.rest.response.guest;

import fa.gb.pojo.guest.Guest;
import fa.gb.rest.response.BaseReadByResponse;
import io.swagger.annotations.ApiModel;

@ApiModel
public class ReadByResponse extends BaseReadByResponse<Guest> {
	
	public ReadByResponse(boolean successful, String responseMessage, String errorMessage, Guest guest) {
		super(successful, responseMessage, errorMessage, guest);
	}
	
}

