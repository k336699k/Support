package ita.support.dba.dao.admin.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ita.support.dba.dao.AbstractDaoImpl;
import ita.support.dba.dao.admin.ITopicDao;
import ita.support.dba.entities.admin.Topic;

@Repository
@Transactional
public class TopicDaoImpl extends AbstractDaoImpl<Topic, Long> implements ITopicDao {

	public TopicDaoImpl() {
		super(Topic.class);
	}

	@Override
	public Topic getTopicByName(String name) {
		Query query = getSession().createQuery("from Topic where name =:name").setString("name", name);
		Topic topic = (Topic) query.uniqueResult();
		return topic;
	}
}
