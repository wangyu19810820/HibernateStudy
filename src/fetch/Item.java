package fetch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

@Entity
@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
public class Item {

	@Id
	@GeneratedValue
	protected Long id;

	@Basic(fetch = FetchType.LAZY)
	protected String name;

	protected double price;

	protected String desc1;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	protected User seller;
	
	@ElementCollection
	@CollectionTable(name = "item_image_1")
	@LazyCollection(LazyCollectionOption.EXTRA)
	protected Set<String> imageSet = new HashSet<>();
	
	@ElementCollection
	@CollectionTable(name = "item_image_2")
	@LazyCollection(LazyCollectionOption.FALSE)
	protected List<String> imageList = new ArrayList<>();

//	@ManyToOne
//	@JoinColumn(name = "category_id")
//	protected Category category;

	@Version
	protected long version;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "item_id")
	protected List<Bid> bid = new ArrayList<>();

	public Item() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDesc1() {
		return desc1;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

//	public Category getCategory() {
//		return category;
//	}
//
//	public void setCategory(Category category) {
//		this.category = category;
//	}
//
	public List<Bid> getBid() {
		return bid;
	}

	public void setBid(List<Bid> bid) {
		this.bid = bid;
	}

	public Set<String> getImageSet() {
		return imageSet;
	}

	public void setImageSet(Set<String> imageSet) {
		this.imageSet = imageSet;
	}

	public List<String> getImageList() {
		return imageList;
	}

	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}

}