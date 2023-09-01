package es.javierdmc.movies.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.javierdmc.movies.domain.entity.Movie;
import es.javierdmc.movies.domain.service.MovieService;
import es.javierdmc.movies.persistence.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void create(Movie movie) {
        movieRepository.create(movie);
    }

    @Override
    public List<Movie> getAll(Optional<Integer> page) {
        return movieRepository.getAll(page);
    }

    @Override
    public Movie find(int id) {
        return movieRepository.find(id);
    }

    @Override
    public void update(Movie movie) {
       movieRepository.update(movie);
    }

    @Override
    public void delete(int id) {
        movieRepository.delete(id);
    }

    @Override
    public int getTotalNumberOfRecords(){
        return movieRepository.getTotalNumberOfRecords();
    }
 
}
