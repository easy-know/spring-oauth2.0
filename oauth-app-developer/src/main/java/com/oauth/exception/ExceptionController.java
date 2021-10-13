package com.oauth.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity existDeveloperError(Throwable throwable) {
        log.warn("InternalServerError - message : {}", throwable.getMessage());
//        return ResponseEntity.ok().body(new ErrorResponse().makeThrowableErrorResponse(throwable));
        return new ResponseEntity(new ErrorResponse().makeThrowableErrorResponse(throwable), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> invalidStateError(IllegalStateException e, HttpServletRequest request) {
        log.warn("MethodArgumentNotValidException - url:{}, trace:{}", request.getRequestURI(), e.getStackTrace());
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.INVALID_INPUT.getCode(), ErrorCode.INVALID_INPUT.getDescription(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.warn("MethodArgumentNotValidException - url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());
        return new ResponseEntity<>(new ErrorResponse().makeBindingErrorResponse(e.getBindingResult()), HttpStatus.BAD_REQUEST);
    }
}
