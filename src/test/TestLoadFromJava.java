package test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import model.User1;

public class TestLoadFromJava {

	public static void main(String[] args) {
		User1 user = new User1();
		user.setUsername("eee");
		user.setPassword("666");
		user.setBirthday(new Date());

		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		builder.applySetting("hibernate.connection.driver_class", "oracle.jdbc.OracleDriver")
			   .applySetting("hibernate.connection.url", "jdbc:oracle:thin:@localhost:1521:orcl")
			   .applySetting("hibernate.connection.username", "scott")
			   .applySetting("hibernate.connection.password", "tiger")
			   .applySetting("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		ServiceRegistry registry = builder.build();
		MetadataSources sources = new MetadataSources(registry);
		sources.addAnnotatedClass(model.User1.class);
		Metadata metadata = sources.buildMetadata();
		SessionFactory fac = null;
		Session session = null;
		try {
			fac = metadata.buildSessionFactory();
			session = fac.openSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
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
