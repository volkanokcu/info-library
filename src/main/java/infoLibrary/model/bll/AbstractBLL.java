package infoLibrary.model.bll;

import java.util.List;

import infoLibrary.model.dal.AbstractDAL;
import infoLibrary.model.entity.GenericEntity;
import infoLibrary.model.entity.Translation;

/**
 * @author Volkan Okçu
 *	
 * @date 2017-Mar-24 00:09:25 
 *
 */
public abstract class AbstractBLL<E extends GenericEntity> implements GenericBLL<E> {
	
	private final AbstractDAL<E> abstractDAL;
	
	public AbstractBLL(AbstractDAL<E> abstractDAL) {
		this.abstractDAL = abstractDAL;
	}
	public boolean insertEntity(E entity){
		boolean result = false;
		if (validateEntity(entity)) result = abstractDAL.insertEntity(entity);
		return result;
	}
	public boolean deleteEntity(E entity) {
		boolean result = false;
		if (validateEntity(entity)) result = abstractDAL.deleteEntity(entity);
		return result;
	}
	public boolean updateEntity(E entity) {
		boolean result = false;
		if (validateEntity(entity)) result = abstractDAL.updateEntity(entity);
		return result;
	}
	public E getSettings() {
		return abstractDAL.getSettings();
	}
	
	@Override
	public E getRandomEntity() {
		return abstractDAL.getRandomEntity();
	}
	
	@Override
	public List<E> getRandomEntities() {
		return abstractDAL.getRandomEntities();
	}
}
