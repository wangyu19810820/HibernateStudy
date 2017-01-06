package caveatemptor.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import caveatemptor.model.Address;
import caveatemptor.model.City;
import caveatemptor.model.Shipment;
import caveatemptor.model.SwissZipcode;
import caveatemptor.model.User;
import caveatemptor.model.Zipcode;

public class TestManyToOne2 {

	public static void main(String[] args) {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();

		User user = new User();
		Address address = new Address();
		address.setStreet("yuhua");
		City city = new City();
		city.setCountry("china");
		city.setName("nanjing");
		Zipcode zipcode = new SwissZipcode("12345");
		city.setZipcode(zipcode);
		address.setCity(city);
		user.setBillingAddress(address);
		System.out.println(zipcode);
		System.out.println(city);
		
		Shipment shipment1 = new Shipment();
		Shipment shipment2 = new Shipment();
		address.getDeliveries().add(shipment1);
		address.getDeliveries().add(shipment2);
		
		em.persist(user);
//		em.persist(shipment);
		
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}

}
