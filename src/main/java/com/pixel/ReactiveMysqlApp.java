package com.pixel;

import com.pixel.model.Country;
import com.pixel.repository.CountryRepository;
import com.pixel.repository.RoomRepository;
import com.pixel.service.CountryService;
import com.pixel.service.RoomService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@Log4j2
public class ReactiveMysqlApp {

	@Autowired
	private CountryService countryService;

	@Autowired
	private CountryRepository countryRepository;

	public static void main(String[] args) {
		SpringApplication.run(ReactiveMysqlApp.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			/*Mono<Void> countriesMono = countryService.getCountries()
					.flatMapMany(Flux::fromIterable) // Convert the Mono<List<Country>> to a Flux<Country>
					.flatMap(country -> this.countryRepository.findByNameAndCode(country.name(), country.code())
							.flatMap(existingCountry -> {
								// Create a new Country record with updated fields
								Country updatedCountry = new Country(
										existingCountry.id(),
										country.name(),
										country.emoji(),
										country.currency(),
										country.code(),
										country.capital()
								);
								// Save the updated record
								return this.countryRepository.save(updatedCountry);
							})
							.switchIfEmpty(this.countryRepository.save(country)) // Insert if no matching record is found
					)
					.then(); // Signal completion

			this.countryRepository
					.deleteAll()
					.thenMany(countriesMono)
					.thenMany(this.countryRepository.findAll())
					.subscribe(log::info);*/
			Mono<Void> countriesMono = countryService.getCountries()
					.flatMapMany(Flux::fromIterable) // Convert Mono<List<Country>> to Flux<Country>
					.flatMap(country -> this.countryRepository.findByNameAndCode(country.name(), country.code())
							.flatMap(existingCountry -> {
								// Create a new instance with updated fields
								Country updatedCountry = new Country(
										existingCountry.id(),  // Use existing id
										country.name(),
										country.emoji(),
										country.currency(),
										country.code(),
										country.capital()
								);
								// Save the updated country
								return this.countryRepository.save(updatedCountry);
							})
							.switchIfEmpty(this.countryRepository.save(country)) // Insert if no matching record is found
					)
					.then(); // Signal completion

// Ensure that no records are deleted before processing new ones
			countriesMono
					.thenMany(this.countryRepository.findAll()) // Retrieve all records
					.subscribe(log::info); // Log the results
			};
		}
		CommandLineRunner commandLineRunner(RoomService roomService) {
			return args -> {
				roomService.getAvailableRooms(1L, LocalDate.now())
						.subscribe(log::info);
			};
		}
}
