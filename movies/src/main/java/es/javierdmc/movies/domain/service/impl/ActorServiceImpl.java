package es.javierdmc.movies.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.javierdmc.movies.domain.entity.Actor;
import es.javierdmc.movies.domain.service.ActorService;
import es.javierdmc.movies.persistence.ActorRepository;

@Service
public class ActorServiceImpl implements ActorService{

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public void create(Actor actor) {
        actorRepository.create(actor);
    }

    @Override
    public List<Actor> getAll(Optional<Integer> page) {
        return actorRepository.getAll(page);
    }

    @Override
    public Actor find(int id) {
        return actorRepository.find(id);
    }

    @Override
    public void update(Actor actor) {
        actorRepository.update(actor);    
    }

    @Override
    public void delete(int id) {
        actorRepository.delete(id);   
    }

    @Override
    public int getTotalNumberOfRecords() {
        return actorRepository.getTotalNumberOfRecords();
    }

    
    
}
