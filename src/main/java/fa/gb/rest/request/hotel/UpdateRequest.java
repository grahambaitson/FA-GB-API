package fa.gb.rest.request.hotel;

import fa.gb.pojo.hotel.HotelInput;
import io.swagger.annotations.ApiModel;
import fa.gb.rest.request.BaseInputRequest;

@ApiModel
public class UpdateRequest extends BaseInputRequest<HotelInput> {
	
	public UpdateRequest() {
		super();
	}
	
	public UpdateRequest(HotelInput hotelInput) {
		super(hotelInput);
	}
	
}
