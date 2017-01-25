package query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Parameter;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import fetch.Item;
import fetch.User;

public class TestQueryParameter {

	public static void main(String[] args) {
		g();
	}
	
	public static void g() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<User> query = em.createQuery("select u from User u", User.class);
		query.setFirstResult(5).setMaxResults(5);
		List<User> list = query.getResultList();
		for (User u : list) {
			System.out.println(u);
		}
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
		
	public static void f() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		for (int i = 5; i <= 20; i++) {
			User u = new User();
			u.setName("user" + i);
			u.setPassword("user" + i);
			u.setBirthday(new Date());
			em.persist(u);
		}
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
	
	public static void e() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery criteria = cb.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		ParameterExpression<String> usernameParameter = cb.parameter(String.class);
		criteria.select(root).where(cb.equal(root.get("name"), usernameParameter));
		Query query = em.createQuery(criteria);
		query.setParameter(usernameParameter, "user1");
		System.out.println(query.getResultList());
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
		
	public static void d() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery criteria = cb.createQuery();
		Root<User> u = criteria.from(User.class);
		criteria.select(u).where(cb.equal(u.get("name"), cb.parameter(String.class, "username")));
		Query query = em.createQuery(criteria);
		query.setParameter("username", "user1");
		System.out.println(query.getResultList());
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
	
	public static void c() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select u from User u where u.name = ?1 and u.birthday = ?2");
		query.setParameter(1, "user1");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			query.setParameter(2, df.parse("2017-1-26"), TemporalType.TIMESTAMP);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(query.getResultList());
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
	
	public static void b() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select i from Item i where i.seller = :user and i.name = :name");
		query.setParameter("user", em.getReference(User.class, 1L));
		query.setParameter("name", "item1");
		List list = query.getResultList();
		System.out.println(list);
		System.out.println("OKKK");
		Item item = em.find(Item.class, 7L);
		System.out.println(item);
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
	
	public static void a() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select u from User u where u.name = :username and u.password = :password");
		query.setParameter("username", "user1");
		query.setParameter("password", "user1");
		System.out.println(query.getResultList());
		query.setParameter("username", "user2");
		System.out.println(query.getResultList());
		for (Parameter<?> parameter : query.getParameters()) {
			System.out.println(parameter + ":" + query.isBound(parameter));
		}
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}

}









