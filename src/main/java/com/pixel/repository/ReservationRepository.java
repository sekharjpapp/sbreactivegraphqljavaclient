package com.pixel.repository;

import com.pixel.entity.Reservation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends ReactiveCrudRepository<Reservation,Integer> {
}
