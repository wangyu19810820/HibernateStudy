package caveatemptor.test;

import java.math.BigDecimal;
import java.util.Currency;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import caveatemptor.model.Item;
import caveatemptor.model.MonetaryAmount;

public class TestConvert {

	public static void main(String[] args) {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		
		Item item = new Item();
		item.setBuyNowPrice(new MonetaryAmount(new BigDecimal("113.32"), Currency.getInstance("EUR")));
		item.setInitialPrice(new MonetaryAmount(new BigDecimal("1"), Currency.getInstance("USD")));
		em.getTransaction().begin();
		em.persist(item);
		em.getTransaction().commit();
		
		em.close();
		fac.close();
	}
}
