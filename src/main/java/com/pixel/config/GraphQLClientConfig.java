package com.pixel.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GraphQLClientConfig {
    @Value("${graphql.client.url}")
    private String graphqlUrl;
    @Bean
    @Qualifier("graphQlWebClient")
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(graphqlUrl)
                .build();
    }
    @Bean
    @Qualifier("jsonPlaceholderWebClient")
    public WebClient jsonPlaceholderWebClient(WebClient.Builder builder) {
        return builder.baseUrl("https://jsonplaceholder.typicode.com/")
                .build();
    }
    @Bean
    public HttpGraphQlClient graphQlClient(@Qualifier("graphQlWebClient") WebClient graphQlWebClient) {
        return HttpGraphQlClient.builder(graphQlWebClient).build();
    }
}
