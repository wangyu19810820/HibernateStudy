package transaction;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;

public class Test {

	public static void main(String[] args) {
		c();
	}
	
	public static void c() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		
//		Item item = new Item();
//		item.setName("11");
//		em.persist(item);
	
//		Item item = new Item();
//		item.setName("22");
//		item.setId(4L);
//		em.merge(item);
//		System.out.println(item);
		
		Item item = em.find(Item.class, 4L);
		em.remove(item);
		
		em.getTransaction().begin();
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
	
	public static void b() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
//		em.getTransaction().begin();
		
		Item item = em.find(Item.class, 3L);
		item.setName("bbb");
		Object o = em.createQuery("select i.name from Item i where i.id = :id")
					 .setParameter("id", 3L)
					 .getSingleResult();
		System.out.println(o);
		
		Item item1 = (Item)em.createQuery("select i from Item i where i.id = :id")
							 .setParameter("id", 3L)
							 .getSingleResult();
		System.out.println(item1.getName());
		
//		em.flush();
		em.refresh(item);
		System.out.println(item.getName());
		
//		em.getTransaction().commit();
		em.close();
		fac.close();
	}
	
	public static void a() {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();

//		Item item = em.find(Item.class, 3L);
//		Bid bid = new Bid();
//		bid.setAmount(new BigDecimal("44.44"));
//		item.getBid().add(bid);
//		em.persist(bid);
		
//		Category car = new Category();
//		car.setName("车");
//		em.persist(car);
//		
//		Category local = new Category();
//		local.setName("国产");
//		em.persist(local);
//		
//		Item item = new Item();
//		item.setName("汽车1");
//		item.setCategory(car);
//		em.persist(item);
//		
//		Item item1 = new Item();
//		item1.setName("汽车1");
//		item1.setCategory(local);
//		em.persist(item1);
//		em.flush();
//		
		for (int l : Arrays.asList(1, 2)) {
			List<Item> items = em.createQuery("select i from Item i")
								 .setLockMode(LockModeType.PESSIMISTIC_READ)
								 .setHint("javax.persistence.lock.timeout", 5000)
//								 .setParameter("catId", l)
								 .getResultList();
			for (Item i : items) {
				System.out.println(i.getVersion());
			}
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}
}
