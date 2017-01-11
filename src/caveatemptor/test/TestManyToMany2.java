package caveatemptor.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import caveatemptor.model.CategorizedItem1;
import caveatemptor.model.Category;
import caveatemptor.model.Item;
import caveatemptor.model.User;

public class TestManyToMany2 {

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
		
		CategorizedItem1 ci1 = new CategorizedItem1(user1, item1);
		CategorizedItem1 ci2 = new CategorizedItem1(user1, item2);
		CategorizedItem1 ci3 = new CategorizedItem1(user2, item1);
		CategorizedItem1 ci4 = new CategorizedItem1(user2, item2);
		
		c1.getCategorizedItem1().add(ci1);
		c1.getCategorizedItem1().add(ci2);
		c2.getCategorizedItem1().add(ci3);
		c2.getCategorizedItem1().add(ci4);

//		em.persist(c1);
//		em.persist(c2);
		
		c1 = em.find(Category.class, 1L);
		c1.getCategorizedItem1().clear();
		em.persist(c1);
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}

}
