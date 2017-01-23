package filtering;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class PersistEntityListener2 {

	@PrePersist
	public void prePersist(Object o) {
		System.out.println("PersistEntityListener2-PrePersist:" + o);
	}
	
	@PostPersist
	public void postPersist(Object o) {
		System.out.println("PersistEntityListener2-PostPersist:" + o);
	}
	
	@PostLoad
	public void postLoad(Object o) {
		System.out.println("PersistEntityListener2-PostLoad:" + o);
	}
	
	@PreUpdate
	public void preUpdate(Object o) {
		System.out.println("PersistEntityListener2-PreUpdate:" + o);
	}
	
	@PostUpdate
	public void postUpdate(Object o) {
		System.out.println("PersistEntityListener2-PostUpdate:" + o);
	}
	
	@PreRemove
	public void preRemove(Object o) {
		System.out.println("PersistEntityListener2-PreRemove:" + o);
	}
	
	@PostRemove
	public void postRemove(Object o) {
		System.out.println("PersistEntityListener2-PostRemove:" + o);
	}
	
}



