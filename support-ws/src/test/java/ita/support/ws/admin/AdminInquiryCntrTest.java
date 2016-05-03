package ita.support.ws.admin;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Matchers.isNotNull;

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

import com.google.gson.Gson;

import ita.support.admin.IAdminFacade;
import ita.support.admin.models.InquiryGetModel;
import ita.support.admin.models.InquiryPostModel;
import ita.support.admin.validator.CreateInquiryValidator;
import ita.support.ws.utils.TestUtil;

@RunWith(MockitoJUnitRunner.class)
public class AdminInquiryCntrTest {
	@Mock
	IAdminFacade adminFacade;

	@Mock
	CreateInquiryValidator inquiryValidator;

	@InjectMocks
	AdminInquiryCntr adminInquiryCntr = new AdminInquiryCntr();

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(adminInquiryCntr).build();
	}

	@Test
	public void testGetInquiriesByCustomers() throws Exception {

		when(adminFacade.getInquiriesByCustomer("customer")).thenReturn((List<InquiryGetModel>) getModel());
		mockMvc.perform(get("/admin/customers/{customerName}/inquiries", "customer")).andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].inquiryId").value(1))
				.andExpect(jsonPath("$[0].description").value("description"))
				.andExpect(jsonPath("$[0].createDate").value("createDate"))
				.andExpect(jsonPath("$[0].rezalt").value("rezalt")).andExpect(jsonPath("$[0].topic").value("topic"))
				.andExpect(jsonPath("$[0].customer").value("customer"));

	}

	private List<InquiryGetModel> getModel() {

		InquiryGetModel inquiryGetModel = new InquiryGetModel(1L, "description", "createDate", "rezalt", "topic",
				"customer");
		List<InquiryGetModel> inquiryModelList = new ArrayList<InquiryGetModel>();
		inquiryModelList.add(inquiryGetModel);

		return inquiryModelList;
	}

	@Test
	public void testGetInquiryByCustomers() throws Exception {

		when(adminFacade.getInquiryByCustomer("customer", 1L)).thenReturn((InquiryGetModel) getModelJson());
		mockMvc.perform(get("/admin/customers/{customerName}/inquiries/{inquiryId}", "customer", 1))
				.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.inquiryId").value(1)).andExpect(jsonPath("$.description").value("description"))
				.andExpect(jsonPath("$.createDate").value("createDate")).andExpect(jsonPath("$.rezalt").value("rezalt"))
				.andExpect(jsonPath("$.topic").value("topic")).andExpect(jsonPath("$.customer").value("customer"));

	}

	private InquiryGetModel getModelJson() {

		InquiryGetModel inquiryGetModel = new InquiryGetModel(1L, "description", "createDate", "rezalt", "topic",
				"customer");

		return inquiryGetModel;
	}

	@Test
	public void testCreateInquiry() throws Exception {
		InquiryPostModel inquiry = new InquiryPostModel("testNmae", "01-01-1970", "in progress", "op");

		when(inquiryValidator.supports(InquiryPostModel.class)).thenReturn(true);
		when(adminFacade.createInquiry((InquiryPostModel) isNotNull(), (String) isNotNull())).thenReturn(true);

		mockMvc.perform(post("/admin/customers/{customerName}/inquiries", "customer").contentType("application/json")
				.content(new Gson().toJson(inquiry))).andExpect(status().isOk());

	}

	@Test
	public void testUpdateInquiry() throws Exception {
		InquiryPostModel inquiry = new InquiryPostModel("testNmae", "01-01-1970", "in progress", "op");

		when(inquiryValidator.supports(InquiryPostModel.class)).thenReturn(true);
		when(adminFacade.updateInquiry((InquiryPostModel) isNotNull(), (String) isNotNull(), (Long) isNotNull()))
				.thenReturn(true);

		mockMvc.perform(put("/admin/customers/{customerName}/inquiries/{inquiryId}", "customer", 1L)
				.contentType("application/json").content(new Gson().toJson(inquiry))).andExpect(status().isOk());

	}

	@Test
	public void testDeleteInquiry() throws Exception {

		when(adminFacade.deleteInquiry((String) isNotNull(), (Long) isNotNull())).thenReturn(true);
		mockMvc.perform(delete("/admin/customers/{customerName}/inquiries/{inquiryId}", "customer", 1L)
				.contentType("application/json")).andExpect(status().isOk());

	}

}