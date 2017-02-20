package by.htp.library.dao;

import java.util.List;

import by.htp.library.domain.Entity;

public interface ICRUDDao<K extends Entity> {

	List<K> findAll();

	boolean delete(int id);

	boolean delete(K entity);

	boolean create(K entity);

	K findEntityById(int id);

	boolean update(K entity);
}
