package fa.gb.pojo.hotel;

import fa.gb.pojo.BasePojo;

import java.util.Date;
import java.util.UUID;

public class Hotel extends BasePojo {

	private final String name;
	private final String address;
	private final Integer numberOfRooms;
	private final double rate;
	
	public Hotel() {
		super();
		this.name = null;
		this.address = null;
		this.numberOfRooms = 0;
		this.rate = 0;
	}
	
	public Hotel(UUID id, String name, String address,
				 Integer numberOfRooms, double rate) {
		super(id);
		this.name = name;
		this.address = address;
		this.numberOfRooms = numberOfRooms;
		this.rate = rate;
	}
	
	public Hotel(UUID id, String name, String address,
				 Integer numberOfRooms, double rate,
				 Date createdDate) {
		super(id, createdDate);
		this.name = name;
		this.address = address;
		this.numberOfRooms = numberOfRooms;
		this.rate = rate;
	}
	
	public Hotel(UUID id, String name, String address, Integer numberOfRooms,
				 double rate, Date createdDate, Date modifiedDate,
				 Date deletedDate) {
		super(id, createdDate, modifiedDate, deletedDate);
		this.name = name;
		this.address = address;
		this.numberOfRooms = numberOfRooms;
		this.rate = rate;
	}

	public String getName() {
		return this.name;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public Integer getNumberOfRooms() {
		return this.numberOfRooms;
	}

	public double getRate() { return this.rate; }
}
