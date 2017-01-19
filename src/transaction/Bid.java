package transaction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Embeddable
public class Bid {

    @NotNull
    protected BigDecimal amount;

    protected Bid() {
    }

    public Bid(BigDecimal amount) {
        this.amount = amount;
    }

    public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmount() {
        return amount;
    }
}