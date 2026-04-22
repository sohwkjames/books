package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/h2-console/**").permitAll()  // 👈 whitelist h2
	            .requestMatchers("/admin/**").hasRole("ADMIN")
	            .anyRequest().authenticated()
	        )
	        .formLogin(Customizer.withDefaults())
	        .csrf(csrf -> csrf
	            .ignoringRequestMatchers("/h2-console/**")      // 👈 disable CSRF for h2
	        )
	        .headers(headers -> headers
	            .frameOptions(frame -> frame.sameOrigin())      // 👈 allow iframes
	        );

	    return http.build();
	}
}
