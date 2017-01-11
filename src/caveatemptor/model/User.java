package caveatemptor.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import caveatemptor.converter.ZipcodeConverter;

@Entity(name = "Users")
@Table(name = "USERS")
public class User implements Serializable {

	private static final long serialVersionUID = 8071498129879952384L;

	@Id
	@GeneratedValue()
	protected Long id;
	
//	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
//	@JoinColumn(unique = true)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "user_address",
			   joinColumns = @JoinColumn(name = "user_id"),
			   inverseJoinColumns = @JoinColumn(name = "address_id", nullable = false, unique = true))
	protected transient AddressEntity addressEntity;
	
	@AttributeOverride(name = "city.name", column = @Column(name = "name"))
//	@Convert(converter = ZipcodeConverter.class, 
//			 attributeName = "city.zipcode",
//			 disableConversion = false)
	protected transient Address homeAddress;
	
//	@Embedded	// ¿ÉÑ¡
	@AttributeOverrides({
		@AttributeOverride(name = "street", column = @Column(name = "BILLING_STREET")),
		@AttributeOverride(name = "city.country", column = @Column(name = "BILLING_COUNTRY")),
		@AttributeOverride(name = "city.zipcode", column = @Column(name = "BILLING_ZIPCODE")),
		@AttributeOverride(name = "city.name", column = @Column(name = "BILLING_CITY"))
	})
//	@Convert(converter = ZipcodeConverter.class, 
//			 attributeName = "city.zipcode",
//			 disableConversion = false)
	protected transient Address billingAddress;
	
	@ManyToOne(fetch = FetchType.LAZY)
	protected transient BillingDetails defaultBilling;
	
	@OneToMany(mappedBy = "user")
	protected transient Set<BillingDetails>billingDetails = new HashSet<>();

	public User() {
		super();
	}

	public Long getId() {
		return id;
	}

//	public Address getHomeAddress() {
//		return homeAddress;
//	}
//
//	public void setHomeAddress(Address homeAddress) {
//		this.homeAddress = homeAddress;
//	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public BillingDetails getDefaultBilling() {
		return defaultBilling;
	}

	public void setDefaultBilling(BillingDetails defaultBilling) {
		this.defaultBilling = defaultBilling;
	}

	public Set<BillingDetails> getBillingDetails() {
		return billingDetails;
	}

	public void setBillingDetails(Set<BillingDetails> billingDetails) {
		this.billingDetails = billingDetails;
	}

	public void addBillingDetails(BillingDetails bd) {
		boolean b = this.billingDetails.add(bd);
		if (b) {
			bd.setUser(this);
		}
	}
	
	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public AddressEntity getAddressEntity() {
		return addressEntity;
	}

	public void setAddressEntity(AddressEntity addressEntity) {
		this.addressEntity = addressEntity;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	@Override
//	public String toString() {
//		return "User [id=" + id + ", homeAddress=" + homeAddress + ", billingAddress=" + billingAddress
//				+ ", defaultBilling=" + defaultBilling + "]";
//	}

}
