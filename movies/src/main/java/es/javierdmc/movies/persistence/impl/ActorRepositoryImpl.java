package es.javierdmc.movies.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import es.javierdmc.movies.db.DBUtil;
import es.javierdmc.movies.domain.entity.Actor;
import es.javierdmc.movies.exception.DBConnectionException;
import es.javierdmc.movies.exception.ResourceNotFoundException;
import es.javierdmc.movies.exception.SQLStatmentException;
import es.javierdmc.movies.persistence.ActorRepository;

@Repository
public class ActorRepositoryImpl implements ActorRepository{

    private final int LIMIT = 10;

    @Override
    public void create(Actor actor) {
        final String SQL = "INSERT INTO actors (name, birthYear, deathYear) VALUES (?,?,?)";
        List<Object> params = new ArrayList<>();
        params.add(actor.getName());
        params.add(actor.getBirthYear());
        params.add(actor.getDeathYear());
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
    public List<Actor> getAll(Optional<Integer> page) {
        String SQL = "SELECT * FROM actors";
        if(page.isPresent()){
            int offset = (page.get()-1) * LIMIT;
            SQL += String.format(" LIMIT %d, %d", offset, LIMIT);
        }
        List<Actor> actors = new ArrayList<>();
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            while (resultSet.next()){
                actors.add(
                    new Actor(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("birthYear"),
                        resultSet.getInt("deathYear")
                    )
                );
            }
            DBUtil.close(connection);
            return actors;
        } catch (DBConnectionException e) {
            throw e;
        } catch (SQLException e){
            throw new SQLStatmentException("SQL: " + e);
        }
    }

    @Override
    public Actor find(int id) {
        final String SQL = "SELECT * FROM actors WHERE id = ? LIMIT 1";
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            DBUtil.close(connection);
            if(resultSet.next()){
                return new Actor(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("birthYear"),
                        resultSet.getInt("deathYear")
                );
            } else {
                throw new ResourceNotFoundException("Id actor: " + id);
            }
        } catch (DBConnectionException e) {
            throw e;
        } catch (SQLException e){
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    @Override
    public void update(Actor actor) {
        final String SQL = "UPDATE actor SET name = ?, birhtYear = ?, deathYear = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(actor.getId());
        params.add(actor.getName());
        params.add(actor.getBirthYear());
        params.add(actor.getDeathYear());
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
        final String SQL = "DELETE FROM actors WHERE id=?";
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
    public int getTotalNumberOfRecords() {
        final String SQL = "SELECT COUNT(*) FROM actors";
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
