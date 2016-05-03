package ita.support.dba.dao.security.impl;

import ita.support.dba.DaoFtException;
import ita.support.dba.dao.AbstractDaoImpl;
import ita.support.dba.dao.security.ISecurityDao;
import ita.support.dba.entities.security.User;
import ita.support.dba.entities.security.UserAuthInfo;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SecurityDaoImpl extends AbstractDaoImpl<User, Long> implements ISecurityDao {

	public SecurityDaoImpl() {
		super(User.class);
	}

	@Override
	public User getUserbyUserNameAndPassword(String username, String password) {

		Query query = getSession().createQuery("from User where username =:username and password =:password")
						.setString("username", username).setString("password", password);
		User user = (User) query.uniqueResult();

		return user;
	}

	@Override
	public UserAuthInfo getUserAuthInfo(String username) throws DaoFtException {

		Query query = getQuery("select new ita.support.dba.entities.security.UserAuthInfo(u.username, u.password, u.role, u.status)"
						+ " from User as u"
						+ " where u.username=:username").setString("username", username);
		UserAuthInfo ui = (UserAuthInfo) query.uniqueResult();
		return ui;
	}
	
	@Override
	public User getUserbyEmail(String email) {
		Query query = getSession().createQuery("from User where email =:email")
						.setString("email", email);
		User user = (User) query.uniqueResult();

		return user;
	}

}
