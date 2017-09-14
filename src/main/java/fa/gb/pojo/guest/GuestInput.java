package fa.gb.pojo.guest;

import io.swagger.annotations.ApiModel;

import java.util.UUID;

@ApiModel
public class GuestInput {
	
	private final String name;
	private final String emailAddress;
	private final String cellNumber;
	
	public GuestInput() {
		this.name = null;
		this.emailAddress = null;
		this.cellNumber = null;
	}
	
	public GuestInput(String name, String emailAddress, String cellNumber) {
		this.name = name;
		this.emailAddress = emailAddress;
		this.cellNumber = cellNumber;
	}

	public String getName() {
		return this.name;
	}
	
	public String getEmailAddress() {
		return this.emailAddress;
	}
	
	public String getCellNumber() {
		return this.cellNumber;
	}
	
	public Guest ValidateAndConvert(UUID id) {
		boolean valid = this.Validate();
		if(!valid) {
			return null;
		}
		
		return new Guest(id,
			this.getName(),
			this.getEmailAddress(),
			this.getCellNumber());
	}
	
	private boolean Validate() {
		return true;
	}

}
