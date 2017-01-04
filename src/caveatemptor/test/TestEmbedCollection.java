package caveatemptor.test;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import caveatemptor.model.Address;
import caveatemptor.model.City;
import caveatemptor.model.Filename;
import caveatemptor.model.GermanZipcode;
import caveatemptor.model.Image;
import caveatemptor.model.Item;
import caveatemptor.model.SwissZipcode;
import caveatemptor.model.User;

public class TestEmbedCollection {

	public static void main(String[] args) {
		testAddress();
	}
	
	public static void testAddress() {
		City nanjing = new City();
		nanjing.setCountry("china");
		nanjing.setName("nanjing");
		nanjing.setZipcode(new GermanZipcode("12345"));

		City nanjing1 = new City();
		nanjing1.setCountry("china");
		nanjing1.setName("nanjing");
		nanjing1.setZipcode(new SwissZipcode("1234"));

		Address homeAddress = new Address();
		homeAddress.setStreet("yuhua");
		homeAddress.setCity(nanjing);
		homeAddress.getContacts().add("contact1");
		homeAddress.getContacts().add("contact2");
		
		Address billingAddress = new Address();
		billingAddress.setCity(nanjing1);
		
		User user1 = new User();
		user1.setHomeAddress(homeAddress);
		user1.setBillingAddress(billingAddress);
		
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager manager = fac.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(user1);
		                                                                                         
		manager.getTransaction().commit();
		manager.getTransaction().begin();
		
		List<User> list = manager.createQuery("from Users", User.class).getResultList();
		list.forEach(System.out::println);

//		List<UserAddress> list = manager.createQuery("from UserAddress", UserAddress.class).getResultList();
//		list.forEach(System.out::println);

		manager.getTransaction().commit();
		manager.close();
		fac.close();
	}
	
	public static void testItem() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();

		Image image1 = new Image("Foo", 640, 480);
		Image image2 = new Image("Bar", 800, 600);
		Image image3 = new Image("Baz", 1024, 768);
		Image image4 = new Image("Baz", 1024, 768);
		
		Filename file1 = new Filename("foo", "jpg");
		Filename file2 = new Filename("bar", "jpg");
		Filename file3 = new Filename("baz", "jpg");
		Filename file4 = new Filename("baz", "jpg");
		
		Item item = new Item();
//		Collection<Image> set = item.getImage6();
//		set.add(image1);
//		set.add(image2);
//		set.add(image3);
//		set.add(image4);
		
		Map<Filename, Image> map = item.getImage7();
		map.put(file1, image1);
		map.put(file2, image2);
		map.put(file3, image3);
		map.put(file4, image4);
		
		em.getTransaction().begin();
		em.persist(item);
		em.getTransaction().commit();
		
		em.close();
		fac.close();
	}

}
