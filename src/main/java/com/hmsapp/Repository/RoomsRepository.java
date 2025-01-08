package com.hmsapp.Repository;

import com.hmsapp.Entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface RoomsRepository extends JpaRepository<Rooms, Long> {
  @Query("select r from Rooms r where r.roomType = :roomType AND r.property.id=:propertyId AND r.date BETWEEN :fromDate AND :toDate")
  List<Rooms> findRooms(
          @Param("fromDate") LocalDate fromDate,
          @Param("toDate") LocalDate toDate,
          @Param("propertyId") Long propertyId,
          @Param("roomType") String roomType);

}
