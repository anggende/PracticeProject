package service;

import java.sql.SQLException;
import java.util.List;

import model.Actor;
import model.Customer;
import repository.CustomerRepository;

public class CustomerService {
	
	private CustomerRepository customerRepository;
	
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository=customerRepository;
	}
	
	public void getCustomersByLastName(String searchText) throws SQLException {
		List<Long> customerIdResults = customerRepository.getCustomersByLastName(searchText);
		for(Long customerId: customerIdResults) {
			Customer customer = customerRepository.getCustomerbyCustomerId(customerId);
			System.out.println("Customer First Name: " + customer.getFirstName());
			System.out.println("Customer Last Name: " + customer.getLastName());
			System.out.println("Customer Email Address: " + customer.getEmailaddress());
			System.out.println("Customer Address: " + customer.getAddress()+"\n");
		}
	}
	
	public void getCustomersByFirstName(String searchText) throws SQLException {
		List<Long> customerIdResults = customerRepository.getCustomersByLastName(searchText);
		for(Long customerId: customerIdResults) {
			Customer customer = customerRepository.getCustomerbyCustomerId(customerId);
			System.out.println("Customer First Name: " + customer.getFirstName());
			System.out.println("Customer Last Name: " + customer.getLastName());
			System.out.println("Customer Email Address: " + customer.getEmailaddress());
			System.out.println("Customer Address: " + customer.getAddress()+"\n");
		}
	}
	//getcountryids, getcityids, getaddressids, getcustomer
	public void getCustomersByCountry(String searchText) throws SQLException {
		List<Long> countryIdResults = customerRepository.getCountryIdsbySearchText(searchText);
		for(Long countryId: countryIdResults) {
			List<Long> cityIdResults = customerRepository.getCityIdsbyCountryId(countryId);
			for(Long cityId: cityIdResults) {
				List<Long> addressIdResults = customerRepository.getAddressIdsbyCityId(cityId);
				for(Long addressId: addressIdResults) {
					List<Long> customerIdResults = customerRepository.getCustomerIdsByAddressId(addressId);
					for(Long customerId: customerIdResults) {
						Customer customer = customerRepository.getCustomerbyCustomerId(customerId);
						System.out.println("Customer First Name: " + customer.getFirstName());
						System.out.println("Customer Last Name: " + customer.getLastName());
						System.out.println("Customer Email Address: " + customer.getEmailaddress());
						System.out.println("Customer Address: " + customer.getAddress()+"\n");
					}
				}
			}
		}
	}
}
