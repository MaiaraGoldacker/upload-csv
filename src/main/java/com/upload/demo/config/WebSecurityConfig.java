package com.upload.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.upload.demo.security.CustomBasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity 
public class WebSecurityConfig {
	
	@Autowired
	private CustomBasicAuthenticationFilter customBasicAuthenticationFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)throws Exception {
		
		http.csrf(csrf -> csrf.disable())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests((request) -> request
					.requestMatchers(
							"/v3/api-docs/**",    
							"/swagger-ui/**",    
							"/swagger-ui.html",  
							"/actuator/**",
							"/user/**"
							).permitAll()
					.anyRequest()
					.authenticated()).addFilterBefore(customBasicAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);				
		
		return http.build();
	}

}
