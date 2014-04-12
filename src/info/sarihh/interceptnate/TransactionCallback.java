package info.sarihh.interceptnate;

/**
 * @author Sari Haj Hussein
 */
public interface TransactionCallback {
	public <T> T doInTransaction();
}