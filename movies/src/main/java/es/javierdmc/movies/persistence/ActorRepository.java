package es.javierdmc.movies.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import es.javierdmc.movies.domain.entity.Actor;

@Repository
public interface ActorRepository {

    public void create(Actor actor);

    public List<Actor> getAll(Optional<Integer> page);

    public Actor find(int id);

    public void update(Actor actor);

    public void delete(int id);

    public int getTotalNumberOfRecords();

    
}
