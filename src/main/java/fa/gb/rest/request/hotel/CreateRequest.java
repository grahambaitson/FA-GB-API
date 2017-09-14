package fa.gb.rest.request.hotel;

import fa.gb.pojo.hotel.HotelInput;
import io.swagger.annotations.ApiModel;

@ApiModel
public class CreateRequest {
	
	private final HotelInput hotelInput;

	public CreateRequest() {
		this.hotelInput = null;
	}
	
	public CreateRequest(HotelInput hotelInput) {
		this.hotelInput = hotelInput;
	}
	
	public HotelInput getHotelInput() {
		return this.hotelInput;
	}
	
}
