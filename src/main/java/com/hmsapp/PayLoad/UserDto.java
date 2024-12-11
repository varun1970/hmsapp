package com.hmsapp.PayLoad;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
public class UserDto {


    private Long id;

    private String userName;

    private String emailId;

    private String mobileNumber;

    private String password;

    private String role;


}
