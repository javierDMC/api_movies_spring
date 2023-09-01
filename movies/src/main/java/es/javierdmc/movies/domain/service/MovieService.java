package es.javierdmc.movies.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.javierdmc.movies.domain.entity.Movie;

@Service
public interface MovieService {
    
    public void create(Movie movie);

    public List<Movie> getAll(Optional<Integer> page);

    public Movie find(int id);

    public void update(Movie movie);

    public void delete(int id);

    public int getTotalNumberOfRecords();

}
