package fetch;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Filter;

@Entity
public class Category {

	@Id
	@GeneratedValue
	protected Long id;
	
	protected String name;
	
	@OneToMany
	@JoinColumn(name = "category_id")
	@Filter(name = "limitByUserRank", 
			condition = ":currentUserRank >= (select u.rank from ce_user u where u.id = seller_id)")
//	@Filter(name = "limitByUserRank")
	protected Set<Item> item = new HashSet<>();

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

	public Set<Item> getItem() {
		return item;
	}

	public void setItem(Set<Item> item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", item=" + item + "]";
	}
	
	
}
