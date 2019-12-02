package infoLibrary.model.dal;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;

import infoLibrary.model.entity.GenericEntity;

/**
 * @author Volkan
 *
 */
public class AbstractDAL<E extends GenericEntity> implements GenericDAL<E>{

	private static final long serialVersionUID = 1121251288063657898L;
	protected final static SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

	public static void printSQLException(SQLException e) {
		System.err.println("Error Message: " + e.getMessage());
		System.err.println("Error Code: " + e.getErrorCode()); //Retrieves the vendor-specific exception code for this SQLException object.
		System.err.println("SQL State: " + e.getSQLState()); //Retrieves the SQLState for this SQLException object.
	}
	@Override
	public boolean insertEntity(E entity) {
		return false;
	}
	@Override
	public boolean deleteEntity(E entity) {
		return false;
	}
	@Override
	public boolean updateEntity(E entity) {
		return false;
	}
	
	@Override
	public E getRandomEntity() {
		return null;
	}
	
	@Override
	public E getSettings() {
		return null;
	}
	@Override
	public List<E> getRandomEntities() {
		return null;
	}
}
