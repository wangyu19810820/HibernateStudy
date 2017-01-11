package caveatemptor.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import caveatemptor.model.Category;
import caveatemptor.model.Item;

public class TestManyToMany {

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
		
		c1.getItems().add(item1);
		c1.getItems().add(item2);
		
		c2.getItems().add(item1);
		c2.getItems().add(item1);
		c2.getItems().add(item2);
		
		item1.getCategories().add(c1);
		item1.getCategories().add(c2);
		
		em.persist(c1);
		em.persist(c2);
		
//		c1 = em.find(Category.class, 2L);
//		Item item3 = c1.getItems().iterator().next();
//		item3.setDescription("ccc");
//		em.persist(c1);
//		em.remove(c1);
		
//		c2 = em.find(Category.class, 8L);
//		c2.getItems().clear();
//		em.remove(c2);
		
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}

}
