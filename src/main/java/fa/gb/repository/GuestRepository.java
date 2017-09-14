package fa.gb.repository;

import fa.gb.mapper.GuestMapper;
import fa.gb.pojo.guest.Guest;
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
public class GuestRepository implements IGuestRepository {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDateSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void create(UUID id, Guest guest) {
		long currentTime = DateUtil.getCurrentUTCTime();
		String sql = "INSERT INTO " + SCHEMA_PREFIX + "\"" + TABLE_NAME_GUEST + "\" " +
				"(id, name, email_address, cell_number, created_date) " +
				"VALUES (?, ?, ?, ?, ?);";
				
		jdbcTemplate.update(sql, new Object[] { 
				id,
				guest.getName(),
				guest.getEmailAddress(),
				guest.getCellNumber(),
				DateUtil.convertUTCTimeToSQLTimestamp(currentTime)
		});
	}		

	@Override
	public Guest readById(UUID id) {
		Guest guest = null;
		
		try {
			String sql = "SELECT * FROM " + SCHEMA_PREFIX + "\"" + TABLE_NAME_GUEST + "\" " +
					"WHERE id = ?;";

			guest = (Guest) this.jdbcTemplate.queryForObject(sql,
			        new Object[]{id}, new GuestMapper());
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
		
		return guest;
	}

	@Override
	public Guest readByEmailAddress(String emailAddress) {
		Guest guest = null;
		
		try {
			String sql = "SELECT * FROM " + SCHEMA_PREFIX + "\"" + TABLE_NAME_GUEST + "\" u " +
					"WHERE UPPER(u.email_address) = UPPER(?);";

			guest = (Guest) this.jdbcTemplate.queryForObject(sql,
			        new Object[]{ emailAddress }, new GuestMapper());
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
		
		return guest;
	}
	
	@Override
	public List<Guest> readAll() {
		String sql = "SELECT * FROM " + SCHEMA_PREFIX + "\"" + TABLE_NAME_GUEST + "\" u " +
				"ORDER BY u.name ASC, u.created_date DESC;";
		
		List<Guest> guests = this.jdbcTemplate.query(sql, new GuestMapper());
		
		return guests;
	}
	
	@Override
	public List<Guest> readAllActive(boolean active) {
		String sqlActiveCheck = (active ? "IS NULL" : "IS NOT NULL");
		String sql = "SELECT * FROM " + SCHEMA_PREFIX + "\"" + TABLE_NAME_GUEST + "\" u " +
				"WHERE deleted_date " + sqlActiveCheck + 
				"ORDER BY u.created_date DESC;";
		
		List<Guest> guests = this.jdbcTemplate.query(sql, new GuestMapper());
		
		return guests;
	}

	@Override
	public void update(Guest guest) {
		long currentTime = DateUtil.getCurrentUTCTime();
		String sql = "UPDATE " + SCHEMA_PREFIX + "\"" + TABLE_NAME_GUEST + "\" " +
				"SET name = ?, email_address = ?, " +
				"cell_number = ?, modified_date = ? " +
				"WHERE id = ?;";
		
		jdbcTemplate.update(sql, new Object[] {
				guest.getName(),
				guest.getEmailAddress(),
				guest.getCellNumber(),
				DateUtil.convertUTCTimeToSQLTimestamp(currentTime),
				guest.getId()
		});	
	}
	
	@Override
	public Date activate(UUID id, boolean activate) {
		Date date = DateUtil.getCurrentUTCDate();
		long currentTime = DateUtil.getCurrentUTCTime(date);
		String sql = "UPDATE " + SCHEMA_PREFIX + "\"" + TABLE_NAME_GUEST + "\" " +
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
		String sql = "DELETE FROM " + SCHEMA_PREFIX + "\"" + TABLE_NAME_GUEST + "\" " +
				"WHERE id = ?;";
				
		jdbcTemplate.update(sql, new Object[] { 
				id
		});	
	}

	@Override
	public void deleteAll() {
		String sql = "DELETE FROM " + SCHEMA_PREFIX + "\"" + TABLE_NAME_GUEST + "\" ;";
				
		jdbcTemplate.update(sql, new Object[] {});	
	}	

}
