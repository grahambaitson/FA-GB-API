package fa.gb.rest.response.booking;

import fa.gb.rest.response.BaseReadAllResponse;
import io.swagger.annotations.ApiModel;
import fa.gb.pojo.booking.Booking;

import java.util.List;

@ApiModel
public class ReadAllResponse extends BaseReadAllResponse<Booking> {
	
	public ReadAllResponse(boolean successful, String responseMessage, String errorMessage, List<Booking> bookings) {
		super(successful, responseMessage, errorMessage, bookings);
	}
	
}

