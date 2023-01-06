package com.blogapplication.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blogapplication.paylaod.ApiResponse;

@RestControllerAdvice
public class GlobleExceptionsHandler {

	// Custome throw exceptions handler !!

	@ExceptionHandler(ResourceNotFoundExceptions.class)
	public ResponseEntity<ApiResponse> exceptionsHandler(ResourceNotFoundExceptions ex) {

		String message = ex.getMessage();

		ApiResponse apiResponse = new ApiResponse(message, false);

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

	}

	// this is for all exceptions handler !!

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ApiResponse> createNewException() {

		ApiResponse apiResponse = new ApiResponse("list is empty ! user not founds  !  !", false);

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentsNotValidExceptions(MethodArgumentNotValidException ex) {

		Map<String, String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {

			String fieldName = ((FieldError) error).getField();

			String message = error.getDefaultMessage();

			resp.put(fieldName, message);

		});

		return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);

	}

}
