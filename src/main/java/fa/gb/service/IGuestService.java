package fa.gb.service;

import fa.gb.pojo.guest.Guest;

import java.util.List;
import java.util.UUID;

public interface IGuestService extends IBaseService<Guest> {

	Guest readByEmailAddress(String emailAddress);
	
	List<Guest> readAllActive(boolean active);
	
	boolean doesGuestExist(UUID id);
	
	boolean doesGuestExist(UUID id, Guest guest);
	
}
