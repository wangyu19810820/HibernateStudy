package caveatemptor.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import caveatemptor.model.BankAccount;
import caveatemptor.model.BillingDetails;
import caveatemptor.model.CreditCard;

public class TestInheritance {

	public static void main(String[] args) {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		
		CreditCard cc = new CreditCard();
		cc.setCardNumber("cc1234");
		cc.setExpMonth("09");
		cc.setExpYear("2020");
		cc.setOwner("a");
		
		BankAccount ba = new BankAccount();
		ba.setOwner("a");
		ba.setAccount("ba1234");
		ba.setBankname("bank1");
		ba.setSwift("swift");
		
		em.getTransaction().begin();
		em.persist(cc);
		em.persist(ba);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		List<BillingDetails> list = em.createQuery("select bd from BillingDetails bd").getResultList();
		em.getTransaction().commit();
		System.out.println(list);
		
		em.close();
		fac.close();
	}

}
