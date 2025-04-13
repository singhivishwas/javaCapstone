package com.example.moviecatalogue.service;

import com.example.moviecatalogue.model.TmdbResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.moviecatalogue.model.TmdbMovie;

@Service
public class TmdbService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public TmdbResponse getPopularMovies() {
        String uri = UriComponentsBuilder.fromHttpUrl(baseUrl + "/movie/popular")
                .queryParam("api_key", apiKey)
                .toUriString();

        return restTemplate.getForObject(uri, TmdbResponse.class);
    }

    public TmdbMovie getMovieById(int movieId) {
        String uri = UriComponentsBuilder.fromHttpUrl(baseUrl + "/movie/" + movieId)
                .queryParam("api_key", apiKey)
                .toUriString();

        return restTemplate.getForObject(uri, TmdbMovie.class);
    }

    public TmdbResponse searchMovies(String query) {
        String uri = UriComponentsBuilder.fromHttpUrl(baseUrl + "/search/movie")
                .queryParam("api_key", apiKey)
                .queryParam("query", query)
                .toUriString();

        return restTemplate.getForObject(uri, TmdbResponse.class);
    }

}
