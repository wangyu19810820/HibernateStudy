package caveatemptor.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import caveatemptor.model.Address;
import caveatemptor.model.City;
import caveatemptor.model.User;


public class TestEmbedEntity {

	public static void main(String[] args) {
		City nanjing = new City();
		nanjing.setCountry("china");
		nanjing.setName("nanjing");
		nanjing.setZipcode("123456");
		
		Address homeAddress = new Address();
		homeAddress.setStreet("yuhua");
		homeAddress.setCity(nanjing);
		
		Address billingAddress = new Address();
		billingAddress.setCity(nanjing);
		
		User user1 = new User();
		user1.setHomeAddress(homeAddress);
		user1.setBillingAddress(billingAddress);
		
//		User user = new User();
//		user.setUsername("aa");
//		user.setPassword("1");
//		user.setBirthday(new Date());

		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager manager = fac.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(user1);
		                                                                                         
		List<User> list = manager.createQuery("from Users", User.class).getResultList();
		list.forEach(System.out::println);

//		List<UserAddress> list = manager.createQuery("from UserAddress", UserAddress.class).getResultList();
//		list.forEach(System.out::println);

		manager.getTransaction().commit();
		manager.close();
		fac.close();
	}
}
