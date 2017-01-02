package caveatemptor.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import caveatemptor.model.MonetaryAmount;

@Converter(autoApply = false)
public class MonetaryAmountConverter implements AttributeConverter<MonetaryAmount, String> {

	@Override
	public String convertToDatabaseColumn(MonetaryAmount monetaryAmount) {
		return monetaryAmount.toString();
	}

	@Override
	public MonetaryAmount convertToEntityAttribute(String s) {
		return MonetaryAmount.fromString(s);
	}

}
