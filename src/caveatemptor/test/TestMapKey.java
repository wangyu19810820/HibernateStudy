package caveatemptor.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import caveatemptor.model.Bid;
import caveatemptor.model.Item;

public class TestMapKey {

	public static void main(String[] args) {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();

		Item item = new Item();
		item.setDescription("abcd");
		
		Bid bid1 = new Bid();
		bid1.setAmount(11.11);
		bid1.setItem(item);
		
		Bid bid2 = new Bid();
		bid2.setAmount(22.22);
		bid2.setItem(item);
		
		em.persist(bid1);
		em.persist(bid2);
		
//		Item item1 = em.find(Item.class, 16L);
//		System.out.println(item1.getBidsMap());

		em.getTransaction().commit();
		em.close();
		fac.close();
	}
}
