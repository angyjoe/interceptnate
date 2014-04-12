package info.sarihh.interceptnate;

import java.util.List;

/**
 * @author Sari Haj Hussein
 */
public interface TransactionCallback {
	public <T> T doInTransaction();
}