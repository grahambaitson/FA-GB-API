package fa.gb.repository;

import fa.gb.pojo.guest.Guest;

import java.util.List;

public interface IGuestRepository extends IBaseRepository<Guest> {

	Guest readByEmailAddress(String emailAddress);
	
	List<Guest> readAllActive(boolean active);
	
}
