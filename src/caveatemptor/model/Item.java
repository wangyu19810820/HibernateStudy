package caveatemptor.model;

import java.sql.Blob;
import java.sql.Clob;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;

import caveatemptor.converter.MonetaryAmountConverter;

@Entity
public class Item {

	@Id
	@GeneratedValue()
	protected Long id;
	
	@Lob
	@Column(name = "image", insertable = false, updatable = false) 
	protected byte[] image;
	
	@Lob
	@Column(name = "description", insertable = false, updatable = false) 
	protected String description;
	
	@Lob
	@Column(name = "image", insertable = false, updatable = false) 
	protected Blob imageBlob;
	
	@Lob
	@Column(name = "description", insertable = false, updatable = false) 
	protected Clob descriptionClob;
	
	@Type(type = "yes_no")
	protected boolean verified = true;
	
	@NotNull
//	@Type(type="monetary_amount_usd")
//	@Columns(columns = {
//		@Column(name = "BUYNOWPRICE_AMOUNT"),
//		@Column(name = "BUYNOPRICE_CURRENCY", length = 3)
//	})
	@Convert(converter = MonetaryAmountConverter.class, disableConversion = false)
	protected MonetaryAmount buyNowPrice;
	
	@NotNull
//	@Type(type="monetary_amount_eur")
//	@Columns(columns = {
//		@Column(name = "INITIALPRICE_AMOUNT"),
//		@Column(name = "INITIALPRICE_CURRENCY", length = 3)
//	})
	@Convert(converter = MonetaryAmountConverter.class, disableConversion = false)
	protected MonetaryAmount initialPrice;

	public Item() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Blob getImageBlob() {
		return imageBlob;
	}

	public void setImageBlob(Blob imageBlob) {
		this.imageBlob = imageBlob;
	}

	public Clob getDescriptionClob() {
		return descriptionClob;
	}

	public void setDescriptionClob(Clob descriptionClob) {
		this.descriptionClob = descriptionClob;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public MonetaryAmount getBuyNowPrice() {
		return buyNowPrice;
	}

	public void setBuyNowPrice(MonetaryAmount buyNowPrice) {
		this.buyNowPrice = buyNowPrice;
	}

	public MonetaryAmount getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(MonetaryAmount initialPrice) {
		this.initialPrice = initialPrice;
	}
}
