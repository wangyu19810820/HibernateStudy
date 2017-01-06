package caveatemptor.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import caveatemptor.model.Bid;
import caveatemptor.model.Item;

public class TestManyToOne1 {

	public static void main(String[] args) {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		Item item = new Item();
		item.setDescription("aaa");
		
//		item = em.find(Item.class, 1L);
		
		Bid bid1 = new Bid();
		bid1.setAmount(11.23);
		bid1.setItem(item);
		
		Bid bid2 = new Bid();
		bid2.setAmount(11.23);
		bid2.setItem(item);

		Bid bid3 = new Bid();
		bid3.setAmount(11.23);
		bid3.setItem(item);

		item.getBids().add(bid1);
		
		item.getBids().add(bid2);
		item.getBids().add(bid3);

		em.persist(item);

//		item.setDescription("bbb");
//		em.persist(bid1);
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}

}
