package leave;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Check;

@Entity
@Check(constraints = "startDate < endDate")
@Table(
	name = "item1",
	uniqueConstraints = @UniqueConstraint(name = "uq_date", columnNames = {"startDate", "endDate"})	,
	indexes = {
		@Index(name = "IDX_STARTDATE", columnList = "startDate")
	}
)
public class Item {

	@Id
	@GeneratedValue
	protected Long id;
	
	protected LocalDate startDate;
	
	protected LocalDate endDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "user_id", 
//				foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
//	@JoinColumns({
//		@JoinColumn(name = "sell_username", referencedColumnName = "username"),
//		@JoinColumn(name = "sell_depart", referencedColumnName = "departname")
//	})
	@JoinColumn(name = "seller", referencedColumnName = "email")
	protected User user;

	public Item() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
}
