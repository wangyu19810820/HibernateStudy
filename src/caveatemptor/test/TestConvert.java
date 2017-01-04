package caveatemptor.test;

import java.math.BigDecimal;
import java.util.Currency;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import caveatemptor.model.Item;
import caveatemptor.model.MonetaryAmount;
import caveatemptor.model.measure.Dimensions;
import caveatemptor.model.measure.Weight;

public class TestConvert {

	public static void main(String[] args) {
		Dimensions dimensions = new Dimensions();
		dimensions.setName("size");
		dimensions.setSymbol("inch");
		dimensions.setDepth(new BigDecimal(2));
		dimensions.setWidth(new BigDecimal(5.4));
		dimensions.setHeight(new BigDecimal(19.2));
		
		Weight weight = new Weight();
		weight.setName("weight");
		weight.setSymbol("kg");
		weight.setValue(new BigDecimal(2.3));
		
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		
		Item item = new Item();
		item.setBuyNowPrice(new MonetaryAmount(new BigDecimal("113.32"), Currency.getInstance("EUR")));
		item.setInitialPrice(new MonetaryAmount(new BigDecimal("1"), Currency.getInstance("USD")));
		item.setDimensions(dimensions);
		item.setWeight(weight);
		
		em.getTransaction().begin();
		em.persist(item);
		em.getTransaction().commit();
		
		em.close();
		fac.close();
	}
}
