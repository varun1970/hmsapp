package com.hmsapp.Service;

import com.hmsapp.Entity.Booking;
import com.hmsapp.Entity.Property;
import com.hmsapp.Entity.Rooms;
import com.hmsapp.Repository.BookingRepository;
import com.hmsapp.Repository.PropertyRepository;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PDFGeneration pdfGeneration;
    @Autowired
    private TwilioService twilioService;
   // @Autowired
    //private  MailgunService mailgunService;

    public Booking searchRooms(LocalDate fromDate, LocalDate toDate, String roomType, Long propertyId, Booking booking) throws UnirestException {
        Property property = propertyRepository.findById(propertyId).orElseThrow(()->new RuntimeException("Property with id " + propertyId + " not found"));
        Booking b1=booking;
        booking.setProperty(property);
        Booking savedBooking=  bookRepository.save(booking);

        pdfGeneration.pdfGeneration("C:\\Users\\Varun B\\OneDrive\\Desktop\\PGM PDFs\\"+savedBooking.getGuestName()+"_Booking.pdf",savedBooking);
        String body="Mr."+booking.getGuestName()+" your Booking in "+property.getName()+" is successfully completed thank you for your time";
        twilioService.sendSms( savedBooking.getMobile(),  body);
        twilioService.sendWhatsAppMessage(savedBooking.getMobile(), body);
//        mailgunService.sendEmail( "vb621622@gmail.com",  "vb05122001@gmail.com",  "Hotal Booking Done",  body);

        return savedBooking;
    }
}
