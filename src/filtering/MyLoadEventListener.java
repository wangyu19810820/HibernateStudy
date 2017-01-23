package filtering;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.EntityKey;
import org.hibernate.event.internal.DefaultLoadEventListener;
import org.hibernate.event.spi.LoadEvent;

public class MyLoadEventListener extends DefaultLoadEventListener {

	private static final long serialVersionUID = 1L;

	@Override
	protected Object loadFromSessionCache(LoadEvent arg0, EntityKey arg1, LoadType arg2) throws HibernateException {
		System.out.println("loadFromSessionCache:" + arg0 + "," + arg1 + "," + arg2);
		return super.loadFromSessionCache(arg0, arg1, arg2);
	}

	@Override
	public void onLoad(LoadEvent event, LoadType loadType) throws HibernateException {
		System.out.println("onLoad:" + event + "," + loadType);
		super.onLoad(event, loadType);
	}

}
