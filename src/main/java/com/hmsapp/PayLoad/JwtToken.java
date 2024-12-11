package com.hmsapp.PayLoad;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class JwtToken {
    private String token;
    private String type;

}
