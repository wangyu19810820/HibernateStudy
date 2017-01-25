package filtering;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;

import fetch.User;

public class TestEnvers {

	public static void main(String[] args) {
		e();
	}
	
	public static void e() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		AuditReader auditReader = AuditReaderFactory.get(em);
		User user1 = auditReader.find(User.class, 2L, 1);
		System.out.println(user1);
		em.unwrap(Session.class).replicate(user1, ReplicationMode.OVERWRITE);
		em.flush();
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
	
	public static void d() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		AuditReader auditReader = AuditReaderFactory.get(em);
		AuditQuery query = auditReader.createQuery().forEntitiesAtRevision(User.class, 2);
		query.add(AuditEntity.property("name").like("6", MatchMode.START));
		query.addOrder(AuditEntity.property("name").asc());
		query.addProjection(AuditEntity.property("name"));
		query.setFirstResult(0);
		query.setMaxResults(10);
		List list = query.getResultList();
		System.out.println(list);
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
	
	public static void c() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		AuditReader auditReader = AuditReaderFactory.get(em);
		User user1 = auditReader.find(User.class, 2L, 1);
		User user2 = auditReader.find(User.class, 2L, 2);
		User user3 = auditReader.find(User.class, 2L, 3);
		System.out.println(user1);
		System.out.println(user2);
		System.out.println(user3);
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
	
	public static void b() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		AuditReader auditReader = AuditReaderFactory.get(em);
		AuditQuery query = auditReader.createQuery()
									  .forRevisionsOfEntity(User.class, false, true);
		List<Object[]> result = query.getResultList();
		for (Object[] tuple : result) {
			User user = (User) tuple[0];
			DefaultRevisionEntity revision = (DefaultRevisionEntity)tuple[1];
			RevisionType revisionType = (RevisionType)tuple[2];
			System.out.println(user);
			System.out.println(revision);
			System.out.println(revisionType);
		}
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
	
	public static void a() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		AuditReader auditReader = AuditReaderFactory.get(em);
		
		Number revisionCreate = auditReader.getRevisionNumberForDate(new Date(1485199012482L));
		System.out.println(revisionCreate);
		
		List<Number> userResvisions = auditReader.getRevisions(User.class, 2L);
		System.out.println(userResvisions);
		
		for (Number num : userResvisions) {
			Date d = auditReader.getRevisionDate(num);
			System.out.println("num:" + num + " date:" + d);
		}
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}

}
