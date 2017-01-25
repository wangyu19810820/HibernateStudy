package fetch;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
//@Audited
//@EntityListeners(PersistEntityListener2.class)
//@Proxy(lazy = false)
//@BatchSize(size = 2)
public class User{

	@Id
	@GeneratedValue
	protected Long id;

	protected String name;

//	@NotAudited
	protected String password;
	
	protected Date birthday;
	
	protected int rank;

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
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", birthday=" + birthday + ", rank="
				+ rank + "]";
	}

//	@PostLoad
//	public void postLoad() {
//		System.out.println("User-PostLoad:" + this);
//	}

}
