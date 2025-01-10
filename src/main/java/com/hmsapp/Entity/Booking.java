package com.hmsapp.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "guest_name")
    private String guestName;

    @Column(name = "no_of_guest")
    private int noOfGuest;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

}