package caveatemptor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class AddressEntity {

	@Id
	@GeneratedValue()
	protected Long id;
	protected String street;
	protected String zipcode;
	protected String city;
	
//	@OneToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
//	@PrimaryKeyJoinColumn
	@OneToOne(mappedBy = "addressEntity")
	protected User user;

	public AddressEntity() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "AddressEntity [id=" + id + ", street=" + street + ", zipcode=" + zipcode + ", city=" + city + "]";
	}

}
