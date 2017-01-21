package fetch;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

public class Test1 {

	public static void main(String[] args) {
		c();
	}
	
	public static void c() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		Map<String, Object> properties = new HashMap<>();
//		properties.put("javax.persistence.loadgraph", 
//					   em.getEntityGraph(Item.class.getSimpleName()));
		properties.put("javax.persistence.loadgraph", 
				   em.getEntityGraph("ItemSeller"));
//		Item item = em.find(Item.class, 5L, properties);
		
//		List<Item> items = em.createQuery("select i from Item i")
//							 .setHint("javax.persistence.fetchgraph", em.getEntityGraph("ItemSeller"))
//							 .getResultList();
		
//		List<Bid> bids = em.createQuery("select s from Bid s")
//						   .setHint("javax.persistence.fetchgraph", em.getEntityGraph("BidItem"))
//						   .getResultList();
		
		EntityGraph<Item> itemGraph = em.createEntityGraph(Item.class);
		itemGraph.addAttributeNodes("seller");
		Map<String, Object> properties1 = new HashMap<>();
		properties1.put("javax.persistence.loadgraph", itemGraph);
		em.find(Item.class, 5L, properties1);
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
	
	public static void b() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		em.unwrap(Session.class).enableFetchProfile(Item.PROFILE_JOIN_SELLER);
		em.find(Item.class, 5L);
		em.clear();
		em.unwrap(Session.class).enableFetchProfile(Item.PROFILE_JOIN_BIDS);
		em.find(Item.class, 5L);
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
	
	public static void a() {
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

		
		Item item1 = em.find(Item.class, 5L);
//		System.out.println(item1.getSeller());
//		em.find(Bid.class, 7L);
		
		
//		List<Item> items = em.createQuery("select i from Item i join fetch i.seller left join fetch i.bid").getResultList();
		
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery criteria = cb.createQuery();
//		Root<Item> i = criteria.from(Item.class);
//		i.fetch("seller");
//		i.fetch("bid", JoinType.LEFT);
//		criteria.select(i);
//		List<Item> items = em.createQuery(criteria).getResultList();
		
//		for (Item item : items) {
//			System.out.println(item.getSeller().getId());
//		}
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}

}



