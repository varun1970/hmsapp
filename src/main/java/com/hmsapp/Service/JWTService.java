package com.hmsapp.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.algorithm.key)")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private static String issuer;

    @Value("${jwt.expiry.duration}")
    private int expiry;

    private static Algorithm algorithm;



    @PostConstruct
    public void postConstruct() throws UnsupportedEncodingException {
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public String  generateTokens(String userName){
        return JWT.create()
                .withClaim("name",userName)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiry))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public static String getUsername(String token) {
        DecodedJWT decodedJWT=JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token);
        return decodedJWT.getClaim("name").asString();


    }


}
