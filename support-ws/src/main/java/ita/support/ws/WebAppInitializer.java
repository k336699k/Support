package ita.support.ws;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	//
	// @Override
	// public void onStartup(ServletContext servletContext) throws
	// ServletException {
	// AnnotationConfigWebApplicationContext ctx = new
	// AnnotationConfigWebApplicationContext();
	// ctx.register(AppConfig.class);
	// ctx.setServletContext(servletContext);
	// Dynamic dynamic = servletContext.addServlet("dispatcher", new
	// DispatcherServlet(ctx));
	// dynamic.addMapping("/");
	// dynamic.setLoadOnStartup(1);
	//
	// }

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class }; // We dont need any special
												// servlet config yet.
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
