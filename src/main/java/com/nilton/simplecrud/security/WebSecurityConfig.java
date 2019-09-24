package com.nilton.simplecrud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//			.anyRequest().authenticated()
//			.and()
//			.httpBasic()
//			.and()
//			.csrf().disable();
		
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/login").permitAll()
		.anyRequest().authenticated()
		.and()
		
		// filtra requisições de login
		.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)
		
		// filtra outras requisições para verificar a presença do JWT no header
		.addFilterBefore(new JWTAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
	}
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("nilton").password("{noop}daca2019").roles("USER")
			.and()
			.withUser("admin").password("{noop}$2a$10$jhj1v.fybvNFg0zEsIpbp.XyL.Hi464ziqhM764HVZRelQxJK4.PG").roles("ADMIN");
	}

}
