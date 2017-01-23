package fetch;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

import org.hibernate.Hibernate;
import org.hibernate.jpa.AvailableSettings;

import filtering.AuditLogInterceptor;

public class Test {

	public static void main(String[] args) {
		add();
	}
	
	public static void c() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
//		em.getReference(User.class, 1L);
		Item item = em.find(Item.class, 2L);
		System.out.println("OK");
		System.out.println(item.getSeller().getClass());
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
	
	public static void b() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		Item item = em.find(Item.class, 2L);
		item.getImageSet().add("image5");
		
//		Bid bid = new Bid(new BigDecimal("22.22"));
//		item.getBid().add(bid);
//		em.persist(bid);
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}

	public static void a() {
		PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		Item item = em.getReference(Item.class, 2L);
//		Item item = em.find(Item.class, 1L);
//		System.out.println(item.getClass());
//		System.out.println(HibernateProxyHelper.getClassWithoutInitializingProxy(item));
		
		Hibernate.initialize(item);
		System.out.println(item.getSeller().getClass());
//		em.detach(item);
//		em.detach(item.getSeller());
		System.out.println(persistenceUtil.isLoaded(item, "seller"));
//		System.out.println(item.getSeller().getId());
		
//		System.out.println(item.getImageSet().getClass());
//		System.out.println(item.getImageList().getClass());
//		System.out.println(item.getBid().getClass());
		
//		item.getImageSet().iterator().next();
//		item.getImageList().size();
//		item.getImageList().get(0);
//		item.getImageList().get(1);
		
		Bid bid = em.getReference(Bid.class, 3L);
//		item.getBid().isEmpty();
		item.getBid().contains(bid);
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}

	public static void add1() {
//		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
//		EntityManager em = fac.createEntityManager();
//		em.getTransaction().begin();
//		
//		Item item = em.getReference(Item.class, 2L);
//		User user = em.getReference(User.class, 1L);
//		Bid bid = new Bid(new BigDecimal("13.33"));
//		bid.setItem(item);
//		bid.setUser(user);
//		em.persist(bid);
//		
//		em.getTransaction().commit();
//		em.close();
//		fac.close();
	}
	
	public static void add() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		User user1 = new User();
		user1.setName("admin");
		user1.setPassword("admin");
//		em.persist(user1);
		
		Item item = new Item();
		item.setName("item1");
		item.setPrice(12.43);
		item.setDesc1("desc1");
		item.setSeller(user1);
		item.getImageSet().add("image1");
		item.getImageSet().add("image2");
		item.getImageList().add("image3");
		item.getImageList().add("image4");
		em.persist(item);
		
//		Bid bid = new Bid(new BigDecimal("13.33"));
//		item.getBid().add(bid);
//		bid.setItem(item);
//		bid.setUser(user1);
//		em.persist(bid);
		
//		Bid bid1 = new Bid(new BigDecimal("11.11"));
//		item.getBid().add(bid1);
////		bid.setItem(item);
////		bid.setUser(user1);
//		em.persist(bid1);
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}

}
