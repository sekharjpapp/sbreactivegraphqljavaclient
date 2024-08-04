package com.pixel.controller;

import com.pixel.service.RoomService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Controller
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // http://localhost:8080/rooms/availability?hotelId=1&date=2024-08-04

    // curl -X GET "http://localhost:8080/rooms/availability?hotelId=1&date=2024-08-04"

    @GetMapping("/availability")
    public Mono<String> getAvailableRooms(@RequestParam("hotelId") Long hotelId,
                                          @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                          Model model) {
        return roomService.getAvailableRooms(hotelId, date)
                .collectList()
                .doOnNext(rooms -> {
                    model.addAttribute("rooms", rooms);
                    model.addAttribute("date", date); // Add date to the model
                })
                .thenReturn("roomAvailability"); // Thymeleaf template name
    }
}
