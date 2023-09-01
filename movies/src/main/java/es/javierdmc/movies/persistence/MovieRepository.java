package es.javierdmc.movies.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import es.javierdmc.movies.domain.entity.Movie;

@Repository
public interface MovieRepository {
    
    public void create(Movie movie);

    public List<Movie> getAll(Optional<Integer> page);

    public Movie find(int id);

    public void update(Movie movie);

    public void delete(int id);

    public int getTotalNumberOfRecords();

}
