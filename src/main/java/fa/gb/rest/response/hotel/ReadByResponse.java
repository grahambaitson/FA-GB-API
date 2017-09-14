package fa.gb.rest.response.hotel;

import fa.gb.pojo.hotel.Hotel;
import fa.gb.rest.response.BaseReadByResponse;
import io.swagger.annotations.ApiModel;

@ApiModel
public class ReadByResponse extends BaseReadByResponse<Hotel> {
	
	public ReadByResponse(boolean successful, String responseMessage, String errorMessage, Hotel hotel) {
		super(successful, responseMessage, errorMessage, hotel);
	}
	
}

