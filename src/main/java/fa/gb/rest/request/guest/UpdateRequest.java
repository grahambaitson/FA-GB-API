package fa.gb.rest.request.guest;

import fa.gb.pojo.guest.GuestInput;
import io.swagger.annotations.ApiModel;
import fa.gb.rest.request.BaseInputRequest;

@ApiModel
public class UpdateRequest extends BaseInputRequest<GuestInput> {
	
	public UpdateRequest() {
		super();
	}
	
	public UpdateRequest(GuestInput guestInput) {
		super(guestInput);
	}
	
}
