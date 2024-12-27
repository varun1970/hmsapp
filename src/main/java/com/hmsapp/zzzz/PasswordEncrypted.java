package com.hmsapp.zzzz;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDate;
import java.time.LocalTime;

public class PasswordEncrypted {
    public static void main(String[] args) {
       String c =  BCrypt.hashpw("testing",BCrypt.gensalt(10));
        System.out.println(c);
        LocalDate d=LocalDate.now();
        System.out.println(d.plusYears(1).isBefore(d.plusYears(2)));
        LocalTime t=LocalTime.now();
        System.out.println(t.plusHours(1));
    }
}
