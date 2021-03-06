package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "User1")
@Table(name = "USER1")
//@DynamicUpdate
public class User1 {
	
//	@GenericGenerator(name = "uuid", strategy = "uuid2")
//	@GeneratedValue(generator = "uuid")
	@Id
	@SequenceGenerator(name="increment", sequenceName="SCOTT.SEQ_USER1" ,allocationSize = 1)
	@GeneratedValue(generator="increment", strategy=GenerationType.SEQUENCE)
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Basic(optional = false)
//	@ColumnTransformer(
//		read = "substr(username, 3, length(username) - 2)",
//		write = "concat('u_', ?)"
//	)
	private String username;
	
	@ColumnDefault("111111")
	private String password;
	
	@Formula("concat(username, password)")
	@Generated(GenerationTime.ALWAYS)
	private String namePwd;
	
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="birthday", insertable=false, updatable=false)
	@Transient
	private LocalDateTime birthday;
	
	@CreationTimestamp
	private LocalDateTime insertTime;
	
	@UpdateTimestamp
	private LocalDateTime updateTime;
	
//	@Enumerated(EnumType.STRING)
	private PersonType personType;
	
	private List<Address> addressList = new ArrayList<Address>();

//	private Date 
	public User1() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

//	@Size(min = 2, max = 6, message = "password is required, 2-6")
//	@NotNull()
//	@Access(AccessType.PROPERTY)
//	@Column(name="password", length=100, nullable=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDateTime birthday) {
		this.birthday = birthday;
	}
	
	public String getNamePwd() {
		return namePwd;
	}

	public void setNamePwd(String namePwd) {
		this.namePwd = namePwd;
	}

	public LocalDateTime getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(LocalDateTime insertTime) {
		this.insertTime = insertTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public PersonType getPersonType() {
		return personType;
	}

	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	@Override
	public String toString() {
		return "User1 [id=" + id + ", username=" + username + ", password=" + password + ", namePwd=" + namePwd
				+ ", birthday=" + birthday + ", insertTime=" + insertTime + ", updateTime=" + updateTime
				+ ", personType=" + personType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User1)) {
			return false;
		}
		User1 other = (User1) obj;
		if (getUsername() == null) {
			if (other.getUsername() != null)
				return false;
		} else if (!getUsername().equals(other.getUsername()))
			return false;
		return true;
	}

}
