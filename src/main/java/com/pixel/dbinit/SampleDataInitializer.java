package com.pixel.dbinit;

import com.pixel.entity.Reservation;
import com.pixel.repository.ReservationRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Log4j2
public class SampleDataInitializer {

    private final ReservationRepository reservationRepository;

    public SampleDataInitializer(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void go() {

        var names = Flux.just("foo", "bar", "baz")
                .map(name -> new Reservation(null, name))
                .flatMap(this.reservationRepository::save);
        this.reservationRepository
                .deleteAll()
                .thenMany(names)
                .thenMany(this.reservationRepository.findAll())
                .subscribe(log::info);
    }
}
