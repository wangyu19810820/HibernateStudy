package caveatemptor.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "category_item_1")
@Immutable
public class CategorizedItem {

	@Embeddable
	public static class Id implements Serializable {

		private static final long serialVersionUID = 4728428055607714853L;

		@Column(name = "category_id")
		protected Long categoryId;
		
		@Column(name = "item_id")
		protected Long itemId;

		public Id() {
			super();
		}

		public Id(Long categoryId, Long itemId) {
			super();
			this.categoryId = categoryId;
			this.itemId = itemId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
			result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Id other = (Id) obj;
			if (categoryId == null) {
				if (other.categoryId != null)
					return false;
			} else if (!categoryId.equals(other.categoryId))
				return false;
			if (itemId == null) {
				if (other.itemId != null)
					return false;
			} else if (!itemId.equals(other.itemId))
				return false;
			return true;
		}

	}
	
	@EmbeddedId
	protected Id id = new Id();
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	protected User addedBy;
	
	protected LocalDateTime addedOn = LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name = "category_id", insertable = false, updatable = false)
	protected Category category;
	
	@ManyToOne
	@JoinColumn(name = "item_id", insertable = false, updatable = false)
	protected Item item;
	
	public CategorizedItem() {
		super();
	}

	public CategorizedItem(User addedBy, Category category, Item item) {
		this.addedBy = addedBy;
		this.category = category;
		this.item = item;
		
		this.id.categoryId = category.getId();
		this.id.itemId = item.getId();
		
//		System.out.println("okkkkkkkkkkkkkkkk" + this.id.categoryId + " " + this.id.itemId);
		
		category.getCategorizedItem().add(this);
		item.getCategorizedItems().add(this);
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
