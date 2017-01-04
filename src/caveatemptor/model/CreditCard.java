package caveatemptor.model;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
//@AttributeOverride(
//        name = "owner",
//        column = @Column(name = "CC_OWNER", nullable = false))
@DiscriminatorValue("CC")
//@SecondaryTable(
//	name = "CREDITCARD",
//	pkJoinColumns = @PrimaryKeyJoinColumn(name = "creditcard_id"))
@PrimaryKeyJoinColumn(name = "creaditcard_id")
public class CreditCard extends BillingDetails {

    @NotNull
//    @Column(table = "CREDITCARD")
    @Size(min = 2, max = 100, message = "card number between 2 and 100")
    protected String cardNumber;

    @NotNull
//    @Column(table = "CREDITCARD")
    protected String expMonth;

    @NotNull
//    @Column(table = "CREDITCARD")
    protected String expYear;

    // ...

    public CreditCard() {
        super();
    }

	public CreditCard(String owner, String cardNumber, String expMonth, String expYear) {
        super(owner);
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }

	public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }
	
	@Override
	public String toString() {
		return "CreditCard [cardNumber=" + cardNumber + ", expMonth=" + expMonth + ", expYear=" + expYear + ", id=" + id
				+ ", owner=" + owner + "]";
	}

}
