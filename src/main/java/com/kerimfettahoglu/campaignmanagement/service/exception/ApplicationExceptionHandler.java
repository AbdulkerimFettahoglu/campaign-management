package com.kerimfettahoglu.campaignmanagement.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
public class ApplicationExceptionHandler {
	
	@ResponseBody
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String businessExceptionHandler(BusinessException ex) {
		log.error(ex.getMessage(), ex);
		return ex.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String exceptionHandler(Exception ex) {
		log.error("Something bad happened.", ex);
		return "Something bad happened. " + ex.getMessage();
	}
}
