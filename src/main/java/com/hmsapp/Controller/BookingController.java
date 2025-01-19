package com.hmsapp.Controller;

import com.hmsapp.Entity.Booking;
import com.hmsapp.Entity.Rooms;
import com.hmsapp.Service.BookingService;
import com.hmsapp.Service.PDFGeneration;
import com.hmsapp.Service.TwilioService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/booking")
@CrossOrigin("http://localhost:4200/")
public class BookingController {

    @Autowired
    private TwilioService twilioService;
    @Autowired
    private PDFGeneration pdfGeneration ;
    @Autowired
    private BookingService bookService;
    @PostMapping
    public ResponseEntity<?> bookRoom(
            @RequestParam LocalDate fromDate,
            @RequestParam LocalDate toDate,
            @RequestParam Long propertyId,
            @RequestParam String roomType,
            @RequestBody Booking booking) throws UnirestException {

        Booking savedBooking = bookService.searchRooms(fromDate, toDate, roomType,propertyId, booking);
        if (savedBooking == null) {
            return new ResponseEntity<>("Something went wrong ,your booking failed ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(savedBooking, HttpStatus.OK);
    }
}
