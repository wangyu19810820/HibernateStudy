package query;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import fetch.User;

public class TestCriteriaQuery {

	public static void main(String[] args) {
		h1();
	}
	
	public static void c() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> i = criteria.from(User.class);
		criteria.select(i).where(cb.equal(i.get("id"), 1L));
		TypedQuery<User> query = em.createQuery(criteria);
		System.out.println(query.getSingleResult());
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
	
	public static void b() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createNativeQuery("select * from ce_user", User.class);
		List<User> list = query.getResultList();
		System.out.println(list);
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
	
	public static void a() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
//		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaBuilder cb = fac.getCriteriaBuilder();
		CriteriaQuery criteria = cb.createQuery();
		criteria.select(criteria.from(User.class));
		Query query = em.createQuery(criteria);
		List<User> list = query.getResultList();
		System.out.println(list);
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
	
	public static void h1() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
//		Session session = em.unwrap(Session.class);
//		org.hibernate.Query query = session.createQuery("select u from User u");
//		org.hibernate.SQLQuery query1 = session.createSQLQuery("select {i.*} from ce_user {i}")
//											   .addEntity("i", User.class);
//		System.out.println(query1.getResultList());
		
//		org.hibernate.Criteria query = session.createCriteria(User.class);
//		query.add(org.hibernate.criterion.Restrictions.eq("id", 1L));
//		System.out.println(query.uniqueResult());
		
		Query query = em.createQuery("select u from User u");
		org.hibernate.Query hibernateQuery = query.unwrap(org.hibernate.query.internal.QueryImpl.class);
		System.out.println(hibernateQuery.getQueryString());
		System.out.println(Arrays.toString(hibernateQuery.getReturnAliases()));
		System.out.println(hibernateQuery.getResultList());
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
}





