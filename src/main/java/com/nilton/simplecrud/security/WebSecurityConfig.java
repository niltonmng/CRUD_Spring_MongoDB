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



/**
 * Classe de configuração de segurança do projeto. Esta entidade restringe o acesso aos recursos
 * do sistema, permitindo que apenas usuários logados e autenticados com JWT possam acessar
 * o sistema e suas depdendencias.
 * 
 * @author Nilton Ginani
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/**
	 * Método responsável por fazer a configuração de segurança dos requests do projeto.
	 * Para que seja possível acessar o sistema, é necessário primeiramente fazer um acesso
	 * com a rota "/login" e com os dados passados.
	 */
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
		
		// filtra outras requisições para verificar a presença do JWT no header, só tem acesso depois que possuir o token de acesso no header.
		.addFilterBefore(new JWTAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
	}
	
	/**
	 * Este método é responsável por configurar os usuários default que podem acessar o sistema, e possuem acesso
	 * às suas dependencias.
	 */
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("nilton").password("{noop}daca2019").roles("USER")
			.and()
			.withUser("admin").password("{noop}daca2019").roles("ADMIN");
	}

}
