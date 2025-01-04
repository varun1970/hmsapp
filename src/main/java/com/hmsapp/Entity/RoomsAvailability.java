package com.hmsapp.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "rooms_availability")
public class RoomsAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "total_room")
    private Long totalRoom;

    @Column(name = "nightly_price")
    private Long nightlyPrice;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Long getTotalRoom() {
        return totalRoom;
    }

    public void setTotalRoom(Long totalRoom) {
        this.totalRoom = totalRoom;
    }

    public Long getNightlyPrice() {
        return nightlyPrice;
    }

    public void setNightlyPrice(Long nightlyPrice) {
        this.nightlyPrice = nightlyPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}