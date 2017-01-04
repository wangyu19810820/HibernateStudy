package caveatemptor.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;

@Embeddable
public class Address {

	@NotNull
	@Column(nullable = false)
	protected String street;
	
	@Embedded
	@NotNull
	protected City city;

	@ElementCollection
	@CollectionTable(name = "contact")
	@Column(name = "name", nullable = false)
	protected Set<String> contacts = new HashSet<>();
	
	public Address() {
		super();
	}

	public Address(String street, City city) {
		super();
		this.street = street;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Set<String> getContacts() {
		return contacts;
	}

	public void setContacts(Set<String> contacts) {
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", contacts=" + contacts + "]";
	}

}
