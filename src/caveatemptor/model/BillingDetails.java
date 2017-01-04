package caveatemptor.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "BD_TYPE")
//@DiscriminatorFormula("case when cardnumber is not null then 'CC' else 'BA' end")
public abstract class BillingDetails {

    @Id
    @GeneratedValue()
    protected Long id;

    @NotNull
    protected String owner;
    
    @ManyToOne(fetch = FetchType.LAZY)
    protected User user;

    protected BillingDetails() {
    }

    protected BillingDetails(String owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
		this.id = id;
	}

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "BillingDetails [id=" + id + ", owner=" + owner + "]";
	}
}
