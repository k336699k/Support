package ita.support.dba.dao;

import ita.support.dba.DaoFtException;
import ita.support.utils.exceptions.EErrorMessages;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractDaoImpl<T, PK extends Serializable> implements IDao<T, PK> {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Class<T> clazz;

	public AbstractDaoImpl() {
		super();
	}

	public AbstractDaoImpl(Class<T> clazz) {
		this.clazz = clazz;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() throws DaoFtException {
		List<T> list = null;
		try {
			list = getSession().createCriteria(clazz).list();
			return list;
		} catch (HibernateException e) {
			throw new DaoFtException(e, EErrorMessages.DBA_0002, clazz.getName());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(PK id) throws DaoFtException {

		try {
			T object = (T) getSession().get(clazz, id);
			return object;
		} catch (HibernateException e) {
			throw new DaoFtException(e, EErrorMessages.DBA_0001, id);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public PK add(T object) throws DaoFtException {

		PK id = null;
		try {
			id = (PK) getSession().save(object);
			return (PK) id;
		} catch (HibernateException e) {
			throw new DaoFtException(e, EErrorMessages.DBA_0003, clazz.getName());
		}
	}

	@Override
	public void update(T object) throws DaoFtException {

		try {
			getSession().update(object);
		} catch (HibernateException e) {
			throw new DaoFtException(e, EErrorMessages.DBA_0004, clazz.getName());
		}
	}

	@Override
	public void delete(T object) throws DaoFtException {

		try {
			getSession().delete(object);
		} catch (HibernateException e) {
			throw new DaoFtException(e, EErrorMessages.DBA_0005, clazz.getName());
		}
	}

	protected Query getQuery(String hql) throws DaoFtException {
		try {
			Query query = getSession().createQuery(hql);
			return query;
		} catch (HibernateException e) {
			throw new DaoFtException(e, EErrorMessages.DBA_0006, hql);
		}
	}
}
