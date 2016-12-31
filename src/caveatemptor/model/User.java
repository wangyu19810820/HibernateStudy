package caveatemptor.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Users")
@Table(name = "USERS")
public class User implements Serializable {

	private static final long serialVersionUID = 8071498129879952384L;

	@Id
	@GeneratedValue()
	protected Long id;
	
	protected Address homeAddress;
	
//	@Embedded	// ¿ÉÑ¡
	@AttributeOverrides({
		@AttributeOverride(name = "street", column = @Column(name = "BILLING_STREET")),
		@AttributeOverride(name = "city.country", column = @Column(name = "BILLING_COUNTRY")),
		@AttributeOverride(name = "city.zipcode", column = @Column(name = "BILLING_ZIPCODE")),
		@AttributeOverride(name = "city.name", column = @Column(name = "BILLING_CITY"))
	})
	protected Address billingAddress;

	public User() {
		super();
	}

	public Long getId() {
		return id;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", homeAddress=" + homeAddress + ", billingAddress=" + billingAddress + "]";
	}
}
