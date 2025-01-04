package com.hmsapp.Service;

import com.hmsapp.Entity.RoomsAvailability;
import com.hmsapp.Repository.RoomsAvailabilityRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class RoomsAvailabilityService {
    public RoomsAvailabilityService(RoomsAvailabilityRepository roomsAvailabilityRepository) {
        this.roomsAvailabilityRepository = roomsAvailabilityRepository;
    }

    private RoomsAvailabilityRepository roomsAvailabilityRepository;

    public List<RoomsAvailability> searchrommes(LocalDate fromDate, LocalDate toDate, String roomType,Long propertyId) {
        return roomsAvailabilityRepository.findRooms(fromDate, toDate, propertyId, roomType);
    }
}
