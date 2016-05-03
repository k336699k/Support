package ita.support.ws.admin;

import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;

import ita.support.security.ISecurityFacade;
import ita.support.security.model.UserChangePasswordModel;
import ita.support.security.model.UserCheckEmailModel;
import ita.support.security.validator.ChangePasswordValidator;
import ita.support.ws.admin.UserChangePasswordCntr;

@RunWith(MockitoJUnitRunner.class)
public class UserChangePasswordCntrTest {

	@InjectMocks
	UserChangePasswordCntr userChangePasswordCntr = new UserChangePasswordCntr();

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(userChangePasswordCntr).build();
	}

	@Mock
	private ISecurityFacade securityFacade;

	@Mock
	private ChangePasswordValidator changePasswordValidator;

	@Test
	public void testUpdatePassword() throws Exception {
		UserChangePasswordModel userChangePassword = new UserChangePasswordModel("Diima", "123456", "123");

		when(securityFacade.updatePassword((UserChangePasswordModel) isNotNull())).thenReturn(true);
		when(changePasswordValidator.supports(UserChangePasswordModel.class)).thenReturn(true);

		mockMvc.perform(post("/admin/changePassword").contentType("application/json").content(new Gson().toJson(userChangePassword))).andExpect(status().isOk());

	}

	@Test
	public void testCheckEmail() throws Exception {
		UserCheckEmailModel userCheckEmailModel = new UserCheckEmailModel("Diima@mail.ru");

		when(securityFacade.checkEmail((UserCheckEmailModel) isNotNull())).thenReturn(true);

		mockMvc.perform(post("/admin/checkEmail").contentType("application/json").content(new Gson().toJson(userCheckEmailModel))).andExpect(status().isOk());

	}
}
