package es.javierdmc.movies.domain.entity;

public class Director {

    private int id;
    private String name;
    private int birthYear;
    private int deathYear;

    public Director(){}

    public Director(String name, int birthYear, int deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public Director(int id, String name, int birthYear, int deathYear) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getBirthYear() {
        return birthYear;
    }
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
    public int getDeathYear() {
        return deathYear;
    }
    public void setDeathYear(int deathYear) {
        this.deathYear = deathYear;
    }

    @Override
    public String toString() {
        return "Director [name=" + name + ", birthYear=" + birthYear + ", deathYear=" + deathYear + "]";
    }

    
    
}
