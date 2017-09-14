package fa.gb.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface IBaseRepository<T> {

	static final String SCHEMA_PREFIX = "fa_services.";
	static final String TABLE_NAME_GUEST = "Guest";
	static final String TABLE_NAME_HOTEL = "Hotel";
	static final String TABLE_NAME_BOOKING = "Booking";
	static final String VIEW_NAME_GUEST_HOTEL_BOOKING = "GuestHotelBooking";
	
	void create(UUID id, T t);
	
	T readById(UUID id);
	
	List<T> readAll();
	
	void update(T t);
	
	Date activate(UUID id, boolean activate);
	
	void deleteById(UUID id);
	
	void deleteAll();
	
}
