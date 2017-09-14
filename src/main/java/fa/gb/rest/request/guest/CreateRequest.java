package fa.gb.rest.request.guest;

import io.swagger.annotations.ApiModel;
import fa.gb.pojo.guest.GuestInput;

@ApiModel
public class CreateRequest {
	
	private final GuestInput guestInput;

	public CreateRequest() {
		this.guestInput = null;
	}
	
	public CreateRequest(GuestInput guestInput) {
		this.guestInput = guestInput;
	}
	
	public GuestInput getGuestInput() {
		return this.guestInput;
	}
	
}
