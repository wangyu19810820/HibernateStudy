package caveatemptor.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import caveatemptor.model.Category;
import caveatemptor.model.Item;
import caveatemptor.model.User;

public class TestMapKey1 {

	public static void main(String[] args) {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();

//		Category c1 = new Category();
//		c1.setName("dress");
//		
//		Category c2 = new Category();
//		c2.setName("china");
//		
//		Item item1 = new Item();
//		item1.setDescription("aaa");
//		
//		Item item2 = new Item();
//		item2.setDescription("bbb");
//		
//		User user1 = new User();
//		User user2 = new User();
//		
//		em.persist(item1);
//		em.persist(item2);
//		em.persist(user1);
//		em.persist(user2);
//		c1.getItemAddedBy().put(item1, user1);
//		c1.getItemAddedBy().put(item2, user1);
//		c2.getItemAddedBy().put(item1, user2);
//		c2.getItemAddedBy().put(item2, user2);
//		
//		em.persist(c1);
//		em.persist(c2);

		Category c = em.find(Category.class, 9L);
		Item i = em.find(Item.class, 5L);
		c.getItemAddedBy().remove(i);
		em.persist(c);
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}

}
