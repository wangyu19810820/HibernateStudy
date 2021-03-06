package caveatemptor.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import caveatemptor.converter.ZipcodeConverter;

@Embeddable
public class City {

	@NotNull
	@Column(nullable = false, length = 6)
	@Convert(converter = ZipcodeConverter.class, 
	 	     disableConversion = false)
	protected Zipcode zipcode;
	
	@NotNull
	@Column(nullable = false)
	protected String name;

	@NotNull
	@Column(nullable = false)
	protected String country;

	public City() {
		super();
	}

	public Zipcode getZipcode() {
		return zipcode;
	}

	public void setZipcode(Zipcode zipcode) {
		this.zipcode = zipcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "City [zipcode=" + zipcode + ", name=" + name + ", country=" + country + "]";
	}
	
}
