package es.javierdmc.movies.domain.entity;

import java.util.List;

public class Movie {

    private int id;
    private String title;
    private int year;
    private int runTime;
    private Director director;
    private List<Actor> actors;
    
    public Movie() {
    }

    public Movie(int id, String title, int year, int runTime) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runTime = runTime;
    }

    public Movie(String title, int year, int runTime) {
        this.title = title;
        this.year = year;
        this.runTime = runTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Movie [id=" + id + ", title=" + title + ", year=" + year + ", runTime=" + runTime + ", director="
                + director + ", actors=" + actors + "]";
    }
}
