package com.hmsapp.Exception;

import com.hmsapp.PayLoad.ErrorDetailDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ExceptionHandel {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailDto> globalExceptionHandler(Exception e, WebRequest request){
        ErrorDetailDto errorDetailDto = new ErrorDetailDto(e.getMessage(),request.getDescription(false), new Date());
        return new ResponseEntity<>(errorDetailDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
