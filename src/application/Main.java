package application;

import java.sql.SQLException;
import java.util.Scanner;

import repository.ActorRepository;
import repository.CustomerRepository;
import repository.FilmRepository;
import service.ActorService;
import service.CustomerService;
import service.FilmService;

public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		FilmRepository filmRepository = new FilmRepository();
		ActorRepository actorRepository = new ActorRepository();
		CustomerRepository customerRepository = new CustomerRepository();
		
		ActorService actorService = new ActorService(actorRepository, filmRepository);
		FilmService filmService = new FilmService(filmRepository,actorRepository);
		CustomerService customerService = new CustomerService(customerRepository);
		
		
		System.out.println("What do you want to search for?");
		System.out.println("[1] Films");
		System.out.println("[2] Actors");
		System.out.println("[3] Customers");
		System.out.println("Enter input [1-3]:");
		
		Scanner scanner = new Scanner(System.in);
		String initialChoice = scanner.nextLine();
		
		if (initialChoice.equals("1")) {
			System.out.println("How do you want to search for FILMS?");
			System.out.println("[1] By Title");
			System.out.println("[2] By Genre/Category");
			System.out.println("[3] By Actor Last Name");
			
			System.out.println("Enter input [1-3]:");
			String searchFilter = scanner.nextLine();
			
			System.out.println("Enter search parameter:");
			String searchText = scanner.nextLine();
			
			if (searchFilter.equals("1")) {
				System.out.println("SEARCH RESULTS:");
				filmService.getFilmsByTitle(searchText);
			}
			else if (searchFilter.equals("2")) {
				System.out.println("SEARCH RESULTS:");
				filmService.getFilmsByGenre(searchText);
			}
			else if (searchFilter.equals("3")) {
				System.out.println("SEARCH RESULTS:");
				filmService.getFilmsByActorLastName(searchText);
			}
			else {
				System.out.println("Invalid choice!");
			}
			
		}
		else if (initialChoice.equals("2")) {
			System.out.println("How do you want to search for ACTORS?");
			System.out.println("[1] By Actor First Name");
			System.out.println("[2] By Actor Last Name");
			
			System.out.println("Enter input [1-2]:");
			String searchFilter = scanner.nextLine();
			
			
			if(searchFilter.equals("1")) {
				System.out.println("Enter search parameter:");
				String searchText = scanner.nextLine();
				
				System.out.println("SEARCH RESULTS:");
				actorService.getActorsByFirstName(searchText);
			}
			else if(searchFilter.equals("2")) {
				System.out.println("Enter search parameter:");
				String searchText = scanner.nextLine();
				
				System.out.println("SEARCH RESULTS:");
				actorService.getActorsByLastName(searchText);
			}
			else {
				System.out.println("Invalid input!");
			}
		}
		else if (initialChoice.equals("3")) {
			System.out.println("How do you want to search for CUSTOMERS?");
			System.out.println("[1] By Customer First Name");
			System.out.println("[2] By Customer Last Name");
			System.out.println("[3] By Customer Country");
			
			System.out.println("Enter input [1-3]:");
			String searchFilter = scanner.nextLine();
			if(searchFilter.equals("1")) {
				System.out.println("Enter search parameter:");
				String searchText = scanner.nextLine();
				
				System.out.println("SEARCH RESULTS:");
				customerService.getCustomersByFirstName(searchText);
			}
			else if(searchFilter.equals("2")) {
				System.out.println("Enter search parameter:");
				String searchText = scanner.nextLine();
				
				System.out.println("SEARCH RESULTS:");
				customerService.getCustomersByLastName(searchText);
			}
			else if(searchFilter.equals("3")) {
				System.out.println("Enter search parameter:");
				String searchText = scanner.nextLine();
				
				System.out.println("SEARCH RESULTS:");
				customerService.getCustomersByCountry(searchText);
			}
			else {
				System.out.println("Invalid input!");
			}
		}
		else {
			System.out.println("Invalid choice. Please try again.");
		}
	}

}
