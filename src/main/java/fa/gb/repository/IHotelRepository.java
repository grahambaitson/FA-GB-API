package fa.gb.repository;

import fa.gb.pojo.hotel.Hotel;

import java.util.List;

public interface IHotelRepository extends IBaseRepository<Hotel> {

    Hotel readByName(String name);

    Hotel readByAddress(String address);

    List<Hotel> readAllActive(boolean active);

}
