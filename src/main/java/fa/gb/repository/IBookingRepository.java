package fa.gb.repository;

import fa.gb.pojo.booking.BookingDAO;
import fa.gb.pojo.booking.Booking;

import java.util.List;

public interface IBookingRepository extends IBaseRepository<BookingDAO> {

	List<BookingDAO> readAllByDetails(BookingDAO bookingDAO);

	List<Booking> readAllGuestHotelBooking();

	List<BookingDAO> readAllActive(boolean active);
	
}
