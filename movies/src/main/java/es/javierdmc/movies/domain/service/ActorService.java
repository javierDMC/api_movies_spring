package es.javierdmc.movies.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.javierdmc.movies.domain.entity.Actor;

@Service
public interface ActorService {
    
    public void create(Actor actor);

    public List<Actor> getAll(Optional<Integer> page);

    public Actor find(int id);

    public void update(Actor actor);

    public void delete(int id);

    public int getTotalNumberOfRecords();

}
