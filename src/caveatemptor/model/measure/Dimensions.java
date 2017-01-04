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
		column = @Column(name = "dimensions_name")
	),
	@AttributeOverride(
		name = "symbol",
		column = @Column(name = "dimensions_symbol")
	)
})
public class Dimensions extends Measurement {
	@NotNull
	protected BigDecimal depth;
	
	@NotNull
	protected BigDecimal height;
	
	@NotNull
	protected BigDecimal width;

	public Dimensions() {
		super();
	}

	public BigDecimal getDepth() {
		return depth;
	}

	public void setDepth(BigDecimal depth) {
		this.depth = depth;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}
	
}
