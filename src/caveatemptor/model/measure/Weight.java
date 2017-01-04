package caveatemptor.model.measure;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@AttributeOverrides({
	@AttributeOverride(
		name = "name",
		column = @Column(name = "weight_name")
	),
	@AttributeOverride(
		name = "symbol",
		column = @Column(name = "weight_symbol")
	)
})
public class Weight extends Measurement {

	@NotNull
	@Column(name = "weight")
	protected BigDecimal value;

	public Weight() {
		super();
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
}
