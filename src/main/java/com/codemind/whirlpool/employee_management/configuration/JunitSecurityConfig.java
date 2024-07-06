package com.codemind.whirlpool.employee_management.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("junit")
public class JunitSecurityConfig {
	@Bean
	SecurityFilterChain junitSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().permitAll() // Allows access to all
																								// endpoints without
																								// authentication
		).csrf().disable(); // Disable CSRF for testing purposes

		return http.build();
	}
}
