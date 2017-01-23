package fetch;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.validation.constraints.NotNull;

@NamedEntityGraph(
	name = "BidItem",
	attributeNodes = {
		@NamedAttributeNode(
			value = "item",
			subgraph = "ItemSellerBid"
		)
	},
	subgraphs = {
		@NamedSubgraph(
			name = "ItemSellerBid",
			attributeNodes = {
				@NamedAttributeNode("seller"),
				@NamedAttributeNode("bid")
			}
		)
	}
)

@Entity
public class Bid {

	@Id
	@GeneratedValue
	protected Long id;
	
    @NotNull
    protected BigDecimal amount;
    
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    protected User user;
//    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    protected Item item;

    protected Bid() {
    }

    public Bid(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmount() {
        return amount;
    }

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	public Item getItem() {
//		return item;
//	}
//
//	public void setItem(Item item) {
//		this.item = item;
//	}
}