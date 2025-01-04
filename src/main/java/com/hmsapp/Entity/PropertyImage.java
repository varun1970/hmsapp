package com.hmsapp.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "property_image")
public class PropertyImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "url", length = 1000)
    private String url;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

}