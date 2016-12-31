package test;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import model.User1;

public class TestUsingAnnotation {

	public static void main(String[] args) {
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		StandardServiceRegistry registry = builder.configure().build();
		SessionFactory fac = null;
		Session session = null;
		try {
			MetadataSources sources = new MetadataSources(registry);
			Metadata metadata = sources.buildMetadata();
			fac = metadata.buildSessionFactory();
			session = fac.openSession();
			User1 user = new User1();
			user.setUsername("aa4");
			user.setPassword("333");
			user.setBirthday(LocalDateTime.now());
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			
//			List<User1> list = session.createQuery("from User1").list();
//			System.out.println(list);
//			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
			if (fac != null) {
				fac.close();
			}
		}
	}

}
