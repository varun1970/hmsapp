package com.hmsapp.PayLoad;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProfileDto {
    private Long id;
    private String userName;
    private String emailId;
    private String mobileNumber;


}
