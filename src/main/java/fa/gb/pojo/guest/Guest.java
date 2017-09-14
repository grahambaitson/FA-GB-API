package fa.gb.pojo.guest;

import fa.gb.pojo.BasePojo;

import java.util.Date;
import java.util.UUID;

public class Guest extends BasePojo {

	private final String name;
	private final String emailAddress;
	private final String cellNumber;
	
	public Guest() {
		super();
		this.name = null;
		this.emailAddress = null;
		this.cellNumber = null;
	}
	
	public Guest(UUID id, String name, String emailAddress,
				 String cellNumber) {
		super(id);
		this.name = name;
		this.emailAddress = emailAddress;
		this.cellNumber = cellNumber;
	}
	
	public Guest(UUID id, String name, String emailAddress,
				 String cellNumber, Date createdDate) {
		super(id, createdDate);
		this.name = name;
		this.emailAddress = emailAddress;
		this.cellNumber = cellNumber;
	}
	
	public Guest(UUID id, String name, String emailAddress, String cellNumber,
				 Date createdDate, Date modifiedDate, Date deletedDate) {
		super(id, createdDate, modifiedDate, deletedDate);
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

}
