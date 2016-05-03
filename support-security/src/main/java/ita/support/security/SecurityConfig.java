package ita.support.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = { "ita.support" })
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	AuthenticationProvider authProvider;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().formLogin().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll();

		//
		http.authorizeRequests().antMatchers("/company/**").hasRole("ADMIN_BUSINESS").and().httpBasic();
		//
		http.authorizeRequests().antMatchers("/clients/hello").hasRole("ADMIN").antMatchers("/clients/set")
				.hasRole("USER").antMatchers("/clients/getAllClients").authenticated().antMatchers("/admin/**")
				.hasRole("ADMIN")

				.antMatchers("/employee/**").hasAnyRole("MASTER", "EMPLOYEE").anyRequest().anonymous().and()
				.httpBasic();

	}

}
