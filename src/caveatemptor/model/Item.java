package caveatemptor.model;

import java.sql.Blob;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SortComparator;
import org.hibernate.annotations.Type;

import caveatemptor.model.measure.Dimensions;
import caveatemptor.model.measure.Weight;

@Entity
public class Item {

	@Id
	@GeneratedValue()
	protected Long id;
	
	@Lob
	@Column(name = "image", insertable = false, updatable = false) 
	protected byte[] image;
	
	@Lob
	@Column(name = "description") 
	protected String description;
	
	@Lob
	@Column(name = "image", insertable = false, updatable = false) 
	protected Blob imageBlob;
	
	@Lob
	@Column(name = "description", insertable = false, updatable = false) 
	protected Clob descriptionClob;
	
	@Type(type = "yes_no")
	protected boolean verified = true;
	
//	@NotNull
//	@Type(type="monetary_amount_usd")
//	@Columns(columns = {
//		@Column(name = "BUYNOWPRICE_AMOUNT"),
//		@Column(name = "BUYNOPRICE_CURRENCY", length = 3)
//	})
//	@Convert(converter = MonetaryAmountConverter.class, disableConversion = false)
	@Transient
	protected MonetaryAmount buyNowPrice;
	
//	@NotNull
//	@Type(type="monetary_amount_eur")
//	@Columns(columns = {
//		@Column(name = "INITIALPRICE_AMOUNT"),
//		@Column(name = "INITIALPRICE_CURRENCY", length = 3)
//	})
//	@Convert(converter = MonetaryAmountConverter.class, disableConversion = false)
	@Transient
	protected MonetaryAmount initialPrice;
	
	@Transient
	protected Dimensions dimensions;
	
	@Transient
	protected Weight weight;
	
	@ElementCollection
//	@CollectionTable(
//		name = "image1",
//		joinColumns = @JoinColumn(name = "item_id")
//	)
//	@SortNatural
//	@SortComparator(ReverseStringComparator.class)
//	@OrderBy("fileName desc")
	@Transient
	protected Set<String> image1 = new LinkedHashSet<String>();
	
	@ElementCollection
	@CollectionTable(name = "image2")
	@GenericGenerator(name = "gen_image2", strategy = "enhanced-sequence")
//	@CollectionId(
//		columns = @Column(name = "image_id"), 
//		type = @Type(type = "long"), 
//		generator = "gen_image2")
	@OrderBy("image2 asc")
	@Transient
	protected Collection<String> image2 = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name = "image3")
//	@OrderColumn
	@GenericGenerator(name = "gen_image3", strategy = "enhanced-sequence")
	@CollectionId(
		columns = @Column(name = "image_id"), 
		type = @Type(type = "long"), 
		generator = "gen_image3")
	@Column(name = "filename")
	@OrderBy("filename asc")
	@Transient
	protected List<String> image3 = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "image4")
	@MapKeyColumn(name = "filename")
	@Column(name = "imagename")
	@Transient
	protected Map<String, String> image4 = new HashMap<>();
	
	@ElementCollection
	@CollectionTable(name = "image5")
	@MapKeyColumn(name = "filename")
	@Column(name = "imagename")
	@SortComparator(ReverseStringComparator.class)
	@Transient
	protected SortedMap<String, String> image5 = new TreeMap<String, String>();
	
	@ElementCollection
	@CollectionTable(name = "image6")
	@GenericGenerator(name = "gen_image6", strategy = "enhanced-sequence")
	@CollectionId(
		columns = @Column(name = "image_id"),
		type = @Type(type = "long"),
		generator = "gen_image6"
	)
	@Transient
	protected Collection<Image> image6 = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name = "image7")
//	@MapKeyColumn(name = "filename1")
	@Transient
	protected Map<Filename, Image> image7 = new HashMap<>();
	
	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
//	@OrderColumn
	protected List<Bid> bids = new ArrayList<>();
	
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

	public Dimensions getDimensions() {
		return dimensions;
	}

	public void setDimensions(Dimensions dimensions) {
		this.dimensions = dimensions;
	}

	public Weight getWeight() {
		return weight;
	}

	public void setWeight(Weight weight) {
		this.weight = weight;
	}

	public Set<String> getImage1() {
		return image1;
	}

	public void setImage1(Set<String> image1) {
		this.image1 = image1;
	}

	public Collection<String> getImage2() {
		return image2;
	}

	public void setImage2(Collection<String> image2) {
		this.image2 = image2;
	}

	public List<String> getImage3() {
		return image3;
	}

	public void setImage3(List<String> image3) {
		this.image3 = image3;
	}

	public Map<String, String> getImage4() {
		return image4;
	}

	public void setImage4(Map<String, String> image4) {
		this.image4 = image4;
	}
	
	public SortedMap<String, String> getImage5() {
		return image5;
	}

	public void setImage5(SortedMap<String, String> image5) {
		this.image5 = image5;
	}

	public Collection<Image> getImage6() {
		return image6;
	}

	public void setImage6(Collection<Image> image6) {
		this.image6 = image6;
	}

	public Map<Filename, Image> getImage7() {
		return image7;
	}

	public void setImage7(Map<Filename, Image> image7) {
		this.image7 = image7;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", image=" + Arrays.toString(image) + ", description=" + description + ", verified="
				+ verified + "]";
	}

	public static class ReverseStringComparator implements Comparator<String> {
	    @Override
	    public int compare(String a, String b) {
	        return b.compareTo(a);
	    }
	}
}

