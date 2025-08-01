package com.friendbook.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManagr(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
		   .csrf().disable()
		   .authorizeHttpRequests(auth -> auth
				   .anyRequest().permitAll())
		   .formLogin(form -> form
				   .loginPage("/login")
				   .loginProcessingUrl("/do-login")
				   .defaultSuccessUrl("/profile",true)
				   .permitAll())
		   .logout(logout -> logout
				   .logoutUrl("/logout")
				   .logoutSuccessUrl("/")
				   .permitAll());
		return http.build();
	}
}
