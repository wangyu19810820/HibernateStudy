package filtering;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Session;
import org.hibernate.type.Type;

public class AuditLogInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 1L;
	protected Session currentSession;
	protected Long currentUserId;
	protected Set<Auditable> inserts = new HashSet<>();
	protected Set<Auditable> updates = new HashSet<>();

	@Override
	public void postFlush(Iterator entities) {
//		Session tempSession = currentSession.sessionWithOptions()
//				.transactionContext()
//				.connection()
//				.openSession();
		System.out.println("postFlush:");
		System.out.println(inserts);
		System.out.println(updates);
		System.out.println(currentUserId);
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, 
			Object[] previousState, String[] propertyNames, Type[] types) {
		System.out.println("onFlushDirty:" + entity);
		if (entity instanceof Auditable) {
			updates.add((Auditable)entity);
		}
		return false;
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		System.out.println("onSave:" + entity);
		if (entity instanceof Auditable) {
			inserts.add((Auditable)entity);
		}
		return false;
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Long getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(Long currentUserId) {
		this.currentUserId = currentUserId;
	}

	public Set<Auditable> getInserts() {
		return inserts;
	}

	public void setInserts(Set<Auditable> inserts) {
		this.inserts = inserts;
	}

	public Set<Auditable> getUpdates() {
		return updates;
	}

	public void setUpdates(Set<Auditable> updates) {
		this.updates = updates;
	}

}
