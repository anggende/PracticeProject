package model;

public class Customer {
	private Long id;
	private String firstName;
	private String lastName;
	private String emailaddress;
	private String address;
	
	public Customer(String firstName, String lastName, String emailaddress, String address) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.emailaddress=emailaddress;
		this.address=address;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
