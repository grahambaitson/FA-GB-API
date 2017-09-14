package fa.gb.rest.response.booking;

import io.swagger.annotations.ApiModel;
import fa.gb.pojo.booking.Booking;
import fa.gb.rest.response.BaseReadByResponse;

@ApiModel
public class ReadByResponse extends BaseReadByResponse<Booking> {
	
	public ReadByResponse(boolean successful, String responseMessage, String errorMessage, Booking booking) {
		super(successful, responseMessage, errorMessage, booking);
	}
	
}

