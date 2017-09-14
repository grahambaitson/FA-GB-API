package fa.gb.pojo;

import java.util.Date;
import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;

public class BasePojo {
	
	private final UUID id;
	private final Date createdDate;
	private final Date modifiedDate;
	private final Date deletedDate;

	public BasePojo() {
		this.id = null;
		this.createdDate = null;
		this.modifiedDate = null;
		this.deletedDate = null;
	}
	
	public BasePojo(UUID id) {
		this.id = id;
		this.createdDate = new Date();
		this.modifiedDate = null;
		this.deletedDate = null;
	}
	
	public BasePojo(UUID id, Date createdDate) {
		this.id = id;
		this.createdDate = createdDate;
		this.modifiedDate = null;
		this.deletedDate = null;
	}
	
	public BasePojo(UUID id, Date createdDate, Date modifiedDate, Date deletedDate) {
		this.id = id;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.deletedDate = deletedDate;
	}
	
	@ApiModelProperty(value = "The id for the object", example = "00000000-0000-0000-0000-000000000000", required = true)
	public UUID getId() {
		return this.id;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}
	
	public Date getModifiedDate() {
		return this.modifiedDate;
	}
	
	public Date getDeletedDate() {
		return this.deletedDate;
	}
	
}
