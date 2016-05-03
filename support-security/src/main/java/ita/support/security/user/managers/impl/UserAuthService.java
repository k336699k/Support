package ita.support.security.user.managers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import ita.support.dba.DaoFtException;
import ita.support.dba.dao.security.ISecurityDao;
import ita.support.dba.entities.security.UserAuthInfo;
import ita.support.security.user.EUserRoles;
import ita.support.security.user.EUserStatuses;
import ita.support.security.user.UserAuth;

@Service
public class UserAuthService implements UserDetailsService {

	@Autowired
	private ISecurityDao userDao;

	@Override
	public UserAuth loadUserByUsername(final String username) {

		UserAuth user = null;
		try {
			UserAuthInfo userInfo = userDao.getUserAuthInfo(username);
			if (userInfo != null) {
				user = new UserAuth(userInfo.username,
								userInfo.password,
								EUserRoles.valueOf(userInfo.role),
								EUserStatuses.valueOf(userInfo.status));
			}
		} catch (DaoFtException dex) {
			dex.printStackTrace();
		}
		return user;
	}
}