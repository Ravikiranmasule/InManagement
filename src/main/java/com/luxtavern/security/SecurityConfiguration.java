package com.luxtavern.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	CustomUserDetailsService customUserDetailService;
	@Autowired
	JWTAuthEntryPoint jwtAuthEntryPoint;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		http 
		    .csrf().disable()
		    .exceptionHandling()
		    .authenticationEntryPoint(jwtAuthEntryPoint)
		    .and()
		    .sessionManagement()
		    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		    .and()
		    .authorizeRequests()
		    .antMatchers("/").permitAll()
		    .antMatchers("/api/auth/**").permitAll()
		    .antMatchers("/api/admin/**").hasRole("ADMIN")
		    .antMatchers("/api/email/**").permitAll()
		    .antMatchers("/api/photo/**").permitAll()
		    .antMatchers("/api/pdf/**").permitAll()
		    .antMatchers("/api/category/**").permitAll()
		    .anyRequest().authenticated()
		    .and()
		    .httpBasic();
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
		
	}	
	
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
		
	}
	
	@Bean
public JWTAuthenticationFilter jwtAuthenticationFilter() {
	return new JWTAuthenticationFilter();
}
}
