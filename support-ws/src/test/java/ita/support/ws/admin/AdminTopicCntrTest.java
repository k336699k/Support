package ita.support.ws.admin;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ita.support.admin.IAdminFacade;
import ita.support.admin.models.TopicGetModel;
import ita.support.ws.admin.AdminTopicCntr;
import ita.support.ws.utils.TestUtil;

@RunWith(MockitoJUnitRunner.class)
public class AdminTopicCntrTest {
	
	@Mock
	IAdminFacade adminFacade;
	
	@InjectMocks
	AdminTopicCntr adminTopicCntr = new AdminTopicCntr();

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(adminTopicCntr).build();
	}
	
	@Test
	public void testGetAllTopics() throws Exception {

		when(adminFacade.getAllTopics()).thenReturn((List<TopicGetModel>) getModel());
		mockMvc.perform(get("/admin/topics")).andExpect(status().isOk())
		.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$[0].topicId").value(1))
		.andExpect(jsonPath("$[0].name").value("Support"));
		
	}
	
	private List<TopicGetModel> getModel() {

		TopicGetModel topicGetModel = new TopicGetModel(1L, "Support");
		List<TopicGetModel> topicListModel = new ArrayList<TopicGetModel>();
		topicListModel.add(topicGetModel);

		return topicListModel;
	}

}
