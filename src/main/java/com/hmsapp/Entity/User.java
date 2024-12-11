package com.hmsapp.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email_id", nullable = false)
    private String emailId;

    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;

    @Column(name = "role", nullable = false)
    private String role;

}