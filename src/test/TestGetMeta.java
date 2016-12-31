package test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;

import model.User1;
import model.User1_;

public class TestGetMeta {

	public static void main(String[] args) {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa");
		EntityManager manager = fac.createEntityManager();
		Metamodel mm = fac.getMetamodel();
		Set<ManagedType<?>> managedTypes = mm.getManagedTypes();
		System.out.println(managedTypes.size());
		
		Iterator<ManagedType<?>> iterator = managedTypes.iterator();
		iterator.next();
		ManagedType<?> type = iterator.next();
		System.out.println(type.getPersistenceType());
		
		SingularAttribute attribute1 = type.getSingularAttribute("password");
		System.out.println(attribute1.getJavaType());
		System.out.println(attribute1.getPersistentAttributeType());
		System.out.println(attribute1.isOptional());
		
		SingularAttribute attribute2 = type.getSingularAttribute("birthday");
		System.out.println(attribute2.getJavaType());
		System.out.println(attribute2.getPersistentAttributeType());
		System.out.println(attribute2.isOptional());
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<User1> query = cb.createQuery(User1.class);
		Root<User1> user1 = query.from(User1.class);
//		query.select(fromItem);
		List<User1> items = manager.createQuery(query).getResultList();
		System.out.println(items);
		
		Path<String> usernamePath = user1.get("username");
		query.where(
			cb.like(usernamePath, cb.parameter(String.class, "pattern"))
		);
		items = manager.createQuery(query)
				.setParameter("pattern", "%b%")
				.getResultList();
		System.out.println(items);
		
		query.where(
				cb.like(user1.get(User1_.username), 
				cb.parameter(String.class, "pattern"))
		);
		items = manager.createQuery(query)
				.setParameter("pattern", "%a%")
				.getResultList();
		System.out.println(items);

		manager.close();
		fac.close();
	}
}

