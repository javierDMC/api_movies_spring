package es.javierdmc.movies.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.javierdmc.movies.domain.entity.Movie;
import es.javierdmc.movies.domain.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("")
    public void create(@RequestBody Movie movie){
        movieService.create(movie);
    }

    /*@ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<Movie> getAll(){
        return movieService.getAll();
    }*/

    @GetMapping("/{id}")
    public Movie find(@PathVariable("id") int id){
        try {
            return movieService.find(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Movie movie){
        movieService.update(movie);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id){
        movieService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Map<String, Object> getAllMap(@RequestParam Optional<Integer> page) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", movieService.getAll(page));
        int totalRecords = movieService.getTotalNumberOfRecords();
        response.put("total records", totalRecords);
        return response;
    }

    
    
}
