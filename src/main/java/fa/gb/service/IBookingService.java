package fa.gb.service;

import fa.gb.pojo.booking.Booking;
import fa.gb.pojo.booking.BookingDAO;

import java.util.List;
import java.util.UUID;

public interface IBookingService extends IBaseService<BookingDAO> {

	List<BookingDAO> readAllByDetails(BookingDAO bookingDAO);

	List<Booking> readAllGuestHotelBooking();

	List<BookingDAO> readAllActive(boolean active);
	
	boolean doesBookingExist(UUID id);
	
	boolean doesBookingExist(UUID id, BookingDAO bookingDAO);
	
}
