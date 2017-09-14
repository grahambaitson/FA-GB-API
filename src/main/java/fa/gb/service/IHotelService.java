package fa.gb.service;

import fa.gb.pojo.hotel.Hotel;

import java.util.List;
import java.util.UUID;

public interface IHotelService extends IBaseService<Hotel> {

	Hotel readByName(String name);

	Hotel readByAddress(String address);

	List<Hotel> readAllActive(boolean active);
	
	boolean doesHotelExist(UUID id);

	boolean doesHotelExist(UUID id, Hotel hotel);
	
}
