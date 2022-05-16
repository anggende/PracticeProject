package model;

public class Film {

	private Long id;
	private String title;
	private String genre;
	private String description;
	private String filmRating;
	private String specialFeatures;
	private String actorLastName;
	private int rentalDuration;
	private int length;
	private Double rentalRate;
	private Double replacementCost;
	public Film(String title, String genre, String description, String filmRating, String specialFeatures, String actorLastName, int rentalDuration,
			int length, Double rentalRate, Double replacementCost) {
		this.title= title;
		this.genre=genre;
		this.description=description;
		this.filmRating = filmRating;
		this.specialFeatures=specialFeatures;
		this.actorLastName=actorLastName;
		this.rentalDuration=rentalDuration;
		this.length=length;
		this.rentalRate=rentalRate;
		this.replacementCost=replacementCost;
	}
	@Override
	public String toString() {
		return "\nFilm Title: " + title + "\nFilm Genre: " + genre
				+ "\nFilm Description: " + description + "\nFilm Rating: "
				+ filmRating + "\nFilm Special Features: " + specialFeatures;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFilmRating() {
		return filmRating;
	}
	public void setFilmRating(String filmRating) {
		this.filmRating = filmRating;
	}
	public String getSpecialFeatures() {
		return specialFeatures;
	}
	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}
	public String getActorlastName() {
		return actorLastName;
	}
	public void setActorlastName(String actorLastName) {
		this.actorLastName = actorLastName;
	}
	public String getActorLastName() {
		return actorLastName;
	}
	public void setActorLastName(String actorLastName) {
		this.actorLastName = actorLastName;
	}
	public int getRentalDuration() {
		return rentalDuration;
	}
	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public Double getRentalRate() {
		return rentalRate;
	}
	public void setRentalRate(Double rentalRate) {
		this.rentalRate = rentalRate;
	}
	public Double getReplacementCost() {
		return replacementCost;
	}
	public void setReplacementCost(Double replacementCost) {
		this.replacementCost = replacementCost;
	}
}
