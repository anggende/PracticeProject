package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import model.Actor;
import model.Film;
import repository.ActorRepository;
import repository.FilmRepository;

public class FilmService {
	
	private FilmRepository filmRepository;
	private ActorRepository actorRepository;
	Scanner scanner = new Scanner(System.in);
	
	public FilmService (FilmRepository filmRepository, ActorRepository actorRepository) {
		this.actorRepository=actorRepository;
		this.filmRepository=filmRepository;
	}
	
	public void addFilm() throws SQLException {

		
		System.out.println("Input the film title:");
		String filmTitle = scanner.nextLine();
		
		System.out.println("Choose the number that best corresponds with the film genre.");
		System.out.println("GENRES:");
		filmRepository.getGenreList();
		Long genreId = Long.parseLong(scanner.nextLine());
		
		System.out.println("Input the film description:");
		String filmDescription = scanner.nextLine();
		
		System.out.println("Input the rental duration in days:");
		Integer rentalDuration = Integer.parseInt(scanner.nextLine());
		
		System.out.println("Input the rental rate in USD:");
		Double rentalRate = Double.parseDouble(scanner.nextLine());
		
		System.out.println("Input the replacement cost in USD:");
		Double replacementCost = Double.parseDouble(scanner.nextLine());
		
		System.out.println("Input the length of the film in minutes:");
		Integer length = Integer.parseInt(scanner.nextLine());
		
		System.out.println("Choose the rating that best fits the film ('G','PG','PG-13','R','NC-17'):");
		String filmRating = scanner.nextLine();
		
		System.out.println("Choose which special features the film has among the choices below. Separate each with a comma:");
		System.out.println("('Trailers','Commentaries','Deleted Scenes','Behind the Scenes')");
		String filmSpecialFeatures = scanner.nextLine();
		
		System.out.println("How many actors starred in the film?");
		int actorNumbers = Integer.parseInt(scanner.nextLine());
		List<Long> starredActors = new ArrayList();
		HashMap<Integer,Long> actorChoices = new HashMap<Integer,Long>();
		
		for(int i=1;i<=actorNumbers;i++) {
			System.out.println("Input the last name of Actor [" + i +"] who starred in the film:");
			String searchText = scanner.nextLine();
			List<Long> actorIdResults = actorRepository.getActorsByLastName(searchText);
			int num = 1;
			for (Long actorId: actorIdResults) {
				Actor actor = actorRepository.getActorbyActorId(actorId);
				System.out.println(num + "- " + actor.getLastName() + ", " + actor.getFirstName());
				actorChoices.put(num, actorId);
				num++;
			}
			System.out.println("Choose the number corresponding to the right actor:");
			int chosenActor = Integer.parseInt(scanner.nextLine());
			starredActors.add(actorChoices.get(chosenActor));
		}
		Film film = new Film(filmTitle, null, filmDescription, filmRating, filmSpecialFeatures, null, rentalDuration, length, rentalRate, replacementCost);
		filmRepository.addFilm(film);
		filmRepository.addFilmSpecialFeaturesToFilm(filmSpecialFeatures);
		filmRepository.addNewFilmCategoryDetails(genreId);
		for (Long actorId: starredActors) {
		filmRepository.addStarringFilmActorsDetails(actorId);
		}
	}
	
	public void getFilmsByTitle(String searchText) throws SQLException {
		List<Long> filmIdResults = filmRepository.getFilmsByTitle(searchText);
		for (Long filmId: filmIdResults) {
			Film film = filmRepository.getFilmByFilmId(filmId);
			System.out.println("\nFilm Title: " + film.getTitle());
			System.out.println("Film Genre: " + film.getGenre());
			System.out.println("Film Description: " + film.getDescription());
			System.out.println("Film Rating: " + film.getFilmRating());
			System.out.println("Film Special Features: " + film.getSpecialFeatures());
			System.out.println("Starring:");
			actorRepository.getStarringActors(filmId);
		}
	}
	
	
	public void getFilmsByGenre(String searchText) throws SQLException {
		List<Long> filmIdResults = filmRepository.getFilmsByGenre(searchText);
		for (Long filmId: filmIdResults) {
			Film film = filmRepository.getFilmByFilmId(filmId);
			System.out.println("\nFilm Title: " + film.getTitle());
			System.out.println("Film Genre: " + film.getGenre());
			System.out.println("Film Description: " + film.getDescription());
			System.out.println("Film Rating: " + film.getFilmRating());
			System.out.println("Film Special Features: " + film.getSpecialFeatures());
			System.out.println("Starring:");
			actorRepository.getStarringActors(filmId);
		}
	}
	
	public void getFilmsByActorLastName(String searchText) throws SQLException {
		List<Long> actorIdResults = actorRepository.getActorsByLastName(searchText);
		List<Long> filmIdResults = new ArrayList();
		for (Long actorId: actorIdResults) {
			filmIdResults = filmRepository.getFilmIdsByActorId(actorId);
			for (Long filmId: filmIdResults) {
				Film film = filmRepository.getFilmByFilmId(filmId);
				System.out.println("\nFilm Title: " + film.getTitle());
				System.out.println("Film Genre: " + film.getGenre());
				System.out.println("Film Description: " + film.getDescription());
				System.out.println("Film Rating: " + film.getFilmRating());
				System.out.println("Film Special Features: " + film.getSpecialFeatures());
				System.out.println("Starring:");
				actorRepository.getStarringActors(filmId);
			}
		}
	}
	
	public void getAllFilmsInDatabase() throws SQLException {
		System.out.println("ALL FILMS:");
		List<String> filmTitles = filmRepository.getAllFilms();
		int i = 1;
		for (String filmTitle: filmTitles) {
			System.out.println("[" + i + "] " + filmTitle);
			i++;
		}
	}
	
	public void deleteFilm(String searchText) throws SQLException {
		List<Long> filmIdResults = filmRepository.getFilmsByTitle(searchText);
		int i = 1;
		for (Long filmId: filmIdResults) {
			Film film = filmRepository.getFilmByFilmId(filmId);
			System.out.println("["+i+"] " + film.getTitle());
			i++;
		}
		System.out.println("Enter the number of the film you want to delete:");
		Integer indexFilmToBeDeleted = Integer.parseInt(scanner.nextLine());
		Long filmIdToBeDeleted = filmIdResults.get(indexFilmToBeDeleted-1);
		Film filmToBeDeleted = filmRepository.getFilmByFilmId(filmIdToBeDeleted);
		
		System.out.println("\nFilm Title: " + filmToBeDeleted.getTitle());
		System.out.println("Film Genre: " + filmToBeDeleted.getGenre());
		System.out.println("Film Description: " + filmToBeDeleted.getDescription());
		System.out.println("Film Rating: " + filmToBeDeleted.getFilmRating());
		System.out.println("Film Special Features: " + filmToBeDeleted.getSpecialFeatures());
		System.out.println("Starring:");
		actorRepository.getStarringActors(filmIdToBeDeleted);
	
		System.out.println("Is this the film you want deleted? Enter Y if Yes");
		String deleteConfirmation = scanner.nextLine();
		if (deleteConfirmation.equals("Y")) {
			filmRepository.deleteFilmFromFilmActor(filmIdToBeDeleted);
			filmRepository.deleteFilmFromFilmCategory(filmIdToBeDeleted);
			filmRepository.deleteFilmFromFilm(filmIdToBeDeleted);
			System.out.println("FILM IS NOW DELETED.");
		}
		else {
			System.out.println("FILM NOT DELETED.");
		}
	}
}
