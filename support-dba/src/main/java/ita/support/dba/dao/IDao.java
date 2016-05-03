package ita.support.dba.dao;

import java.io.Serializable;
import java.util.List;

import ita.support.dba.DaoFtException;

public interface IDao<T, PK extends Serializable> {

	/**
	 * Returns all instances of the type.
	 * 
	 * @return all entities
	 * @throws DataDaoServException
	 */
	List<T> getAll() throws DaoFtException;

	/**
	 * Retrieves an entity by its id.
	 * 
	 * @param id
	 *            - must not be null.
	 * @return the entity with the given id or null if none found
	 * @throws DataDaoServException
	 */
	T get(PK id) throws DaoFtException;

	/**
	 * Persist the given transient instance, first assigning a generated
	 * identifier. (Or using the current value of the identifier property if the
	 * assigned generator is used.) This operation cascades to associated
	 * instances if the association is mapped with cascade="save-update"
	 * 
	 * @param object
	 *            - a transient instance of a persistent class
	 * @return the generated identifier
	 * @throws DataDaoServException
	 */
	PK add(T object) throws DaoFtException;

	/**
	 * Either save(Object) or update(Object) the given instance, depending upon
	 * resolution of the unsaved-value checks. This operation cascades to
	 * associated instances if the association is mapped with
	 * cascade="save-update"
	 * 
	 * @param object
	 *            - a transient or detached instance containing new or updated
	 *            state
	 * @throws DataDaoServException
	 */
	void update(T object) throws DaoFtException;

	/**
	 * Deletes a given object.
	 * 
	 * @param object
	 *            - must not be null
	 * @throws DataDaoServException
	 */
	void delete(T object) throws DaoFtException;

}
