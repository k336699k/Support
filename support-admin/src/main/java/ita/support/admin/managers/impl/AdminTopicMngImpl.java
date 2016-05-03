package ita.support.admin.managers.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ita.support.admin.managers.IAdminTopicMng;
import ita.support.admin.models.TopicGetModel;
import ita.support.admin.utils.AdminModelsTransformer;
import ita.support.dba.DaoFtException;
import ita.support.dba.dao.admin.ITopicDao;
import ita.support.dba.entities.admin.Topic;
import ita.support.utils.exceptions.FtException;

@Service
@Transactional
public class AdminTopicMngImpl implements IAdminTopicMng {

	private Logger log = LoggerFactory.getLogger(AdminTopicMngImpl.class);

	@Autowired
	private ITopicDao topicDAO;

	@Autowired
	private AdminModelsTransformer modelsTransformer;

	@Override
	public List<TopicGetModel> getAllTopics() {
		log.debug("lunch metod getAllTopics()");
		List<Topic> topics = null;
		try {
			topics = topicDAO.getAll();
		} catch (DaoFtException e) {
			throw new FtException("Cannot get Topics from DB", e);
		}
		List<TopicGetModel> topicsModelList = new ArrayList<TopicGetModel>();
		topicsModelList = modelsTransformer.buildListTopicModel(topics);
		return topicsModelList;
	}

}
