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

import es.javierdmc.movies.domain.entity.Director;
import es.javierdmc.movies.domain.service.DirectorService;

@RestController
@RequestMapping("/directors")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @PostMapping("")
    public void create(@RequestBody Director director){
        directorService.create(director);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Map<String, Object> getAllMap(@RequestParam Optional<Integer> page){
        Map<String, Object> response = new HashMap<>();
        response.put("data", directorService.getAll(page));
        int totalRecords = directorService.getTotalNumberOfRecords();
        response.put("total records", totalRecords);
        return response;
    }

    @GetMapping("/{id}")
    public Director find(@PathVariable("id") int id){
        try {
            return directorService.find(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Director director){
        directorService.update(director);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id){
        directorService.delete(id);
    }
    
}
