package ita.support.dba.dao.admin;

import ita.support.dba.dao.IDao;
import ita.support.dba.entities.admin.Customer;

public interface ICustomerDao extends IDao<Customer, Long> {
	
	Customer getCustomerByName(String name);

}
