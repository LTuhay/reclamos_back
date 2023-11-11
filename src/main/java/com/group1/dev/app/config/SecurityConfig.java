package com.group1.dev.app.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
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
								.requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.DELETE, "/reclamo/**"))
								.hasAnyAuthority("Administrador")
						.requestMatchers(AntPathRequestMatcher.antMatcher("/reclamo/**"))
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


	@Bean
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
                new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}