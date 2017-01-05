package caveatemptor.test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import caveatemptor.model.Item;

public class TestCollection {

	public static void main(String[] args) {
		testSortMap();
	}

	public static void testSortMap() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		
		Item item = new Item();
		Map<String, String> map = item.getImage5();
		map.put("file1.jpg", "file1");
		map.put("file3.jpg", "file3");
		map.put("file2.jpg", "file2");
		
		em.getTransaction().begin();
		em.persist(item);
		em.getTransaction().commit();
		
//		em.getTransaction().begin();
//		Item item = em.find(Item.class, 1L);
//		System.out.println(item.getImage5());
//		em.getTransaction().commit();
		
		em.close();
		fac.close();
	}
	
	public static void testMap() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		
		Item item = new Item();
		Map<String, String> map = item.getImage4();
		map.put("file1.jpg", "file1");
		map.put("file2.jpg", "file2");
		map.put("file3.jpg", "file3");
		
		em.getTransaction().begin();
		em.persist(item);
		em.getTransaction().commit();
		
		em.close();
		fac.close();
	}
	
	public static void testList() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		
//		Item item = new Item();
//		List<String> list = item.getImage3();
//		list.add("file1.jpg");
//		list.add("file2.jpg");
//		list.add("file3.jpg");
//		list.add("file1.jpg");
//		
//		em.getTransaction().begin();
//		em.persist(item);
//		em.getTransaction().commit();
		
		em.getTransaction().begin();
		Item item1 = em.find(Item.class, 1L);
//		item.getImage3().remove("file1.jpg");
//		item.getImage3().remove("file2.jpg");
//		item.getImage3().remove("file3.jpg");
//		em.persist(item);
		em.getTransaction().commit();
		List<String> list = item1.getImage3();
		for (String s : list) {
			System.out.println(s);
		}

		em.close();
		fac.close();
	}
	
	public static void testCollection() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		
		Item item = new Item();
		Collection<String> collection = item.getImage2();
		collection.add("file1.jpg");
		collection.add("file2.jpg");
		collection.add("file3.jpg");
		collection.add("file1.jpg");
		
		em.getTransaction().begin();
		em.persist(item);
		em.getTransaction().commit();
		
//		em.getTransaction().begin();
//		Item item = em.find(Item.class, 1L);
//		item.getImage2().remove("file2.jpg");
//		em.persist(item);
//		em.getTransaction().commit();
//		System.out.println(item.getImage2());
		
		em.close();
		fac.close();
	}
	
	public static void testSet() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		
		Item item = new Item();
		Set<String> set = item.getImage1();
		set.add("file1.jpg");
		set.add("file3.jpg");
		set.add("file2.jpg");
		
		em.getTransaction().begin();
		em.persist(item);
		em.getTransaction().commit();
		
//		em.getTransaction().begin();
//		Item item = em.find(Item.class, 3L);
//		System.out.println(item.getImage1());
//		em.getTransaction().commit();
		
		em.close();
		fac.close();
	}
}
