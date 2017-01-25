package filtering;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;

import fetch.Item;
import fetch.User;

public class Test {

	public static void main(String[] args) {
		b();
	}
	
	public static void b() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.setFlushMode(FlushModeType.COMMIT);
		em.getTransaction().begin();
		
		System.out.println("1");
		User user1 = em.find(User.class, 2L);
		user1.setName("66");
		
		Item item2 = em.find(Item.class, 1L);
		item2.setName("66");
//		System.out.println("3");
//		em.createQuery("from User").getResultList();
		
		em.remove(user1);
		em.remove(item2);
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
	
	public static void a() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		PersistenceUnitUtil util = fac.getPersistenceUnitUtil();
		
		User user1 = new User();
		user1.setName("admin3");
		user1.setPassword("admin3");
		user1.setId(55L);
//		em.persist(user1);
		
		em.getTransaction().begin();

		em.merge(user1);
//		em.detach(user2);
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}

}



