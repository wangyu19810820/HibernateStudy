package model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect(
		value = "select u.id as userId, u.username as username, a.city as city " 
				+ "from CE_User1 u left outer join CE_Address a on u.id = a.id"
)
public class UserAddress {

	@Id
	protected Long userId;
	protected String username;
	protected String city;

	public UserAddress() {
		super();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "UserAddress [userId=" + userId + ", username=" + username + ", city=" + city + "]";
	}

}
