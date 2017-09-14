package fa.gb.rest.request.booking;

import fa.gb.pojo.booking.BookingInput;
import io.swagger.annotations.ApiModel;
import fa.gb.rest.request.BaseInputRequest;

@ApiModel
public class UpdateRequest extends BaseInputRequest<BookingInput> {
	
	public UpdateRequest() {
		super();
	}
	
	public UpdateRequest(BookingInput bookingInput) {
		super(bookingInput);
	}
	
}
