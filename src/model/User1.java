package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "SCOTT.USER1")
public class User1 {
	private int id;
	private String username;
	private String password;
	private Date birthday;

	public User1() {
	}

	@Id
	@SequenceGenerator(name="increment", sequenceName="SCOTT.SEQ_USER1" ,allocationSize = 1)
	@GeneratedValue(generator="increment", strategy=GenerationType.SEQUENCE)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotNull()
	@Column(name="username", length=100)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Size(min = 2, max = 6, message = "password is required, 2-6")
	@Column(name="password", length=100)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="birthday")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Override
	public String toString() {
		return "User1 [id=" + id + ", username=" + username + ", password=" + password + ", birthday=" + birthday + "]";
	}

}
