package caveatemptor.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class CategorizedItem1 {

	@ManyToOne
	@JoinColumn(name = "item_id", nullable = false, updatable = false)
	protected Item item;
	
	protected LocalDateTime addOn = LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	protected User addedBy;
	
	public CategorizedItem1() {
		super();
	}

	public CategorizedItem1(User addedBy, Item item) {
		super();
		this.item = item;
		this.addedBy = addedBy;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public LocalDateTime getAddOn() {
		return addOn;
	}

	public void setAddOn(LocalDateTime addOn) {
		this.addOn = addOn;
	}

	public User getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(User addedBy) {
		this.addedBy = addedBy;
	}
	
	
}
