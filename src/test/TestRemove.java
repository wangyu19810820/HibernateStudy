package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.User1;

public class TestRemove {

	public static void main(String[] args) {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();

		User1 u1 = em.find(User1.class, 1);
		em.remove(u1);
		System.out.println(u1.getId());
		em.persist(u1);
		System.out.println(u1.getId());

		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
}
