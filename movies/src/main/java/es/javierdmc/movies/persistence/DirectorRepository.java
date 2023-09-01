package es.javierdmc.movies.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import es.javierdmc.movies.domain.entity.Director;

@Repository
public interface DirectorRepository {

    public void create(Director director);

    public List<Director> getAll(Optional<Integer> page);

    public Director find(int id);

    public void update(Director director);

    public void delete(int id);

    public int getTotalNumberOfRecords();
    
}
