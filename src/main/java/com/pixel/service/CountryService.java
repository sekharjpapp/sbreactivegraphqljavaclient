package com.pixel.service;

import com.pixel.model.Country;
import com.pixel.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;
    private final HttpGraphQlClient graphQlClient;
    private final WebClient webClient;

    public CountryService(HttpGraphQlClient graphQlClient, WebClient webClient) {
        this.graphQlClient = graphQlClient;
        this.webClient = webClient;
    }

    @Transactional
    public Mono<List<Country>> getCountries() {
        String document = """
                query {
                    countries {
                        name
                        emoji
                        currency
                        code
                        capital
                    }
                }
                """;
        return graphQlClient.document(document)
                .retrieve("countries")
                .toEntityList(Country.class);
    }

}
