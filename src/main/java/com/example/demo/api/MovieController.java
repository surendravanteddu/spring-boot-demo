package com.example.demo.api;

import com.example.demo.model.Movie;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/movie")
@RestController
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie).orElse(null);
    }

    @GetMapping
    public List<Movie> getAll() {
        return movieService.getAll();
    }

    @GetMapping(path = "id/{id}")
    public Movie getById(@PathVariable("id") String id) {
        return movieService.getById(id).orElse(null);
    }

    @GetMapping(path = "title/{title}")
    public Movie getByTitle(@PathVariable("title") String title) {
        return movieService.getByTitle(title).orElse(null);
    }

    @GetMapping(path = "language/{language}")
    public List<Movie> getByLanguage(@PathVariable("language") String language) {
        return movieService.getByLanguage(language);
    }

    @DeleteMapping(path = "id/{id}")
    public void deleteById(@PathVariable("id") String id) {
        movieService.deleteById(id);
    }

    @PutMapping(path = "id/{id}")
    public Movie updateById(@PathVariable("id") String id,
                              @RequestBody Movie movie) {
        return movieService.updateById(id, movie).orElse(null);
    }
}
