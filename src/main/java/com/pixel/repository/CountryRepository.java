package com.pixel.repository;

import com.pixel.model.Country;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface CountryRepository extends ReactiveCrudRepository<Country,Integer> {

    Optional<Country> findByCode(String code);
    Mono<Country> findByNameAndCode(String name, String code);
}
