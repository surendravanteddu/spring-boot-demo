package com.example.demo.dao;

import com.example.demo.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("MovieRepository")
public interface MovieDao extends MongoRepository<Movie, String> {

    @Query("{language: ?0}")
    List<Movie> getByLanguage(String language);
}
