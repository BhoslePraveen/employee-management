package com.codemind.whirlpool.employee_management.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("!junit") 
public class SecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		 http
	        .authorizeRequests(authorizeRequests -> 
	            authorizeRequests
	                .antMatchers("/emp/all").permitAll()  // Allows access to /all without authentication
	                .anyRequest().authenticated()    // Requires authentication for any other request
	        )
	        .formLogin()
	        .and()// Enables form-based login
	        .httpBasic();  // Enables HTTP Basic authentication

	    return http.build();
	}

	@Bean
	InMemoryUserDetailsManager userDetailsService() {

		UserDetails adminUser = User.withUsername("admin").password("1234").authorities("ADMIN").build();

		UserDetails generalUser = User.withUsername("user").password("12345").authorities("USER").build();

		return new InMemoryUserDetailsManager(adminUser, generalUser);

	}

	@Bean
	PasswordEncoder getPassWordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
