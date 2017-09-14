package fa.gb.repository;

import fa.gb.mapper.HotelMapper;
import fa.gb.pojo.hotel.Hotel;
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
public class HotelRepository implements IHotelRepository {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDateSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void create(UUID id, Hotel hotel) {
		long currentTime = DateUtil.getCurrentUTCTime();
		String sql = "INSERT INTO " + SCHEMA_PREFIX + "\"" + TABLE_NAME_HOTEL + "\" " +
				"(id, name, address, number_of_rooms, rate,  created_date) " +
				"VALUES (?, ?, ?, ?, ?, ?);";
				
		jdbcTemplate.update(sql, new Object[] { 
				id,
				hotel.getName(),
				hotel.getAddress(),
				hotel.getNumberOfRooms(),
				hotel.getRate(),
				DateUtil.convertUTCTimeToSQLTimestamp(currentTime)
		});
	}		

	@Override
	public Hotel readById(UUID id) {
		Hotel hotel = null;
		
		try {
			String sql = "SELECT * FROM " + SCHEMA_PREFIX + "\"" + TABLE_NAME_HOTEL + "\" " +
					"WHERE id = ?;";

			hotel = (Hotel) this.jdbcTemplate.queryForObject(sql,
			        new Object[]{id}, new HotelMapper());
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
		
		return hotel;
	}

	@Override
	public Hotel readByName(String name) {
		Hotel hotel = null;

		try {
			String sql = "SELECT * FROM " + SCHEMA_PREFIX + "\"" + TABLE_NAME_HOTEL + "\" h " +
					"WHERE UPPER(h.name) = UPPER(?);";

			hotel = (Hotel) this.jdbcTemplate.queryForObject(sql,
					new Object[]{ name }, new HotelMapper());
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}

		return hotel;
	}

	@Override
	public Hotel readByAddress(String address) {
		Hotel hotel = null;

		try {
			String sql = "SELECT * FROM " + SCHEMA_PREFIX + "\"" + TABLE_NAME_HOTEL + "\" h " +
					"WHERE UPPER(h.address) = UPPER(?);";

			hotel = (Hotel) this.jdbcTemplate.queryForObject(sql,
					new Object[]{ address }, new HotelMapper());
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}

		return hotel;
	}

	@Override
	public List<Hotel> readAll() {
		String sql = "SELECT * FROM " + SCHEMA_PREFIX + "\"" + TABLE_NAME_HOTEL + "\" h " +
				"ORDER BY h.name ASC, h.created_date DESC;";
		
		List<Hotel> hotels = this.jdbcTemplate.query(sql, new HotelMapper());
		
		return hotels;
	}
	
	@Override
	public List<Hotel> readAllActive(boolean active) {
		String sqlActiveCheck = (active ? "IS NULL" : "IS NOT NULL");
		String sql = "SELECT * FROM " + SCHEMA_PREFIX + "\"" + TABLE_NAME_HOTEL + "\" h " +
				"WHERE deleted_date " + sqlActiveCheck + 
				"ORDER BY h.created_date DESC;";
		
		List<Hotel> hotels = this.jdbcTemplate.query(sql, new HotelMapper());
		
		return hotels;
	}

	@Override
	public void update(Hotel hotel) {
		long currentTime = DateUtil.getCurrentUTCTime();
		String sql = "UPDATE " + SCHEMA_PREFIX + "\"" + TABLE_NAME_HOTEL + "\" " +
				"SET name = ?, address = ?, " +
				"number_of_rooms = ?, rate = ?, " +
				"modified_date = ? " +
				"WHERE id = ?;";
		
		jdbcTemplate.update(sql, new Object[] {
				hotel.getName(),
				hotel.getAddress(),
				hotel.getNumberOfRooms(),
				hotel.getRate(),
				DateUtil.convertUTCTimeToSQLTimestamp(currentTime),
				hotel.getId()
		});	
	}
	
	@Override
	public Date activate(UUID id, boolean activate) {
		Date date = DateUtil.getCurrentUTCDate();
		long currentTime = DateUtil.getCurrentUTCTime(date);
		String sql = "UPDATE " + SCHEMA_PREFIX + "\"" + TABLE_NAME_HOTEL + "\" " +
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
		String sql = "DELETE FROM " + SCHEMA_PREFIX + "\"" + TABLE_NAME_HOTEL + "\" " +
				"WHERE id = ?;";
				
		jdbcTemplate.update(sql, new Object[] { 
				id
		});	
	}

	@Override
	public void deleteAll() {
		String sql = "DELETE FROM " + SCHEMA_PREFIX + "\"" + TABLE_NAME_HOTEL + "\" ;";
				
		jdbcTemplate.update(sql, new Object[] {});	
	}	

}
