package fa.gb.mapper;

import fa.gb.pojo.hotel.Hotel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class HotelMapper implements RowMapper<Hotel> {

	@Override
	public Hotel mapRow(ResultSet rs, int rowNum) throws SQLException {
		UUID id = UUID.fromString(rs.getString("id"));
		String name = rs.getString("name");
		String address = rs.getString("address");
		Integer numberOfRooms = rs.getInt("number_of_rooms");
		double rate = rs.getDouble("rate");
		Date createdDate = rs.getTimestamp("created_date");
		Date modifiedDate = rs.getTimestamp("modified_date");
		Date deletedDate = rs.getTimestamp("deleted_date");
		
		return new Hotel(id, name, address, numberOfRooms,
				rate, createdDate, modifiedDate, deletedDate);
	}

}
