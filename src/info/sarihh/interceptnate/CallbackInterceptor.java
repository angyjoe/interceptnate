package info.sarihh.interceptnate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Sari Haj Hussein
 */
public class CallbackInterceptor extends TraditionalInterceptor {

	private static Session session;

	/** callback work and do it in an open transaction */
	public static <T> T inTransaction(TransactionCallback callback) {
		session = getSessionFactory().openSession();
		Transaction transaction = null;
		T t = null;
		try {
			transaction = session.beginTransaction();
			t = callback.doInTransaction();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return t;
	}

	/** return the Session */
	public static Session getSession() {
		return session;
	}
}
