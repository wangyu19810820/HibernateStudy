package caveatemptor.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import caveatemptor.model.Bid;
import caveatemptor.model.Item;

public class TestManyToOne {

	public static void main(String[] args) {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();

		Item item = new Item();
		item.setDescription("abcd");
		
		Bid bid1 = new Bid();
		bid1.setAmount(11.11);
		bid1.setItem(item);
		item.getBids().add(bid1);
		
		Bid bid2 = new Bid();
		bid2.setAmount(22.22);
		bid2.setItem(item);
		item.getBids().add(bid2);
		
		em.getTransaction().begin();
		em.persist(item);
		em.persist(bid1);
		em.getTransaction().commit();
		
//		em.getTransaction().begin();
//		Bid bid = em.find(Bid.class, 2L);
//		System.out.println(bid);
//		System.out.println(bid.getItem());
		
//		Item item1 = em.find(Item.class, 1L);
//		System.out.println(item1);
//		System.out.println("OKKKKKKKKKKK");
//		System.out.println(item1.getBids());
//		em.getTransaction().commit();
		
//		em.getTransaction().begin();
//		Item item1 = em.find(Item.class, 1L);
//		item1.getBids().clear();
//		em.persist(item1);
//		em.getTransaction().commit();
		
//		em.getTransaction().begin();
//		Item item1 = em.find(Item.class, 1L);
//		em.remove(item1);
//		Bid bid3 = em.find(Bid.class, 5L);
//		em.remove(bid3);
//		em.getTransaction().commit();
		
		em.close();
		fac.close();
	}
}
