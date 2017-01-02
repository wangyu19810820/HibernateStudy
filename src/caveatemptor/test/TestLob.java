package caveatemptor.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.engine.jdbc.StreamUtils;

import caveatemptor.model.Item;

public class TestLob {

	public static void main(String[] args) throws Exception {
		EntityManagerFactory fac = Persistence.createEntityManagerFactory("com.gadfly1981.jpa.mysql");
		EntityManager em = fac.createEntityManager();
		
		File file = new File("Files/demo.png");
		FileInputStream in = new FileInputStream(file);
//		byte[] byteArr = new byte[(int) file.length()];
//		in.read(byteArr);
//		in.close();
		
//		Item item = new Item();
//		item.setDescription("Description");
//		item.setImage(byteArr);
//		Clob clob = new Clob();
//		clob
//		item.setDescriptionClob(.setString(0, "Description"));
		
//		em.getTransaction().begin();
//		em.persist(item);
//		em.getTransaction().commit();
		
		// insert Blob对象
		Item item = new Item();
		item.setDescription("Description");
		Session session = em.unwrap(Session.class);
		Blob blob = session.getLobHelper()
				.createBlob(in, file.length());
		item.setImageBlob(blob);
		em.persist(item);
		
		em.getTransaction().begin();
		em.persist(item);
		em.getTransaction().commit();

		
		// 获取大对象
//        Item item = em.find(Item.class, 1L);
//        InputStream imageDataStream = item.getImageBlob().getBinaryStream();
//        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//        StreamUtils.copy(imageDataStream, outStream);
//		byte[] imageBytes = outStream.toByteArray();
//		System.out.println(Arrays.toString(imageBytes));
//		System.out.println(Arrays.toString(item.getImage()));
        
		em.close();
		fac.close();
		
		
	}
}
