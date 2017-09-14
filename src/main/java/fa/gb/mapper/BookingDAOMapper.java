package fa.gb.mapper;

import fa.gb.pojo.booking.BookingDAO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class BookingDAOMapper implements RowMapper<BookingDAO> {

    @Override
    public BookingDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
        UUID id = UUID.fromString(rs.getString("id"));
        UUID guest_id = UUID.fromString(rs.getString("guest_id"));
        UUID hotel_id = UUID.fromString(rs.getString("hotel_id"));
        double rate = rs.getDouble("rate");
        Date reservationStartDate = rs.getDate("reservation_start_date");
        Date reservationEndDate = rs.getDate("reservation_end_date");
        Date createdDate = rs.getTimestamp("created_date");
        Date modifiedDate = rs.getTimestamp("modified_date");
        Date deletedDate = rs.getTimestamp("deleted_date");

        return new BookingDAO(id, reservationStartDate, reservationEndDate,
                rate, guest_id, hotel_id,
                createdDate, modifiedDate, deletedDate);
    }

}
