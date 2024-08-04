package com.pixel.service;

import com.pixel.entity.Room;
import com.pixel.repository.RoomRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Flux<Room> getAvailableRooms(Long hotelId, LocalDate date) {
        return roomRepository.findAvailableRooms(hotelId, date);
    }
}

