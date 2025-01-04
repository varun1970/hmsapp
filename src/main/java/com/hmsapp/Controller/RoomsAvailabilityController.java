package com.hmsapp.Controller;

import com.hmsapp.Entity.RoomsAvailability;
import com.hmsapp.Service.RoomsAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomsAvailabilityController {
    @Autowired
    private RoomsAvailabilityService roomsAvailabilityService;

    @GetMapping("/search")
    public ResponseEntity<?> searchRoomAvailability(
            @RequestParam LocalDate fromDate,
            @RequestParam LocalDate toDate,
            @RequestParam Long propertyId,
            @RequestParam String roomType){

        List<RoomsAvailability> rooms = roomsAvailabilityService.searchrommes(fromDate, toDate, roomType,propertyId);
        List<RoomsAvailability> totalrooms;
        if (rooms.isEmpty()) {
            return new ResponseEntity<>("No rooms available for the given criteria", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(rooms.stream().filter(x -> x.getTotalRoom()!=0).toList(), HttpStatus.OK);
    }


}
