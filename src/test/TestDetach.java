package test;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

import model.User1;

public class TestDetach {

	public static void main(String[] args) {
		b();
	}
	
	public static void b() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		User1 u1 = em.find(User1.class, 1);
		System.out.println(u1);
		u1.setUsername("111");
		em.detach(u1);
		u1.setUsername("222");
		
//		User1 u1 = new User1();
//		u1.setId(2);
//		u1.setUsername("33");
//		
		User1 u2 = em.merge(u1);
//		System.out.println(u2.getUsername());
		
//		em.unwrap(Session.class).saveOrUpdate(u1);
//		System.out.println(u1.getUsername());
		
		em.getTransaction().commit();
//		em.getTransaction().rollback();
		em.close();
		fac.close();
	}
	
	public static void a() {
		Set<User1> set = new HashSet<>();
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
//		User1 u1 = em.find(User1.class, 2);
//		em.detach(u1);
		User1 u1 = new User1();
		u1.setUsername("hh");
		User1 u2 = em.find(User1.class, 3);
		User1 u3 = em.find(User1.class, 2);
		System.out.println(u1 == u3);
		System.out.println(u1.equals(u3));
		set.add(u1);
		set.add(u2);
		set.add(u3);
		System.out.println(set.size());

		em.getTransaction().commit();
		em.close();
		
		EntityManager em1 = fac.createEntityManager();
		em1.getTransaction().begin();
		
		User1 u4 = em1.find(User1.class, 2);
		set.add(u4);
		System.out.println(set.size());

		em1.getTransaction().commit();
		em1.close();
		
		fac.close();
	}
}
