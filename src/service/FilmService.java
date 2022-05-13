package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Actor;
import model.Film;
import repository.ActorRepository;
import repository.FilmRepository;

public class FilmService {
	
	private FilmRepository filmRepository;
	private ActorRepository actorRepository;
	
	public FilmService (FilmRepository filmRepository, ActorRepository actorRepository) {
		this.actorRepository=actorRepository;
		this.filmRepository=filmRepository;
	}
	
	
	public void addFilm() {
		
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
			Actor actor = actorRepository.getActorbyActorId(actorId);
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
	
}
