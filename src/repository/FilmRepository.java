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
		    query = "INSERT INTO film(film_id, title, genre, description, rating, special_features, language_id, original_language_id"
		    		+ ", rental_duration, rental_rate, length, replacement_cost) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		    PreparedStatement preparedStatement = conn.prepareStatement(query);
		    preparedStatement.setLong(1, this.getLastFilmId()+1); 
		    preparedStatement.setString(2, film.getTitle());
		    preparedStatement.setString(3, null);
		    preparedStatement.setString(4, film.getDescription());
		    preparedStatement.setString(5, film.getFilmRating());
		    preparedStatement.setString(6, null);
		    
		    preparedStatement.setLong(7, 1);
		    preparedStatement.setString(8, null);
		    preparedStatement.setInt(9, film.getRentalDuration());
		    preparedStatement.setDouble(10, film.getRentalRate());
		    preparedStatement.setInt(11, film.getLength());
		    preparedStatement.setDouble(12, film.getReplacementCost());
		    
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
   
   public void addFilmSpecialFeaturesToFilm(String specialFeatures) throws SQLException {
	   try {
		    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
		    query = "UPDATE film SET special_features = ? WHERE film_id = ?";
		    PreparedStatement preparedStatement = conn.prepareStatement(query);
		    
		    preparedStatement.setString(1, specialFeatures);
		    preparedStatement.setLong(2, this.getLastFilmId());
		    preparedStatement.executeUpdate();
		   
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				conn.close();
		}	
   }
   
   public void addNewFilmCategoryDetails(Long genreId) throws SQLException {
	   try {
		    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
		    String query = "INSERT INTO film_category(film_id,category_id) VALUES(?,?)";
		    PreparedStatement preparedStatement = conn.prepareStatement(query);
		    
		    preparedStatement.setLong(1, this.getLastFilmId());
		    preparedStatement.setLong(2, genreId);
		    preparedStatement.executeUpdate();
		   
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				conn.close();
		}	
   }
   
   public void addStarringFilmActorsDetails(Long actor) throws SQLException {
	   try {
		    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
		    String query = "INSERT INTO film_actor(actor_id,film_id) VALUES (?,?)";
		    PreparedStatement preparedStatement = conn.prepareStatement(query);
		    preparedStatement.setLong(1, actor);
		    preparedStatement.setLong(2, this.getLastFilmId());
		    preparedStatement.executeUpdate();
		   
		} catch (SQLException e) {
			e.printStackTrace();
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
		    Film film = new Film(null, null, null, null, null, null, 0, 0, null, null);
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
   
   public Long getLastFilmId() throws SQLException {
	   try {
		    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
		    
		    query = "SELECT MAX(film_id) FROM film";
		    PreparedStatement preparedStatement = conn.prepareStatement(query);
		    
		    ResultSet resultSet = preparedStatement.executeQuery();
		    Long lastFilmId = null;
		    
		    while(resultSet.next()) {
		    	lastFilmId = resultSet.getLong(1);
		    }
		    return lastFilmId;
		    
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
				conn.close();
		}	
   }
   
   
   public void getGenreList() throws SQLException {
	   try {
		    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
		    
		    query = "SELECT category_id, category.name FROM category";
		    PreparedStatement preparedStatement = conn.prepareStatement(query);
		    
		    ResultSet resultSet = preparedStatement.executeQuery();
		    
		    while(resultSet.next()) {
		    	System.out.println("["+resultSet.getLong(1)+"] "+resultSet.getString(2));
		    }
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				conn.close();
		}	
    }
   
   public List<String> getAllFilms() throws SQLException {
	   try {
		    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
		    
		    query = "SELECT title FROM film";
		    PreparedStatement preparedStatement = conn.prepareStatement(query);
		    ResultSet resultSet = preparedStatement.executeQuery();
		    
		    List<String> filmTitles = new ArrayList();
		    while(resultSet.next()) {
		    	String filmTitle = resultSet.getString(1);
		    	filmTitles.add(filmTitle);
		    }
		    return filmTitles;
		    
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
				conn.close();
		}	
   }
   
   public void deleteFilmFromFilmActor(Long id) throws SQLException {
	   try {
		    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
		    
		    query = "DELETE FROM film_actor WHERE film_id = ?";
		    PreparedStatement preparedStatement = conn.prepareStatement(query);
		    preparedStatement.setLong(1, id);
		    preparedStatement.execute();
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				conn.close();
		}	
   }
   
   public void deleteFilmFromFilmCategory(Long id) throws SQLException {
	   try {
		    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
		    
		    query = "DELETE FROM film_category WHERE film_id = ?";
		    PreparedStatement preparedStatement = conn.prepareStatement(query);
		    preparedStatement.setLong(1, id);
		    preparedStatement.execute();
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				conn.close();
		}	
   }

   public void deleteFilmFromFilm(Long id) throws SQLException {
	   try {
		    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
		    
		    query = "DELETE FROM film WHERE film_id = ?";
		    PreparedStatement preparedStatement = conn.prepareStatement(query);
		    preparedStatement.setLong(1, id);
		    preparedStatement.execute();
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				conn.close();
		}	
   }
   
}
