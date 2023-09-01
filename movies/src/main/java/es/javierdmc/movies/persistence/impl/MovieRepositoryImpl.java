package es.javierdmc.movies.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import es.javierdmc.movies.db.DBUtil;
import es.javierdmc.movies.domain.entity.Movie;
import es.javierdmc.movies.exception.DBConnectionException;
import es.javierdmc.movies.exception.ResourceNotFoundException;
import es.javierdmc.movies.exception.SQLStatmentException;
import es.javierdmc.movies.persistence.MovieRepository;

@Repository
public class MovieRepositoryImpl implements MovieRepository{

    private final int LIMIT = 10;

    @Override
    public void create(Movie movie) {
        final String SQL = "INSERT INTO movies (title, year, runtime) VALUES (?,?,?)";
        List<Object> params = new ArrayList<>();
        params.add(movie.getTitle());
        params.add(movie.getYear());
        params.add(movie.getRunTime());
        try (Connection connection = DBUtil.open()){
            DBUtil.insert(connection, SQL, params);
            DBUtil.close(connection);
        } catch (DBConnectionException e) {
            throw e;
        } catch (SQLException e){
            throw new SQLStatmentException("SQL: " + SQL);
        } catch (Exception e){
            System.out.println("Exception: " + e);
        }
    }

    @Override
    public List<Movie> getAll(Optional<Integer> page) {
        String SQL = "SELECT * FROM movies";
        if(page.isPresent()){
            int offset = (page.get()-1) * LIMIT;
            SQL += String.format(" LIMIT %d, %d", offset, LIMIT);
        }
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            while (resultSet.next()){
                movies.add(
                    new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("year"),
                        resultSet.getInt("runtime")
                    )
                );
            }
            DBUtil.close(connection);
            return movies;
        } catch (DBConnectionException e) {
            throw e;
        } catch (SQLException e){
            throw new SQLStatmentException("SQL: " + e);
        }
    }

    @Override
    public Movie find(int id) {
        final String SQL = "SELECT * FROM movies WHERE id = ? LIMIT 1";
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            DBUtil.close(connection);
            if(resultSet.next()){
                return new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("year"),
                        resultSet.getInt("runtime")
                );
            } else {
                throw new ResourceNotFoundException("Id movie: " + id);
            }
        } catch (DBConnectionException e) {
            throw e;
        } catch (SQLException e){
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    @Override
    public void update(Movie movie) {
        final String SQL = "UPDATE movies SET title = ?, year = ?, runTime = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(movie.getTitle());
        params.add(movie.getYear());
        params.add(movie.getRunTime());
        params.add(movie.getId());
        try (Connection connection = DBUtil.open()){
            DBUtil.update(connection, SQL, params);
            DBUtil.close(connection);
        } catch (DBConnectionException e) {
            throw e;
        } catch (SQLException e){
            throw new SQLStatmentException("SQL: " + SQL);
        } catch (Exception e){
            System.out.println("Exception: " + e);
        }
    }

    @Override
    public void delete(int id) {
        final String SQL = "DELETE FROM movies WHERE id=?";
        try (Connection connection = DBUtil.open()){
            DBUtil.delete(connection, SQL, List.of(id));
            DBUtil.close(connection);
        } catch (DBConnectionException e) {
            throw e;
        } catch (SQLException e){
            throw new SQLStatmentException("SQL: " + SQL);
        } catch (Exception e){
            System.out.println("Exception: " + e);
        }
    }

    @Override
    public int getTotalNumberOfRecords(){
        final String SQL = "SELECT COUNT(*) FROM movies";
        int rows = 0;
        try (Connection connection = DBUtil.open()){
            rows = DBUtil.countRows(connection, SQL, null);
            DBUtil.close(connection);
        } catch (DBConnectionException e) {
            throw e;
        } catch (SQLException e){
            throw new SQLStatmentException("SQL: " + SQL);
        } catch (Exception e){
            System.out.println("Exception: " + e);
        }
        if(rows>0){
                return rows;
        }else{
                throw new ResourceNotFoundException("Rows not found");
        }
    }
}
