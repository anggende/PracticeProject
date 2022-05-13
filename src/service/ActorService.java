package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Actor;
import repository.ActorRepository;
import repository.FilmRepository;

public class ActorService {

	private ActorRepository actorRepository;
	private FilmRepository filmRepository;
	
	public ActorService(ActorRepository actorRepository, FilmRepository filmRepository) {
		this.actorRepository=actorRepository;
		this.filmRepository=filmRepository;
	}
	
	Scanner scanner = new Scanner(System.in); 
	
	private void addActor() throws SQLException {
		System.out.println("Please enter the actor's first name:");
		String firstName = scanner.nextLine();
		System.out.println("Please enter the actor's last name:");
		String lastName = scanner.nextLine();
		
		Actor actor = new Actor(firstName, lastName);
		actorRepository.addActor(actor);
		System.out.println("Actor added successfully.");
	}
	
	
	public void getActorsByLastName(String searchText) throws SQLException {
		List<Long> actorIdResults = actorRepository.getActorsByLastName(searchText);
		for(Long actorId: actorIdResults) {
			Actor actor = actorRepository.getActorbyActorId(actorId);
			System.out.println("\n" + actor.getLastName() + ", " + actor.getFirstName());
			filmRepository.getStarredMovies(actorId);
		}
	}


	public void getActorsByFirstName(String searchText) throws SQLException {
		List<Long> actorIdResults = actorRepository.getActorsByFirstName(searchText);
		for(Long actorId: actorIdResults) {
			Actor actor = actorRepository.getActorbyActorId(actorId);
			System.out.println("\n" + actor.getLastName() + ", " + actor.getFirstName());
			filmRepository.getStarredMovies(actorId);
		}
	}
	
	
	
}
