package caveatemptor.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import caveatemptor.model.Address;
import caveatemptor.model.BillingDetails;
import caveatemptor.model.City;
import caveatemptor.model.CreditCard;
import caveatemptor.model.GermanZipcode;
import caveatemptor.model.SwissZipcode;
import caveatemptor.model.User;

public class TestUserBillingDetails {

	public static void main(String[] args) {
		CreditCard cc = new CreditCard();
		cc.setCardNumber("2234123412341234");
		cc.setExpMonth("01");
		cc.setExpYear("2011");
		cc.setOwner("John1");
		
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
		
		Address billingAddress = new Address();
		billingAddress.setCity(nanjing1);

		User user = new User();
		user.setDefaultBilling(cc);
		user.addBillingDetails(cc);
		user.setHomeAddress(homeAddress);
		user.setBillingAddress(billingAddress);
		
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		
//		em.getTransaction().begin();
//		em.persist(cc);
//		em.persist(user);
//		em.getTransaction().commit();
		
//		em.getTransaction().begin();
//		User user1 = em.find(User.class, 2L);
//		user1.getBillingDetails();
//		System.out.println(user1.getBillingDetails());
//		em.getTransaction().commit();
		
		em.getTransaction().begin();
		BillingDetails bd = em.find(BillingDetails.class, 1L);
		System.out.println(bd);
		System.out.println(bd.getUser());
		em.getTransaction().commit();

		em.close();
		fac.close();
	}

}
