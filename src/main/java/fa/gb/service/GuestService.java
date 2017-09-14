package fa.gb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.gb.pojo.guest.Guest;
import fa.gb.repository.GuestRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("guestService")
public class GuestService implements IGuestService {
		
	@Autowired
	GuestRepository guestRepository;

	@Override
	public void create(UUID id, Guest guest) {
		this.guestRepository.create(id, guest);
	}

	@Override
	public Guest readById(UUID id) {
		return this.guestRepository.readById(id);
	}
	
	@Override
	public Guest readByEmailAddress(String emailAddress) {
		return this.guestRepository.readByEmailAddress(emailAddress);
	}

	@Override
	public List<Guest> readAll() {
		return this.guestRepository.readAll();
	}
	
	@Override
	public List<Guest> readAllActive(boolean active) {
		return this.guestRepository.readAllActive(active);
	}
	
	@Override
	public void update(Guest guest) {
		this.guestRepository.update(guest);
	}
	
	@Override
	public Date activate(UUID id, boolean activate) {
		return this.guestRepository.activate(id, activate);
	}

	@Override
	public void deleteById(UUID id) {
		this.guestRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		this.guestRepository.deleteAll();
	}
	
	@Override
	public boolean doesGuestExist(UUID id) {
		Guest currentGuest = this.readById(id);
		if(currentGuest != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean doesGuestExist(UUID id, Guest guest) {
		boolean doesGuestExist = this.doesGuestExist(id);
		if(doesGuestExist) {
			return true;
		}

		Guest currentGuest = this.readByEmailAddress(guest.getEmailAddress());
		if(currentGuest != null) {
			return true;
		}
		
		return false;
	}
	
}
