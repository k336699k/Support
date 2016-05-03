package ita.support.dba.dao.admin.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ita.support.dba.dao.AbstractDaoImpl;
import ita.support.dba.dao.admin.ICustomerDao;
import ita.support.dba.entities.admin.Customer;

@Repository
@Transactional
public class CustomerDaoImpl extends AbstractDaoImpl<Customer, Long> implements ICustomerDao {

	public CustomerDaoImpl() {
		super(Customer.class);
	}

	@Override
	public Customer getCustomerByName(String name) {
		Query query = getSession().createQuery("from Customer where name =:name").setString("name", name);
		Customer customer = (Customer) query.uniqueResult();
		return customer;
	}
}
