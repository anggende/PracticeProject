package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Actor;

public class ActorRepository{
	 
	 //
	 static final String USER = "root";
	 static final String PASS = "MxK43!aj";
	 static final String URL = "jdbc:mysql://localhost:3306/sakila";
	 String query=null;
	 
	 Connection conn = null;
	
	 
    public void addActor(Actor actor) throws SQLException {
    	try {
		    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
		    query = "INSERT INTO actor(actor_id, first_name, last_name) VALUES( ?, ?, ?)";
		    PreparedStatement preparedStatement = conn.prepareStatement(query);
		    
		    preparedStatement.setLong(1, actor.getId());
		    preparedStatement.setString(2, actor.getFirstName());
		    preparedStatement.setString(3, actor.getLastName());
		    preparedStatement.executeUpdate();
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				conn.close();
		}	
    }
    
    public List<Long> getActorsByFirstName(String searchText) throws SQLException {
    	try {
		    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
		    query = "SELECT actor_id FROM actor WHERE actor.first_name LIKE ?";
		    PreparedStatement preparedStatement = conn.prepareStatement(query);
		    preparedStatement.setString(1, "%"+searchText+"%");
		    ResultSet resultSet = preparedStatement.executeQuery();
		    
		    List<Long> firstNameResults = new ArrayList();
		    while(resultSet.next()) {
		    	Long actorId = resultSet.getLong(1);
		    	firstNameResults.add(actorId);
		    }
		    return firstNameResults;
		    
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
				conn.close();
		}	
    }
    
    public List<Long> getActorsByLastName(String searchText) throws SQLException {
    	try {
		    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
		    query = "SELECT actor_id FROM actor WHERE actor.last_name LIKE ?";
		    PreparedStatement preparedStatement = conn.prepareStatement(query);
		    preparedStatement.setString(1, "%"+searchText+"%");
		    ResultSet resultSet = preparedStatement.executeQuery();
		    
		    List<Long> lastNameResults = new ArrayList();
		    while(resultSet.next()) {
		    	Long actorId = resultSet.getLong(1);
		    	lastNameResults.add(actorId);
		    }
		    return lastNameResults;
		    
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
				conn.close();
		}	
    }
    
    public Actor getActorbyActorId(Long id) throws SQLException {
    	try {
		    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
		    query = "SELECT actor_id,first_name,last_name FROM actor WHERE actor.actor_id = ?";
		    PreparedStatement preparedStatement = conn.prepareStatement(query);
		    preparedStatement.setLong(1, id);
		    
		    ResultSet resultSet = preparedStatement.executeQuery();
		    
		    Actor actor = new Actor(null, null);
		    while(resultSet.next()) {
		    	actor.setId(id);
		    	actor.setFirstName(resultSet.getString(2));
		    	actor.setLastName(resultSet.getString(3));
		    }
		    return actor;
		    
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
				conn.close();
		}	
    }
    
    public List<Long> getActorIdsByFilmId(Long id) throws SQLException {
 	   try {
 		    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
 		    
 		    query = "SELECT film_actor.actor_id FROM film_actor WHERE film_actor.film_id = ?";
 		    PreparedStatement preparedStatement = conn.prepareStatement(query);
 		    preparedStatement.setLong(1, id);
 		    
 		    ResultSet resultSet = preparedStatement.executeQuery();
 		    
 		    List<Long> actorIdResults = new ArrayList();
 		    while(resultSet.next()) {
 		    	Long result = resultSet.getLong(1);
 		    	actorIdResults.add(result);
 		    }
 		    return actorIdResults;
 		    
 		} catch (SQLException e) {
 			e.printStackTrace();
 			return null;
 		} finally {
 				conn.close();
 		}	
 	   
 	   
    }
    
    public void getStarringActors(Long id) throws SQLException {
		List<Long> actorIdResults = this.getActorIdsByFilmId(id);
		for(Long actorId: actorIdResults) {
			Actor starringActor = this.getActorbyActorId(actorId);
			System.out.println("* " + starringActor.getLastName() + ", " + starringActor.getFirstName());
		}
	}
	 
}

