package com.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/h2-console/**");
	}
	
	
	
	   @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf().disable()
            .authorizeHttpRequests()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic(); // Enable form-based login
	        
	        return http.build();
	    }
	   
	   @Bean
	      public InMemoryUserDetailsManager userDetailsService() {
	          UserDetails user = User.withDefaultPasswordEncoder()
	                  .username("aamir")
	                  .password("123")
	                  .roles("ADMIN")
	                  .build();
	          return new InMemoryUserDetailsManager(user);
	      }
	

}
