package test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.User;

public class TestRead {

	public static void main(String[] args) {
		Configuration con = new Configuration().configure();
		SessionFactory fac = con.buildSessionFactory();
		Session session = null;
		try {
			session = fac.openSession();
			session.beginTransaction();
			List<User> list = session.createQuery("from User").list();
			list.forEach(System.out::println);
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
		}
	}

}
