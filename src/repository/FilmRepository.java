package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Film;

public class FilmRepository {
	static final String USER = "root";
	 static final String PASS = "MxK43!aj";
	 static final String URL = "jdbc:mysql://localhost:3306/sakila";
	 String query=null;
	 
	 Connection conn = null;
	
	 
   public void addFilm(Film film) throws SQLException {
   	try {
		    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
		    query = "INSERT INTO film(film_id, title, genre, description, rating, special_features) VALUES( ?, ?, ?, ?, ?, ?)";
		    PreparedStatement preparedStatement = conn.prepareStatement(query);
		    
		    preparedStatement.setLong(1, film.getId());
		    preparedStatement.setString(2, film.getTitle());
		    preparedStatement.setString(3, film.getGenre());
		    preparedStatement.setString(4, film.getDescription());
		    preparedStatement.setString(5, film.getFilmRating());
		    preparedStatement.setString(6, film.getSpecialFeatures());
		    preparedStatement.executeUpdate();
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				conn.close();
		}	
   }
   
   public List<Long> getFilmsByGenre(String genre) throws SQLException {
	   try {
		    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
		    
		    query = "SELECT film_category.film_id FROM film_category WHERE film_category.category_id =(SELECT category.category_id FROM category WHERE category.name LIKE ?)";
		    PreparedStatement preparedStatement = conn.prepareStatement(query);
		    preparedStatement.setString(1, "%"+genre+"%");
		    
		    ResultSet filmSet = preparedStatement.executeQuery();
		    List<Long> filmIdsOfChosenGenre = new ArrayList();
		    while (filmSet.next()) {
		    	Long filmId = filmSet.getLong(1);
		    	filmIdsOfChosenGenre.add(filmId);
		    }
		    return filmIdsOfChosenGenre;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
				conn.close();
		}	
   }
   
   public Film getFilmByFilmId(Long filmId) throws SQLException {
	   try {
		    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
		    
		    query = "SELECT film.title,category.name,film.description,film.rating,film.special_features FROM film INNER JOIN film_category ON"
		    		+ " film_category.film_id = film.film_id INNER JOIN category ON film_category.category_id = category.category_id WHERE film.film_id=?";
		    PreparedStatement preparedStatement = conn.prepareStatement(query);
		    preparedStatement.setLong(1, filmId);
		    
		    ResultSet resultSet = preparedStatement.executeQuery();
		    Film film = new Film(null, null, null, null, null, null);
		    while (resultSet.next()) {
		    	film.setTitle(resultSet.getString(1));
		    	film.setGenre(resultSet.getString(2));
		    	film.setDescription(resultSet.getString(3));
		    	film.setFilmRating(resultSet.getString(4));
		    	film.setSpecialFeatures(resultSet.getString(5));}
		    return film;
		    
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
				conn.close();
		}	
   }
   
   public List<Long> getFilmsByTitle(String title) throws SQLException {
	   try {
		    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
		    
		    query = "SELECT film.film_id FROM film WHERE film.title LIKE ?";
		    PreparedStatement preparedStatement = conn.prepareStatement(query);
		    preparedStatement.setString(1, "%"+title+"%");
		    
		    ResultSet resultSet = preparedStatement.executeQuery();
		    
		    List<Long> titleResults = new ArrayList();
		    while(resultSet.next()) {
		    	Long filmId = resultSet.getLong(1);
		    	titleResults.add(filmId);
		    }
		    return titleResults;
		    
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
				conn.close();
		}	
   }
   
   public List<Long> getFilmIdsByActorId(Long id) throws SQLException {
	   try {
		    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
		    
		    query = "SELECT film_actor.film_id FROM film_actor WHERE film_actor.actor_id = ?";
		    PreparedStatement preparedStatement = conn.prepareStatement(query);
		    preparedStatement.setLong(1, id);
		    
		    ResultSet resultSet = preparedStatement.executeQuery();
		    
		    List<Long> actorLastNameResults = new ArrayList();
		    while(resultSet.next()) {
		    	Long result = resultSet.getLong(1);
		    	actorLastNameResults.add(result);
		    }
		    return actorLastNameResults;
		    
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
				conn.close();
		}	
   }
   

   public void getStarredMovies(Long id) throws SQLException {
		List<Long> filmIdResults = this.getFilmIdsByActorId(id);
		System.out.println("Starred in:");
		for(Long filmId: filmIdResults) {
			Film starredFilm = this.getFilmByFilmId(filmId);
			System.out.println("* " + starredFilm.getTitle());
		}
}
}
