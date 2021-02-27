package com.example.demo.service;

import com.example.demo.dao.MovieDao;
import com.example.demo.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private MovieDao movieDao;
    private MongoTemplate mongoTemplate;

    @Autowired
    public MovieService(MovieDao movieDao, MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.movieDao = movieDao;
    }

    public Optional<Movie> addMovie(Movie movie) { ;
        return Optional.ofNullable(movieDao.insert(movie));
    }

    public List<Movie> getAll() {
        return movieDao.findAll();
    }

    public Optional<Movie> getById(String id) {
        return movieDao.findById(id);
    }

    public Optional<Movie> getByTitle(String title) {
        return Optional.ofNullable(mongoTemplate.findOne(Query.query(Criteria.where("Title").is(title)), Movie.class));
    }

    public List<Movie> getByLanguage(String language) {
        return movieDao.getByLanguage(language);
    }

    public Optional<Movie> updateById(String id, Movie movie) {
        return movieDao.findById(id)
                .map(item -> {
                    item.setTitle(movie.getTitle());
                    return movieDao.save(item);
                });
    }

    public void deleteById(String id) {
        movieDao.deleteById(id);
    }
}
