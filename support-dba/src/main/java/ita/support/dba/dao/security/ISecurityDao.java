package ita.support.dba.dao.security;

import ita.support.dba.DaoFtException;
import ita.support.dba.dao.IDao;
import ita.support.dba.entities.security.User;
import ita.support.dba.entities.security.UserAuthInfo;

public interface ISecurityDao extends IDao<User, Long> {

	User getUserbyUserNameAndPassword(String username, String password);
	
	UserAuthInfo getUserAuthInfo(String username) throws DaoFtException;
	
	User getUserbyEmail(String email);

}
