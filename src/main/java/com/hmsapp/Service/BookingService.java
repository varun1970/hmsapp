package com.hmsapp.Service;

import com.hmsapp.Entity.Booking;
import com.hmsapp.Entity.Property;
import com.hmsapp.Entity.Rooms;
import com.hmsapp.Repository.BookingRepository;
import com.hmsapp.Repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookRepository;
    @Autowired
    private PropertyRepository propertyRepository;

    public Booking searchrommes(LocalDate fromDate, LocalDate toDate, String roomType, Long propertyId, Booking booking) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(()->new RuntimeException("Property with id " + propertyId + " not found"));
        booking.setProperty(property);
        return  bookRepository.save(booking);
    }
}
