package model;


public class Actor {
	private Long id;
	private String firstName;
	private String lastName;
	
	public Actor(String firstName, String lastName) {
		this.firstName=firstName;
		this.lastName=lastName;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id2) {
		this.id = id2;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "\n" + lastName + ", " + firstName;
	}
	
	
}
