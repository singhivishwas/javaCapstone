package com.example.moviecatalogue.controller;

import com.example.moviecatalogue.model.TmdbMovie;
import com.example.moviecatalogue.service.TmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.moviecatalogue.model.FavoriteMovie;
import com.example.moviecatalogue.repository.FavoriteRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieController {

    private final TmdbService tmdbService;

    @Autowired
    public MovieController(TmdbService tmdbService) {
        this.tmdbService = tmdbService;
    }

    @Autowired
    private FavoriteRepository favoriteRepository;

    @GetMapping("/")
    public String showPopularMovies(Model model) {
        List<TmdbMovie> movies = tmdbService.getPopularMovies().getResults();
        model.addAttribute("movies", movies);
        return "index";
    }

    @GetMapping("/movie/{id}")
    public String showMovieDetails(@PathVariable int id, Model model) {
        TmdbMovie movie = tmdbService.getMovieById(id);
        model.addAttribute("movie", movie);
        return "details";
    }

    @GetMapping("/favorites")
    public String showFavorites(Model model) {
        model.addAttribute("favorites", favoriteRepository.findAll());
        return "favorites";
    }



    @GetMapping("/search")
    public String searchMovies(@RequestParam String query, Model model) {
        List<TmdbMovie> results = tmdbService.searchMovies(query).getResults();
        model.addAttribute("movies", results);
        model.addAttribute("searchQuery", query);
        return "index";
    }


    @PostMapping("/favorites/add/{id}")
    public String addToFavorites(@PathVariable int id) {
        TmdbMovie tmdbMovie = tmdbService.getMovieById(id);

        FavoriteMovie fav = new FavoriteMovie();
        fav.setId(tmdbMovie.getId());
        fav.setTitle(tmdbMovie.getTitle());
        fav.setPosterPath(tmdbMovie.getPosterPath());
        fav.setReleaseDate(tmdbMovie.getReleaseDate());
        fav.setRating(tmdbMovie.getRating());

        favoriteRepository.save(fav);
        return "redirect:/favorites";
    }

    @PostMapping("/favorites/remove/{id}")
    public String removeFromFavorites(@PathVariable int id) {
        favoriteRepository.deleteById(id);
        return "redirect:/favorites";
    }

}
