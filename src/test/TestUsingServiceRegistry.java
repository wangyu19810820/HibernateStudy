package test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import model.User;

public class TestUsingServiceRegistry {

	public static void main(String[] args) {
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		StandardServiceRegistry service = builder.configure().build();
		Session session = null;
		try {
			MetadataSources resources = new MetadataSources(service);
			Metadata metadata = resources.buildMetadata();
			SessionFactory factory = metadata.buildSessionFactory();
			session = factory.openSession();
			User user = new User();
			user.setUsername("ÀîËÄ");
			user.setPassword("222222");
			user.setBirthday(new Date());
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			StandardServiceRegistryBuilder.destroy(service);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
