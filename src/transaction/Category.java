package transaction;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

@Entity
public class Category {

	@Id
	@GeneratedValue
	protected Long id;
	
	protected String name;
	
	@Version
	protected long version;
	
	public Category() {
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

	public long getVersion() {
		return version;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", version=" + version + "]";
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
