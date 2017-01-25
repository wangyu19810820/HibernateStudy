package fetch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import filtering.PersistEntityListener1;

@NamedEntityGraphs({
	@NamedEntityGraph(
		name = "ItemSeller",
		attributeNodes = {
			@NamedAttributeNode("seller")
		}
	)
})

@Entity
@EntityListeners(PersistEntityListener1.class)
@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
//@Audited
@Filter(name = "limitByUserRank", 
		condition = ":currentUserRank >= (select u.rank from ce_user u where u.id = seller_id)")
public class Item {

	public static final String PROFILE_JOIN_SELLER = "PROFILE_JOIN_SELLER";
	public static final String PROFILE_JOIN_BIDS = "PROFILE_JOIN_BIDS";
	
	@Id
	@GeneratedValue
	protected Long id;

	@Basic(fetch = FetchType.LAZY)
	protected String name;

	protected double price;

	protected String desc1;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@LazyToOne(LazyToOneOption.NO_PROXY)
	@BatchSize(size = 2)
//	@Fetch(FetchMode.SELECT)
	protected User seller;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "item_image_1")
//	@LazyCollection(LazyCollectionOption.EXTRA)
	@BatchSize(size = 2)
	protected Set<String> imageSet = new HashSet<>();
	
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "item_image_2")
//	@LazyCollection(LazyCollectionOption.FALSE)
	protected List<String> imageList = new ArrayList<>();

//	@ManyToOne
//	@JoinColumn(name = "category_id")
//	protected Category category;

	@Version
	protected long version;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "item_id")
//	@LazyToOne(LazyToOneOption.FALSE)
	@BatchSize(size = 13)
//	@Fetch(FetchMode.SUBSELECT)
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

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price=" + price + ", desc1=" + desc1 + ", seller=" + seller
				+ "]";
	}

}
