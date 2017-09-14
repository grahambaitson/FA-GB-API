package fa.gb.pojo.hotel;

import io.swagger.annotations.ApiModel;

import java.util.UUID;

@ApiModel
public class HotelInput {

	private final String name;
	private final String address;
	private final Integer numberOfRooms;
	private final double rate;
	
	public HotelInput() {
		this.name = null;
		this.address = null;
		this.numberOfRooms = 0;
		this.rate = 0;
	}
	
	public HotelInput(String name, String address, Integer numberOfRooms,
					  double rate) {
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

	public Hotel ValidateAndConvert(UUID id) {
		boolean valid = this.Validate();
		if(!valid) {
			return null;
		}
		
		return new Hotel(id,
			this.getName(),
			this.getAddress(),
			this.getNumberOfRooms(),
			this.getRate());
	}
	
	private boolean Validate() {
		return true;
	}

}
