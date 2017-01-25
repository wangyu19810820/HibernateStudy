package filtering;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Filter;
import org.hibernate.Session;

import fetch.Category;
import fetch.Item;
import fetch.User;

public class TestFilter {

	public static void main(String[] args) {
		test();
	}
	
	public static void test() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		Filter filter = em.unwrap(Session.class).enableFilter("limitByUserRank");
		filter.setParameter("currentUserRank", 3);
		
		List<Item> list = em.createQuery("from Item").getResultList();
		for (Item i : list) {
			System.out.println(i);
		}
		
//		Item item = em.find(Item.class, 11L);
//		System.out.println(item);
		
//		Category category = em.find(Category.class, 12L);
//		System.out.println(category);
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
	
	public static void add2() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		Item item = em.find(Item.class, 7L);
		User user = em.find(User.class, 1L);
		item.setSeller(user);
		
		Item item1 = em.find(Item.class, 8L);
		User user1 = em.find(User.class, 2L);
		item1.setSeller(user1);
		
		Item item2 = em.find(Item.class, 11L);
		User user2 = em.find(User.class, 4L);
		item2.setSeller(user2);
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
	
	public static void add1() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		User user = em.find(User.class, 3L);
		
		Item item = new Item();
		item.setName("item3");
		item.setPrice(32.43);
		item.setDesc1("desc3");
		item.setSeller(user);
		item.getImageSet().add("image1");
		item.getImageSet().add("image2");
		item.getImageList().add("image3");
		item.getImageList().add("image4");
		em.persist(item);
		
		Item item2 = new Item();
		item2.setName("item4");
		item2.setPrice(42.43);
		item2.setDesc1("desc4");
		item2.setSeller(user);
		item2.getImageSet().add("image1");
		item2.getImageSet().add("image2");
		item2.getImageList().add("image3");
		item2.getImageList().add("image4");
		em.persist(item2);

		Category category = new Category();
		category.setName("category2");
		category.getItem().add(item);
		category.getItem().add(item2);
		em.persist(category);

		em.getTransaction().commit();
		em.close();
		fac.close();
	}
	
	public static void add() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();

		User user1 = new User();
		user1.setName("user1");
		user1.setPassword("user1");
		user1.setRank(1);
		em.persist(user1);
		
		User user2 = new User();
		user2.setName("user2");
		user2.setPassword("user2");
		user2.setRank(2);
		em.persist(user2);
		
		User user3 = new User();
		user3.setName("user3");
		user3.setPassword("user3");
		user3.setRank(3);
		em.persist(user3);
		
		User user4 = new User();
		user4.setName("user4");
		user4.setPassword("user4");
		user4.setRank(4);
		em.persist(user4);

		em.getTransaction().commit();
		em.close();
		fac.close();
	}
}
