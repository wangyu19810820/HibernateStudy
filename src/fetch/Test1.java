package fetch;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

public class Test1 {

	public static void main(String[] args) {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();

//		List<Item> items = em.createQuery("select i from Item i where i.id < 20").getResultList();
//		System.out.println("OKKKKKKKK");
//		for (Item item : items) {
//			System.out.println(item.getSeller().getName());
////			System.out.println(item.getBid());
//		}
//		System.out.println(items.get(0).getSeller().getId());
//		System.out.println(items.get(2).getSeller().getId());

		
//		Item item1 = em.find(Item.class, 5L);
//		System.out.println(item1.getSeller());
//		em.find(Bid.class, 7L);
		
		
		List<Item> items = em.createQuery("select i from Item i join fetch i.seller left join fetch i.bid").getResultList();
		
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery criteria = cb.createQuery();
//		Root<Item> i = criteria.from(Item.class);
//		i.fetch("seller");
//		i.fetch("bid", JoinType.LEFT);
//		criteria.select(i);
//		List<Item> items = em.createQuery(criteria).getResultList();
		
		for (Item item : items) {
			System.out.println(item.getSeller().getId());
		}
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}

}



