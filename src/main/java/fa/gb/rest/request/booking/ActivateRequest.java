package fa.gb.rest.request.booking;

import io.swagger.annotations.ApiModel;

@ApiModel
public class ActivateRequest {
	
	private final boolean activate;
	
	public ActivateRequest() {
		this.activate = false;
	}
	
	public ActivateRequest(boolean activate) {
		this.activate = activate;
	}
	
	public boolean getActivate() {
		return this.activate;
	}
}
