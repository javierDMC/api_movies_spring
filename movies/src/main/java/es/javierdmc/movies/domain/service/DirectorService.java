package es.javierdmc.movies.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.javierdmc.movies.domain.entity.Director;

@Service
public interface DirectorService {

    public void create(Director director);

    public List<Director> getAll(Optional<Integer> page);

    public Director find(int id);

    public void update(Director director);

    public void delete(int id);

    public int getTotalNumberOfRecords();
    
}
