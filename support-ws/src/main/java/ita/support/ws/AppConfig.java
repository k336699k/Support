package ita.support.ws;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * class for setting the context mvc spring, instead of xml
 * 
 * @author cat
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "ita.support" })
public class AppConfig extends WebMvcConfigurerAdapter {

	// TODO: its temporary setting for development!!!Need for refactor when
	// start prod!!!
	// Configure cross origin requests processing.
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("POST", "GET", "PUT", "DELETE", "OPTIONS")
						.allowedOrigins("*");

		registry.addMapping("/**")
						.allowedMethods("POST", "GET", "PUT", "DELETE")
						.allowCredentials(true);

	}
}
