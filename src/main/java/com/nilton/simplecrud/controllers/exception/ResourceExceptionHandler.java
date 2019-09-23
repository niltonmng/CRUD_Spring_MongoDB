package com.nilton.simplecrud.controllers.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nilton.simplecrud.domain.services.exception.ObjectNotFoundException;

@ControllerAdvice // essa anotacao indica que a class é responsavel por tratar possiveis erros nas requisicoes
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class) // a anotacao identifica que quando ocorrer essa excecao, ele deve fazer o tratamento abaixo
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		long timestamp = System.currentTimeMillis();
		Integer status = HttpStatus.NOT_FOUND.value();
		String error = "Not Found. NGMESSAGE";
		String message = e.getMessage();
		String path = request.getRequestURI();

		StandardError err = new StandardError(timestamp, status, error, message, path);

		return ResponseEntity.status(status).body(err);
	}

}
