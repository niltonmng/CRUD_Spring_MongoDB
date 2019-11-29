// package com.nilton.simplecrud.security;

// import java.io.IOException;
// import java.util.Collections;

// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// import com.fasterxml.jackson.databind.ObjectMapper;

// /**
//  * Esta entidade tem como objetivo interceptar as requisições do tipo POST feitas com a rota /"login"
//  * e tentar autenticar um usuário. Se a autenticação for sucedida, ocorre um retorno de um JWT com a
//  * autorização no cabeçalho da resposta.
//  * @author Nilton Ginani/
//  *
//  */
// public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

// 	protected JWTLoginFilter(String url, AuthenticationManager authManager) {
// 		super(new AntPathRequestMatcher(url));
// 		setAuthenticationManager(authManager);
// 	}

// 	/**
// 	 * Este método é quem lida com a tentativa de autenticação, onde se pega o username e password da
// 	 * da requisição e se utiliza o AuthenticationManager para verificar se os dados são correspondentes
// 	 * aos dados do usuário existente.
// 	 */
// 	@Override
// 	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
// 			throws AuthenticationException, IOException, ServletException {
		
// 		AccountCredentials credentials = new ObjectMapper()
// 				.readValue(request.getInputStream(), AccountCredentials.class);
		
// 		return getAuthenticationManager().authenticate(
// 				new UsernamePasswordAuthenticationToken(
// 						credentials.getUsername(), 
// 						credentials.getPassword(), 
// 						Collections.emptyList()
// 						)
// 				);
// 	}
	
// 	/**
// 	 * Este método é responsável por enviar ao service TokenAuthenticationService o username
// 	 * do usuário para que este adicione um JWT à nossa reponse. Apenas funciona caso os dados
// 	 * estejam corretos.
// 	 */
// 	@Override
// 	protected void successfulAuthentication(
// 			HttpServletRequest request, 
// 			HttpServletResponse response,
// 			FilterChain filterChain,
// 			Authentication auth) throws IOException, ServletException {
		
// 		TokenAuthenticationService.addAuthentication(response, auth.getName());
// 	}


// }
