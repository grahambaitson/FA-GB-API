package fa.gb.service;

import fa.gb.pojo.hotel.Hotel;
import fa.gb.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("hotelService")
public class HotelService implements IHotelService {
		
	@Autowired
    HotelRepository hotelRepository;

	@Override
	public void create(UUID id, Hotel hotel) {
		this.hotelRepository.create(id, hotel);
	}

	@Override
	public Hotel readById(UUID id) {
		return this.hotelRepository.readById(id);
	}

	@Override
	public Hotel readByName(String name) {
		return this.hotelRepository.readByName(name);
	}

	@Override
	public Hotel readByAddress(String address) {
		return this.hotelRepository.readByAddress(address);
	}

	@Override
	public List<Hotel> readAll() {
		return this.hotelRepository.readAll();
	}
	
	@Override
	public List<Hotel> readAllActive(boolean active) {
		return this.hotelRepository.readAllActive(active);
	}
	
	@Override
	public void update(Hotel hotel) {
		this.hotelRepository.update(hotel);
	}
	
	@Override
	public Date activate(UUID id, boolean activate) {
		return this.hotelRepository.activate(id, activate);
	}

	@Override
	public void deleteById(UUID id) {
		this.hotelRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		this.hotelRepository.deleteAll();
	}
	
	@Override
	public boolean doesHotelExist(UUID id) {
		Hotel currentHotel = this.readById(id);
		if(currentHotel != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean doesHotelExist(UUID id, Hotel hotel) {
		boolean doesHotelExist = this.doesHotelExist(id);
		if(doesHotelExist) {
			return true;
		}

		Hotel currentNameHotel = this.readByName(hotel.getName());
		if(currentNameHotel != null) {
			return true;
		}

		return false;
	}

}
