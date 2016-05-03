package ita.support.ws.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ita.support.admin.IAdminFacade;
import ita.support.admin.models.TopicGetModel;

@RestController
@RequestMapping("admin")
public class AdminTopicCntr {

	
	@Autowired
	private IAdminFacade adminFacade;

	@RequestMapping(path = "/topics", method = RequestMethod.GET)
	public ResponseEntity<?> getAllTopics() {
		List<TopicGetModel> topicModel = null;
		try {
			topicModel = adminFacade.getAllTopics();
			if (topicModel == null) {
				return new ResponseEntity<List<TopicGetModel>>(topicModel, HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<List<TopicGetModel>>(topicModel, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
	}

}
