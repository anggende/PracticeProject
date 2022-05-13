package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;


public class CustomerRepository {
	 static final String USER = "root";
	 static final String PASS = "MxK43!aj";
	 static final String URL = "jdbc:mysql://localhost:3306/sakila";
	 String query=null;
	 
	 Connection conn = null;
	 
	 public Customer getCustomerbyCustomerId(Long id) throws SQLException {
	    	try {
			    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
			    
			    query = "SELECT customer.first_name, customer.last_name, customer.email,"
			    		+ "address.address, address.district,"
			    		+ "city.city, country.country FROM customer INNER JOIN address ON customer.address_id"
			    		+ "=address.address_id INNER JOIN city ON address.city_id=city.city_id INNER JOIN"
			    		+ " country ON country.country_id=city.country_id WHERE customer.customer_id = ?";
			    
			    PreparedStatement preparedStatement = conn.prepareStatement(query);
			    preparedStatement.setLong(1, id);
			    
			    ResultSet resultSet = preparedStatement.executeQuery();
			    
			    Customer customer = new Customer(null, null, null, null);
			    while(resultSet.next()) {
			    	customer.setId(id);
			    	customer.setFirstName(resultSet.getString(1));
			    	customer.setLastName(resultSet.getString(2));
			    	customer.setEmailaddress(resultSet.getString(3));
			    	customer.setAddress(resultSet.getString(4) +", "+resultSet.getString(5)+", "+resultSet.getString(6)+ ", "
			    			+ resultSet.getString(7));
			    }
			    return customer;
			    
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
					conn.close();
			}	
	    }
	 
	 public List<Long> getCustomersByFirstName(String searchText) throws SQLException {
	    	try {
			    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
			    query = "SELECT customer_id FROM customer WHERE customer.first_name LIKE ?";
			    PreparedStatement preparedStatement = conn.prepareStatement(query);
			    preparedStatement.setString(1, "%"+searchText+"%");
			    ResultSet resultSet = preparedStatement.executeQuery();
			    
			    List<Long> firstNameResults = new ArrayList();
			    while(resultSet.next()) {
			    	Long customerId = resultSet.getLong(1);
			    	firstNameResults.add(customerId);
			    }
			    return firstNameResults;
			    
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
					conn.close();
			}	
	    }
	 
	 public List<Long> getCustomersByLastName(String searchText) throws SQLException {
	    	try {
			    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
			    query = "SELECT customer_id FROM customer WHERE customer.last_name LIKE ?";
			    PreparedStatement preparedStatement = conn.prepareStatement(query);
			    preparedStatement.setString(1, "%"+searchText+"%");
			    ResultSet resultSet = preparedStatement.executeQuery();
			    
			    List<Long> lastNameResults = new ArrayList();
			    while(resultSet.next()) {
			    	Long customerId = resultSet.getLong(1);
			    	lastNameResults.add(customerId);
			    }
			    return lastNameResults;
			    
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
					conn.close();
			}	
	    }
	 
	 public List<Long> getCustomerIdsByAddressId(Long id) throws SQLException {
	    	try {
			    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
			    query = "SELECT customer.customer_id FROM customer WHERE customer.address_id = ?";
			    PreparedStatement preparedStatement = conn.prepareStatement(query);
			    preparedStatement.setLong(1, id);
			    ResultSet resultSet = preparedStatement.executeQuery();
			    
			    List<Long> customerResults = new ArrayList();
			    while(resultSet.next()) {
			    	Long customerId = resultSet.getLong(1);
			    	customerResults.add(customerId);
			    }
			    return customerResults;
			    
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
					conn.close();
			}	
	    }
	 
	 public List<Long> getCountryIdsbySearchText(String searchText) throws SQLException {
	    	try {
			    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
			    query = "SELECT country.country_id FROM country WHERE country.country LIKE ?";
			    PreparedStatement preparedStatement = conn.prepareStatement(query);
			    preparedStatement.setString(1, "%"+searchText+"%");
			    ResultSet resultSet = preparedStatement.executeQuery();
			    
			    List<Long> countryResults = new ArrayList();
			    while(resultSet.next()) {
			    	Long countryId = resultSet.getLong(1);
			    	countryResults.add(countryId);
			    }
			    return countryResults;
			    
			} catch (SQLException e) {
				e.printStackTrace(); 
				return null;
			} finally {
					conn.close();
			}	
	 }
	 
	 public List<Long> getCityIdsbyCountryId(Long id) throws SQLException {
	    	try {
			    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
			    query = "SELECT city.city_id FROM city WHERE city.country_id =?";
			    PreparedStatement preparedStatement = conn.prepareStatement(query);
			    preparedStatement.setLong(1, id);
			    ResultSet resultSet = preparedStatement.executeQuery();
			    
			    List<Long> cityResults = new ArrayList();
			    while(resultSet.next()) {
			    	Long cityId = resultSet.getLong(1);
			    	cityResults.add(cityId);
			    }
			    return cityResults;
			    
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
					conn.close();
			}	
	 }	
	 
	 public List<Long> getAddressIdsbyCityId(Long id) throws SQLException {
	    	try {
			    conn = DriverManager.getConnection(URL + "?user=" + USER +"&password="+PASS);
			    query = "SELECT address.address_id FROM address WHERE address.city_id= ?";
			    PreparedStatement preparedStatement = conn.prepareStatement(query);
			    preparedStatement.setLong(1, id);
			    ResultSet resultSet = preparedStatement.executeQuery();
			    
			    List<Long> addressResults = new ArrayList();
			    while(resultSet.next()) {
			    	Long addressId = resultSet.getLong(1);
			    	addressResults.add(addressId);
			    }
			    return addressResults;
			    
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
					conn.close();
			}	
	 }
	 
}
