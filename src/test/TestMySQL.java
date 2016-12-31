package test;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.PersonType;
import model.User1;
import model.UserAddress;

public class TestMySQL {

	public static void main(String[] args) {
		User1 user1 = new User1();
		user1.setUsername("aaa");
		user1.setPassword("222");
		user1.setBirthday(LocalDateTime.now());
		user1.setPersonType(PersonType.YELLOW);
//		User user = new User();
//		user.setUsername("aa");
//		user.setPassword("1");
//		user.setBirthday(new Date());

		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager manager = fac.createEntityManager();
		manager.getTransaction().begin();
//		manager.persist(user);
		manager.persist(user1);
		                                                                                         
		List<User1> list = manager.createQuery("from User1", User1.class).getResultList();
		list.forEach(System.out::println);

//		List<UserAddress> list = manager.createQuery("from UserAddress", UserAddress.class).getResultList();
//		list.forEach(System.out::println);

		manager.getTransaction().commit();
		manager.close();
		fac.close();
	}
}
