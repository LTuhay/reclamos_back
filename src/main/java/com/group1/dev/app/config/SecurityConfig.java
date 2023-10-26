package com.group1.dev.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.group1.dev.app.Jwt.JwtAuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private AuthenticationProvider authProvider;
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		new AntPathRequestMatcher("/edificios/**");
		return http
				.csrf(csrf -> csrf.disable())
				.headers(headers -> headers.disable())//BORRAR UNA VEZ QUE SE TERMINE DE UTILIZAR H2
				.authorizeHttpRequests(
						authRequest -> authRequest
						.requestMatchers(PathRequest.toH2Console()).anonymous()
						.requestMatchers(AntPathRequestMatcher.antMatcher("/users/**"))
						.hasAnyAuthority("Administrador")
						.requestMatchers(AntPathRequestMatcher.antMatcher("/edificios/**"))
						.hasAnyAuthority("Administrador")
						.requestMatchers(AntPathRequestMatcher.antMatcher("/Unidades/**"))
						.hasAnyAuthority("Administrador")
						.requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.DELETE,"/reclamo/**"))
						.hasAnyAuthority("Administrador")
						.requestMatchers(AntPathRequestMatcher.antMatcher("/auth/register"))
						.hasAnyAuthority("Administrador")
						.requestMatchers(AntPathRequestMatcher.antMatcher("/auth/login"))
						.anonymous()
						.anyRequest().authenticated())
				.sessionManagement(
						//Deshabilita la politica de creacion de sesiones
						sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authProvider)
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();

	}

}