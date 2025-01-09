package com.hmsapp.Controller;

import com.hmsapp.Entity.Booking;
import com.hmsapp.Entity.Rooms;
import com.hmsapp.Service.BookingService;
import com.hmsapp.Service.PDFGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    @Autowired
    private PDFGeneration pdfGeneration ;
    @Autowired
    private BookingService bookService;
    @GetMapping
    public ResponseEntity<?> bookRoom(
            @RequestParam LocalDate fromDate,
            @RequestParam LocalDate toDate,
            @RequestParam Long propertyId,
            @RequestParam String roomType,
            @RequestBody Booking booking){

        Booking savedBooking = bookService.searchrommes(fromDate, toDate, roomType,propertyId, booking);
        if (savedBooking == null) {
            return new ResponseEntity<>("Something went wrong ,your booking failed ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        pdfGeneration.pdfGeneration("C:\\Users\\Varun B\\OneDrive\\Desktop\\PGM PDFs\\"+savedBooking.getGuestName()+"_Booking.pdf",savedBooking);
        return new ResponseEntity<>(savedBooking, HttpStatus.OK);
    }
}
