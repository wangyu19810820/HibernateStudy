package caveatemptor.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import caveatemptor.model.AddressEntity;
import caveatemptor.model.User;

public class TestOneToOne {

	public static void main(String[] args) {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		
		AddressEntity address = new AddressEntity();
		address.setCity("nanjing");
		address.setStreet("yuhua");
		address.setZipcode("123456");
		
		User user = new User();
		user.setAddressEntity(address);
//		address.setUser(user);
		
		em.getTransaction().begin();
//		em.persist(user);
//		em.persist(address);
		
//		em.createQuery("from Users").getResultList();
		
//		User user1 = em.find(User.class, 2L);
//		System.out.println(user1.getAddressEntity());
		AddressEntity address1 = em.find(AddressEntity.class, 2L);
		
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
}
