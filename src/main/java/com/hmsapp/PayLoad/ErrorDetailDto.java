package com.hmsapp.PayLoad;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;

@Getter
@Setter
public class ErrorDetailDto {

    public String errorMessage;
    public String request;
    public Date date;
    public ErrorDetailDto(String errorMessage, String request, Date date) {
        this.errorMessage = errorMessage;
        this.request = request;
        this.date = date;
    }

}
