package fa.gb.service;

import fa.gb.pojo.booking.BookingDAO;
import fa.gb.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.gb.pojo.booking.Booking;
import fa.gb.repository.BookingRepository;
import fa.gb.util.ColourUtil;

import java.util.*;
import java.util.stream.Collectors;

@Service("bookingService")
public class BookingService implements IBookingService {
		
	@Autowired
	BookingRepository bookingRepository;

	@Override
	public void create(UUID id, BookingDAO bookingDAO) { this.bookingRepository.create(id, bookingDAO);	}

	@Override
	public BookingDAO readById(UUID id) { return this.bookingRepository.readById(id); }

	@Override
	public List<BookingDAO> readAllByDetails(BookingDAO bookingDAO){ return this.bookingRepository.readAllByDetails(bookingDAO); };

	@Override
	public List<BookingDAO> readAll() {
		return this.bookingRepository.readAll();
	}

	@Override
	public List<Booking> readAllGuestHotelBooking() {
		List<Booking> bookings = new ArrayList<Booking>();

		// Loop through all the individual bookings and combine ones that are part of the concurrent nights
		List<Booking> guestHotelBookings = this.bookingRepository.readAllGuestHotelBooking();
		for(Booking guestHotelBooking : guestHotelBookings) {
			boolean bookingUpdated = false;

			// Check the bookings that have already been read
			// Combining bookings from the same guest, for the same hotel that has the same rate
			List<Booking> previousBookings = bookings.stream()
					.filter(b -> Objects.equals(b.getGuestEmailAddress(), guestHotelBooking.getGuestEmailAddress()) &&
							Objects.equals(b.getHotelName(), guestHotelBooking.getHotelName()) &&
							Objects.equals(b.getRate(), guestHotelBooking.getRate()))
					.collect(Collectors.toList());
			for(Booking previousBooking : previousBookings) {
				// Just double check this is not a duplicate
				// NOTE: Duplicates should be getting handled on the repository side
				final Date reservationStartDate = previousBooking.getReservationStartDate();
				final Date reservationEndDate = previousBooking.getReservationEndDate();
				final Date guestReservationStartDate = guestHotelBooking.getReservationStartDate();
				final Date guestReservationEndDate = guestHotelBooking.getReservationEndDate();
				if(reservationStartDate.equals(guestReservationStartDate) && reservationEndDate.equals(guestReservationEndDate)) {
					bookingUpdated = true;
					continue;
				}

				// Check to make sure that the start date is before the end date (i.e. valid reservation)
				if(reservationStartDate.after(reservationEndDate)) {
					bookingUpdated = true;
					continue;
				}

				// Check the start date of the current reservation to be checked
				// If it is one day before, update the saved reservation's start date
				if(DateUtil.checkIfDayBefore(reservationStartDate, guestReservationStartDate)) {
					previousBooking.setReservationStartDate(DateUtil.removeDays(reservationStartDate, 1));
					bookingUpdated = true;
					continue;
				}

				// Check the end date of the current reservation to be checked
				// If it is one day after, update the saved reservation's end date
				if(DateUtil.checkIfDayAfter(reservationEndDate, guestReservationEndDate)) {
					previousBooking.setReservationEndDate(DateUtil.addDays(reservationEndDate, 1));
					bookingUpdated = true;
					continue;
				}
			}

			// If the guest hotel booking doesn't lie beside one of the saved bookings, add it to the list
			if(!bookingUpdated) {
				// Update the reservation colour for the rate (if it hasn't been done already i.e. came from the database)
				if(guestHotelBooking.getReservationColour() == null) {
					guestHotelBooking.setReservationColour(
						ColourUtil.generateRandomSeededColour(
							Double.toString(guestHotelBooking.getRate())));

					// Add a border colour to distinguish similar colours
					guestHotelBooking.setReservationBorderColour(
						ColourUtil.generateRandomSeededColour(
							Double.toString(guestHotelBooking.getRate())  + "border"));
				}

				bookings.add(guestHotelBooking);
			}
		}

		return bookings;
	}

	@Override
	public List<BookingDAO> readAllActive(boolean active) {
		return this.bookingRepository.readAllActive(active);
	}
	
	@Override
	public void update(BookingDAO bookingDAO) {
		this.bookingRepository.update(bookingDAO);
	}
	
	@Override
	public Date activate(UUID id, boolean activate) {
		return this.bookingRepository.activate(id, activate);
	}

	@Override
	public void deleteById(UUID id) {
		this.bookingRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		this.bookingRepository.deleteAll();
	}
	
	@Override
	public boolean doesBookingExist(UUID id) {
		BookingDAO currentBookingDAO = this.readById(id);
		if(currentBookingDAO != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean doesBookingExist(UUID id, BookingDAO bookingDAO) {
		boolean doesBookingExist = this.doesBookingExist(id);
		if(doesBookingExist) {
			return true;
		}

		List<BookingDAO> currentBookingDAO = this.readAllByDetails(bookingDAO);
		if(currentBookingDAO != null && !currentBookingDAO.isEmpty()) {
			return true;
		}

		return false;
	}

}
