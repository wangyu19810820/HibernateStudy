package caveatemptor.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import caveatemptor.model.CategorizedItem;
import caveatemptor.model.Category;
import caveatemptor.model.Item;
import caveatemptor.model.User;

public class TestManyToMany1 {

	public static void main(String[] args) {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();

		Category c1 = new Category();
		c1.setName("dress");
		
		Category c2 = new Category();
		c2.setName("china");
		
		Item item1 = new Item();
		item1.setDescription("aaa");
		
		Item item2 = new Item();
		item2.setDescription("bbb");
		
//		em.persist(c1);
//		em.persist(c2);
//		em.persist(item1);
//		em.persist(item2);
		
		User user1 = new User();
		User user2 = new User();
//		em.persist(user1);
//		em.persist(user2);
		
		CategorizedItem ci1 = new CategorizedItem(user1, c1, item1);
		CategorizedItem ci2 = new CategorizedItem(user1, c1, item2);
		CategorizedItem ci3 = new CategorizedItem(user2, c2, item1);
		CategorizedItem ci4 = new CategorizedItem(user2, c2, item2);
				
//		em.persist(ci1);
//		em.persist(ci2);
//		em.persist(ci3);
//		em.persist(ci4);
		
		Category c3 = em.find(Category.class, 6L);
		for (CategorizedItem c : c3.getCategorizedItem()) {
			System.out.println(c);
			em.remove(c);
		}
//		em.remove(c3);
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}

}
