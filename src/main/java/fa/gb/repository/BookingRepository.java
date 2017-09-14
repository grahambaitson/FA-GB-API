package fa.gb.repository;

import fa.gb.mapper.BookingDAOMapper;
import fa.gb.mapper.BookingMapper;
import fa.gb.pojo.booking.Booking;
import fa.gb.pojo.booking.BookingDAO;
import fa.gb.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class BookingRepository implements IBookingRepository {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDateSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void create(UUID id, BookingDAO bookingDAO) {
		long currentTime = DateUtil.getCurrentUTCTime();
		String sql = "INSERT INTO " + SCHEMA_PREFIX + "\"" + TABLE_NAME_BOOKING + "\" " +
				"(id, guest_id, hotel_id, rate, reservation_start_date, reservation_end_date, created_date) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?);";
				
		jdbcTemplate.update(sql, new Object[] { 
				id,
				bookingDAO.getGuestId(),
				bookingDAO.getHotelId(),
				bookingDAO.getRate(),
				bookingDAO.getReservationStartDate(),
				bookingDAO.getReservationEndDate(),
				DateUtil.convertUTCTimeToSQLTimestamp(currentTime)
		});
	}		

	@Override
	public BookingDAO readById(UUID id) {
		BookingDAO bookingDAO = null;
		
		try {
			String sql = "SELECT * FROM " + SCHEMA_PREFIX + "\"" + TABLE_NAME_BOOKING + "\" " +
					"WHERE id = ?;";

			bookingDAO = (BookingDAO) this.jdbcTemplate.queryForObject(sql,
			        new Object[]{id}, new BookingDAOMapper());
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
		
		return bookingDAO;
	}

	@Override
	public List<BookingDAO> readAllByDetails(BookingDAO bookingDAO) {
		String sql = "SELECT * FROM " + SCHEMA_PREFIX + "\"" + TABLE_NAME_BOOKING + "\" " +
				"WHERE guest_id = ? AND hotel_id = ? AND reservation_start_date = ? AND reservation_end_date = ?;";

		List<BookingDAO> bookingDAOs = this.jdbcTemplate.query(sql,
				new Object[]{ bookingDAO.getGuestId(), bookingDAO.getHotelId(), bookingDAO.getReservationStartDate(), bookingDAO.getReservationEndDate() },
				new BookingDAOMapper());

		return bookingDAOs;
	}
	
	@Override
	public List<BookingDAO> readAll() {
		String sql = "SELECT * FROM " + SCHEMA_PREFIX + "\"" + TABLE_NAME_BOOKING + "\" b " +
				"ORDER BY b.reservation_start_date DESC, b.guest_id ASC, b.hotel_id ASC;";
		
		List<BookingDAO> bookingDAOs = this.jdbcTemplate.query(sql, new BookingDAOMapper());
		
		return bookingDAOs;
	}

	@Override
	public List<Booking> readAllGuestHotelBooking() {
		String sql = "SELECT * FROM " + SCHEMA_PREFIX + "\"" + VIEW_NAME_GUEST_HOTEL_BOOKING + "\" ghb " +
				"ORDER BY ghb.guest_name, ghb.hotel_name, ghb.reservation_start_date;";

		List<Booking> bookings = this.jdbcTemplate.query(sql, new BookingMapper());

		return bookings;
	}
	
	@Override
	public List<BookingDAO> readAllActive(boolean active) {
		String sqlActiveCheck = (active ? "IS NULL" : "IS NOT NULL");
		String sql = "SELECT * FROM " + SCHEMA_PREFIX + "\"" + TABLE_NAME_BOOKING + "\" b " +
				"WHERE deleted_date " + sqlActiveCheck + 
				"ORDER BY b.reservation_start_date DESC, b.guest_id ASC, b.hotel_id ASC;";
		
		List<BookingDAO> bookingDAOs = this.jdbcTemplate.query(sql, new BookingDAOMapper());
		
		return bookingDAOs;
	}

	@Override
	public void update(BookingDAO bookingDAO) {
		long currentTime = DateUtil.getCurrentUTCTime();
		String sql = "UPDATE " + SCHEMA_PREFIX + "\"" + TABLE_NAME_BOOKING + "\" " +
				"SET rate = ?, reservation_start_date = ?, " +
				"reservation_end_date = ?, modified_date = ? " +
				"WHERE id = ?;";
		
		jdbcTemplate.update(sql, new Object[] {
				bookingDAO.getRate(),
				bookingDAO.getReservationStartDate(),
				bookingDAO.getReservationEndDate(),
				DateUtil.convertUTCTimeToSQLTimestamp(currentTime),
				bookingDAO.getId()
		});	
	}
	
	@Override
	public Date activate(UUID id, boolean activate) {
		Date date = DateUtil.getCurrentUTCDate();
		long currentTime = DateUtil.getCurrentUTCTime(date);
		String sql = "UPDATE " + SCHEMA_PREFIX + "\"" + TABLE_NAME_BOOKING + "\" " +
				"SET modified_date = ?, deleted_date = ? " +
				"WHERE id = ?;";
				
		jdbcTemplate.update(sql, new Object[] { 
				DateUtil.convertUTCTimeToSQLTimestamp(currentTime),
				activate ? null : DateUtil.convertUTCTimeToSQLTimestamp(currentTime),
				id
		});	
		
		return activate ? null : date;
	}

	@Override
	public void deleteById(UUID id) {
		String sql = "DELETE FROM " + SCHEMA_PREFIX + "\"" + TABLE_NAME_BOOKING + "\" " +
				"WHERE id = ?;";
				
		jdbcTemplate.update(sql, new Object[] { 
				id
		});	
	}

	@Override
	public void deleteAll() {
		String sql = "DELETE FROM " + SCHEMA_PREFIX + "\"" + TABLE_NAME_BOOKING + "\" ;";
				
		jdbcTemplate.update(sql, new Object[] {});	
	}	

}
