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
	public SecurityFilterChain filterChain(HttpSecurity http,HandlerMappingIntrospector introspector) throws Exception {
		MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
		return http
				.csrf(csrf -> csrf.disable())
				/*
				.authorizeHttpRequests(
						authRequest -> authRequest
						.requestMatchers(PathRequest.toH2Console()).permitAll()
						//.requestMatchers(HttpMethod.DELETE)
						//.hasRole("Administrador")
						.requestMatchers(new AntPathRequestMatcher("/users/**"))
						.hasAnyAuthority("Administrador")
						.requestMatchers(new AntPathRequestMatcher("/edificios/**"))
						.hasAnyAuthority("Administrador")
						.requestMatchers(new AntPathRequestMatcher("/reclamo/**"))
						.hasAnyAuthority("Administrador","Propietario","Inquilino","Empleado")
						.requestMatchers(new AntPathRequestMatcher("/auth/**"))
						.anonymous()
						.anyRequest().authenticated())*/
				.authorizeHttpRequests()
				.requestMatchers("/users/**")
				.hasAnyAuthority("Administrador")
				.anyRequest().authenticated()
				.and()
				.sessionManagement(
						//Deshabilita la politica de creacion de sesiones
						sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authProvider)
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();

	}

	/*
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer(HandlerMappingIntrospector introspector) {
		MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
		return (web) -> web.ignoring().requestMatchers(PathRequest.toH2Console());
	}
	*/

}