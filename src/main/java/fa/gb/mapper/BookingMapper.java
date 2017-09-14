package fa.gb.mapper;

import org.springframework.jdbc.core.RowMapper;
import fa.gb.pojo.booking.Booking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class BookingMapper implements RowMapper<Booking> {

	@Override
	public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
		UUID id = UUID.fromString(rs.getString("id"));
		Date reservationStartDate = rs.getDate("reservation_start_date");
		Date reservationEndDate = rs.getDate("reservation_end_date");
		double rate = rs.getDouble("booking_rate");
		String guestEmailAddress = rs.getString("guest_email_address");
		String hotelName = rs.getString("hotel_name");
		Date createdDate = rs.getTimestamp("created_date");
		Date modifiedDate = rs.getTimestamp("modified_date");
		Date deletedDate = rs.getTimestamp("deleted_date");
		
		return new Booking(id, reservationStartDate, reservationEndDate,
				rate, guestEmailAddress, hotelName,
				createdDate, modifiedDate, deletedDate);
	}

}
