package infoLibrary.model.dal;

import java.io.Serializable;
import java.util.List;

import infoLibrary.model.entity.GenericEntity;

/**
 * @author Volkan Okçu
 *	
 * @date 2017-Mar-21 22:36:11 
 *
 */
public interface GenericDAL <E extends GenericEntity> extends Serializable {

	public abstract boolean insertEntity(E entity);
	public abstract boolean deleteEntity(E entity);
	public abstract boolean updateEntity(E entity);
	public abstract E getRandomEntity();
	public abstract List<E> getRandomEntities();
	public abstract E getSettings();
}
