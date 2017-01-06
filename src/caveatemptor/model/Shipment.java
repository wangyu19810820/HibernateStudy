package caveatemptor.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Shipment {

	@Id
	@GeneratedValue()
	protected Long id;
	
	@CreationTimestamp
	protected LocalDateTime createOn;

	public Shipment() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreateOn() {
		return createOn;
	}

	public void setCreateOn(LocalDateTime createOn) {
		this.createOn = createOn;
	}

	@Override
	public String toString() {
		return "Shipment [id=" + id + ", createOn=" + createOn + "]";
	}
}
