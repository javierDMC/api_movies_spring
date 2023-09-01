package es.javierdmc.movies.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.javierdmc.movies.domain.entity.Director;
import es.javierdmc.movies.domain.service.DirectorService;
import es.javierdmc.movies.persistence.DirectorRepository;

@Service
public class DirectorServiceImpl implements DirectorService{

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public void create(Director director) {
        directorRepository.create(director);
    }

    @Override
    public List<Director> getAll(Optional<Integer> page) {
        return directorRepository.getAll(page);
    }

    @Override
    public Director find(int id) {
        return directorRepository.find(id);
    }

    @Override
    public void update(Director director) {
        directorRepository.update(director);
    }

    @Override
    public void delete(int id) {
        directorRepository.delete(id);
    }

    @Override
    public int getTotalNumberOfRecords() {
        return directorRepository.getTotalNumberOfRecords();
    }    
}
