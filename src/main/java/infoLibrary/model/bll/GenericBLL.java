package infoLibrary.model.bll;

import java.util.List;

import infoLibrary.model.entity.GenericEntity;

/**
 * @author Volkan Okçu
 *	
 * @date 2017-Mar-24 00:07:11 
 *
 */
public interface GenericBLL<E extends GenericEntity> {
	
	public abstract boolean validateEntity(E entity);
	public abstract E getRandomEntity();
	public List<E> getRandomEntities();

}
