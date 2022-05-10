package com.projectX.exceptions;

import com.projectX.dto.ExceptionResponse;
import com.projectX.exceptions.user.UniqueUsernameException;
import com.projectX.exceptions.user.UserWrongCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserWrongCredentialsException.class, UniqueUsernameException.class})
    public ResponseEntity<ExceptionResponse> handleUserEntityException(Exception ex, WebRequest request) throws Exception {
        HttpStatus statusCode;
        String message;

        if (ex instanceof UserWrongCredentialsException) {
            message = ex.getMessage();
            statusCode = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof UniqueUsernameException) {
            message = ex.getMessage();
            statusCode = HttpStatus.CONFLICT;
        } else {
            throw ex;
        }

        return ResponseEntity.status(statusCode).body(new ExceptionResponse(message));
    }

}
