package com.hmsapp.Service;

import com.hmsapp.Entity.Rooms;
import com.hmsapp.Repository.RoomsRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class RoomsService {
    public RoomsService(RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }

    private RoomsRepository roomsRepository;

    public List<Rooms> searchrommes(LocalDate fromDate, LocalDate toDate, String roomType, Long propertyId) {
        return roomsRepository.findRooms(fromDate, toDate, propertyId, roomType);
    }
}
