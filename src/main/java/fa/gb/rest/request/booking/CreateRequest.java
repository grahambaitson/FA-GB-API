package fa.gb.rest.request.booking;

import fa.gb.pojo.booking.BookingInput;
import io.swagger.annotations.ApiModel;

@ApiModel
public class CreateRequest {
	
	private final BookingInput input;

	public CreateRequest() {
		this.input = null;
	}
	
	public CreateRequest(BookingInput input) {
		this.input = input;
	}
	
	public BookingInput getInput() {
		return this.input;
	}
	
}
