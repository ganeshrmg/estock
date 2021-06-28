package com.estock.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	 @Bean
	 SimpleCORSFilter simplecorsFilter() {
		 SimpleCORSFilter filter = new SimpleCORSFilter();
        return filter;
    } 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http	
		.cors()
		.and()
		//.csrf().disable()
		.httpBasic()
		.and()
		    .authorizeRequests()
				.antMatchers("/", "/home","/duediligence/getDuediligenceStatus/*","/getDuediligenceStatus/*").permitAll()
				.anyRequest().authenticated()
				.and()
			//.addFilterBefore(simplecorsFilter(), SessionManagementFilter.class)
			.formLogin()
				.loginPage("/loginpage")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("Ganesh")
				.password("pass")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	} 
}*/
