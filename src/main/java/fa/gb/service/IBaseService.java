package fa.gb.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface IBaseService<T> {

	void create(UUID id, T t);
	
	T readById(UUID id);
	
	List<T> readAll();
	
	void update(T t);
	
	Date activate(UUID id, boolean activate);
	
	void deleteById(UUID id);
	
	void deleteAll();
	
}
