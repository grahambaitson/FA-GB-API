package fa.gb.mapper;

import fa.gb.pojo.guest.Guest;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class GuestMapper implements RowMapper<Guest> {

	@Override
	public Guest mapRow(ResultSet rs, int rowNum) throws SQLException {
		UUID id = UUID.fromString(rs.getString("id"));
		String name = rs.getString("name");
		String emailAddress = rs.getString("email_address");
		String cellNumber = rs.getString("cell_number");
		Date createdDate = rs.getTimestamp("created_date");
		Date modifiedDate = rs.getTimestamp("modified_date");
		Date deletedDate = rs.getTimestamp("deleted_date");
		
		return new Guest(id, name, emailAddress, cellNumber,
				createdDate, modifiedDate, deletedDate);
	}

}
