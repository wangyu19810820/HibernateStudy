package fetch;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import filtering.Auditable;

@Entity
//@EntityListeners(PersistEntityListener2.class)
//@Proxy(lazy = false)
//@BatchSize(size = 2)
public class User{

	@Id
	@GeneratedValue
	protected Long id;

	protected String name;

	protected String password;

	public User() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
//	@PostLoad
//	public void postLoad() {
//		System.out.println("User-PostLoad:" + this);
//	}

}
