package leave;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name = "user_leave")
@SecondaryTable(
	name = "user_other",
	pkJoinColumns = {
		@PrimaryKeyJoinColumn(name = "username"),
		@PrimaryKeyJoinColumn(name = "departname")
	}
)
public class User {

	@Embeddable
	public static class UserId implements Serializable {

		private static final long serialVersionUID = 6952464329520336755L;
		protected String username;
		protected String departname;
		
		public UserId() {
			super();
		}
		
		public UserId(String username, String departname) {
			super();
			this.username = username;
			this.departname = departname;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getDepartname() {
			return departname;
		}

		public void setDepartname(String departname) {
			this.departname = departname;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((departname == null) ? 0 : departname.hashCode());
			result = prime * result + ((username == null) ? 0 : username.hashCode());
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
			UserId other = (UserId) obj;
			if (departname == null) {
				if (other.departname != null)
					return false;
			} else if (!departname.equals(other.departname))
				return false;
			if (username == null) {
				if (other.username != null)
					return false;
			} else if (!username.equals(other.username))
				return false;
			return true;
		}
	}
	
	@EmbeddedId
	protected UserId id;
	
	@ManyToOne
//	@MapsId("departname")
	@JoinColumn(name = "departname", insertable = false, updatable = false)
	protected Department department;
	
	@Column(nullable = false,
			unique = true)
	protected String email;
	
	@Column(table = "user_other")
	protected String sex;
	
	public User() {
		super();
		id = new UserId("", "");
	}

	public UserId getId() {
		return id;
	}

	public void setId(UserId id) {
		this.id = id;
	}

	public String getUsername() {
		return id.username;
	}

	public void setUsername(String username) {
		this.id.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
