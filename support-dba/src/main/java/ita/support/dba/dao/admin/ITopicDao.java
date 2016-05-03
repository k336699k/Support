package ita.support.dba.dao.admin;

import ita.support.dba.dao.IDao;
import ita.support.dba.entities.admin.Topic;

public interface ITopicDao extends IDao<Topic, Long> {
	
	Topic getTopicByName(String name);

}
