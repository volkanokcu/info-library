package infoLibrary.model.entity;

import java.io.Serializable;

/**
 * @author Volkan Okçu
 * @since 16 Mar 2017
 * @version 1.0.0
 */
public interface GenericEntity extends Serializable {

	public abstract Long getId();
	public abstract boolean isStatus();
	public abstract void setStatus(boolean status);

}
