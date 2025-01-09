package com.hmsapp.Controller;

import com.hmsapp.Entity.Rooms;
import com.hmsapp.Service.RoomsService;
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
public class RoomsController {
    @Autowired
    private RoomsService roomsService;

    @GetMapping("/search")
    public ResponseEntity<?> searchRoomAvailability(
            @RequestParam LocalDate fromDate,
            @RequestParam LocalDate toDate,
            @RequestParam Long propertyId,
            @RequestParam String roomType){

        List<Rooms> rooms = roomsService.searchrommes(fromDate, toDate, roomType,propertyId);
        if (rooms.isEmpty()) {
            return new ResponseEntity<>("No rooms available for the given criteria", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(rooms.stream().filter(x -> x.getTotalRoom()!=0).toList(), HttpStatus.OK);
    }


}
