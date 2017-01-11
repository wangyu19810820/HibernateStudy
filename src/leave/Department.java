package leave;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Department {

	@Id
	protected String departname;

	public Department() {
		super();
	}

	public String getDepartname() {
		return departname;
	}

	public void setDepartname(String departname) {
		this.departname = departname;
	}

	@Override
	public String toString() {
		return "Department [departname=" + departname + "]";
	}

}
