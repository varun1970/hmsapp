package com.hmsapp;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class A {
    public static void main(String[] args) {
       String c =  BCrypt.hashpw("testing",BCrypt.gensalt(10));
        System.out.println(c);
    }
}
