package test;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.User;
import model.User1;

public class TestJPA {

	public static void main(String[] args) {
		User1 user1 = new User1();
		user1.setUsername("bb");
		user1.setPassword("22");
		user1.setBirthday(LocalDateTime.now());
//		User user = new User();
//		user.setUsername("aa");
//		user.setPassword("1");
//		user.setBirthday(new Date());

		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa");
		EntityManager manager = fac.createEntityManager();
		manager.getTransaction().begin();
//		manager.persist(user);
		manager.persist(user1);
//		List<User1> list = manager.createQuery("from User1", User1.class).getResultList();
//		list.forEach(System.out::println);
		manager.getTransaction().commit();
		manager.close();
		fac.close();
	}

}
