package caveatemptor.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
public class Category {

	@Id
	@GeneratedValue()
	protected Long id;
	
	protected String name;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "category_item",
			   joinColumns = @JoinColumn(name = "category_id"),
			   inverseJoinColumns = @JoinColumn(name = "item_id"))
	@GenericGenerator(name = "cat_item_id_gen", strategy = "enhanced-sequence")
	@CollectionId(columns = @Column(name = "category_item_id"),
				  type = @Type(type = "long"),
				  generator = "cat_item_id_gen")
	@Transient
	protected List<Item> items = new ArrayList<>();
	
	@OneToMany(mappedBy = "category")
	@Transient
	protected Set<CategorizedItem> categorizedItem = new HashSet<>();
	
	@ElementCollection
	@CollectionTable(name = "category_item_2",
				     joinColumns = @JoinColumn(name = "category_id"))
	@Transient
	protected Set<CategorizedItem1> categorizedItem1 = new HashSet<>();

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "category_item",
			   joinColumns = @JoinColumn(name = "category_id"),
			   inverseJoinColumns = @JoinColumn(name = "user_id"))
	protected Map<Item, User>itemAddedBy = new HashMap<>();
	
	public Category() {
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

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Set<CategorizedItem> getCategorizedItem() {
		return categorizedItem;
	}

	public void setCategorizedItem(Set<CategorizedItem> categorizedItem) {
		this.categorizedItem = categorizedItem;
	}

	public Set<CategorizedItem1> getCategorizedItem1() {
		return categorizedItem1;
	}

	public void setCategorizedItem1(Set<CategorizedItem1> categorizedItem1) {
		this.categorizedItem1 = categorizedItem1;
	}

	public Map<Item, User> getItemAddedBy() {
		return itemAddedBy;
	}

	public void setItemAddedBy(Map<Item, User> itemAddedBy) {
		this.itemAddedBy = itemAddedBy;
	}
	
}
