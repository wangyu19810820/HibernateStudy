package leave;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestLeave {

	public static void main(String[] args) {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		em.getTransaction().begin();
		
		Department department = new Department();
		department.setDepartname("depart2");
		
		User user = new User();
		user.getId().setUsername("user3");
//		user.setDepartment(department);
		user.getId().setDepartname(department.getDepartname());
		user.setEmail("e2d@d.com");
		user.setSex("ÄÐ");
		em.persist(department);
//		em.persist(user);
		
		Item item = new Item();
		item.setUser(user);
		item.setStartDate(LocalDate.now());
		item.setEndDate(LocalDate.now());
		em.persist(item);
		
//		User user = em.find(User.class, new User.UserId("user1", "depart1"));
//		System.out.println(user.getDepartment());
		
		em.getTransaction().commit();
		em.close();
		fac.close();
	}

}
