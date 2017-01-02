package caveatemptor.converter;

import javax.persistence.AttributeConverter;

import caveatemptor.model.GermanZipcode;
import caveatemptor.model.SwissZipcode;
import caveatemptor.model.Zipcode;

public class ZipcodeConverter implements AttributeConverter<Zipcode, String> {

	public String convertToDatabaseColumn(Zipcode attribute) {
		return attribute.getValue();
	}
	
	public Zipcode convertToEntityAttribute(String s) {
		if (s.length() == 5) {
			return new GermanZipcode(s);
		} else if (s.length() == 4) {
			return new SwissZipcode(s);
		} else {
			throw new IllegalArgumentException("Unsupported zipcode in database: " + s);
		}
	}
}
