package com.pixel.repository;

import com.pixel.entity.Room;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface RoomRepository extends ReactiveCrudRepository<Room, Long> {
    @Query("SELECT * FROM rooms WHERE hotel_id = :hotelId AND available_from <= :date AND available_to >= :date")
    Flux<Room> findAvailableRooms(@Param("hotelId") Long hotelId, @Param("date") LocalDate date);

}
