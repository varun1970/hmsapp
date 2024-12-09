package com.hmsapp.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JWTService {

    @Value("@{jwt.algorithm.key)")
    private String algorithmKey;

    @Value("${jwr.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private int expiry;

    private Algorithm algorithm;
    @PostConstruct
    public void postConstruct() throws UnsupportedEncodingException {
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public String  generateTokens(String userName){
        return JWT.create()
                .withClaim("name",userName)
                .withExpiresAt(new Date(System.currentTimeMillis()))
                .withIssuer(issuer)
                .sign(algorithm);
    }


}
