package com.pixel.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Table("rooms")
public class Room {

    @Id
    private Long id;

    private Long hotelId;
    private String roomNumber;
    private String roomType;
    private String description;
    private BigDecimal price;
    private Integer capacity;
    private LocalDate availableFrom;
    private LocalDate availableTo;
    private String amenities;
    private Integer floor;
    private String bedType;
}
