package fa.gb.rest.response.guest;

import fa.gb.pojo.guest.Guest;
import fa.gb.rest.response.BaseReadAllResponse;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel
public class ReadAllResponse extends BaseReadAllResponse<Guest> {
	
	public ReadAllResponse(boolean successful, String responseMessage, String errorMessage, List<Guest> guests) {
		super(successful, responseMessage, errorMessage, guests);
	}
	
}

