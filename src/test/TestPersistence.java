package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.annotations.QueryHints;

import model.User1;

public class TestPersistence {

	public static void main(String[] args) {
		b();
	}
	
	public static void b() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		em.setFlushMode(FlushModeType.COMMIT);
//		User1 u1 = em.find(User1.class, 2);
//		u1.setUsername("hh");
		User1 u1 = new User1();
		u1.setUsername("aa");
		em.persist(u1);
		
		System.out.println("OKKK");
		User1 u2 = em.find(User1.class, 6);
		System.out.println(u2);
		TypedQuery<User1> q = em.createQuery("select u from User1 u", User1.class);
		System.out.println(q.getResultList());
		System.out.println("OKKK");
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
	
	public static void a() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
//		User1 user1 = new User1();
//		user1.setUsername("aaabbb");
//		em.persist(user1);
		
		User1 user1 = em.find(User1.class, 2);
//		User1 user1 = em.getReference(User1.class, 1);
//		Hibernate.initialize(user1);
		
//		Department department = new Department();
//		department.setDepartname("depart2");
//		User user = new User();
//		user.getId().setUsername("user3");
////		user.setDepartment(department);
//		user.getId().setDepartname(department.getDepartname());
//		user.setEmail("e2d@d.com");
//		user.setSex("ÄÐ");
//		em.persist(department);
//		em.persist(user);

		System.out.println(user1.getId());
//		em.detach(user1); 
//		em.unwrap(Session.class).setReadOnly(user1, true);
//		user1.setUsername("555");
//		em.flush();
//		user1.setUsername("666");
//		System.out.println(user1.getUsername());
		
//		org.hibernate.Query query = em.unwrap(Session.class).createQuery("select u from User1 u");
//		query.setReadOnly(true);
//		List<User1> list = query.list();
//		for (User1 u : list) {
//			u.setUsername("bb");
//		}
//		em.flush();
//		for (User1 u : list) {
//			System.out.println(u.getUsername());
//		}
		
		Query query = em.createQuery("select u from User1 u", User1.class).setHint(QueryHints.READ_ONLY, true);
		for (Object o : query.getResultList()) {
			User1 u = (User1)o;
			u.setUsername("cc");
			System.out.println(u);
		}
		em.flush();
		
//		System.out.println(user1.getUsername());
//		System.out.println(em.contains(user1));
//		PersistenceUnitUtil util = fac.getPersistenceUnitUtil();
//		System.out.println(util.isLoaded(user1));
//		System.out.println(util.getIdentifier(user1));
		
		
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
}
