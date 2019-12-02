package infoLibrary.model.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * @author Volkan Okçu
 * @since 16 Mar 2017
 * @version 1.0.0
 */

@MappedSuperclass
public class AbstractEntity implements GenericEntity {

	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@Access(AccessType.FIELD)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "STATUS")
	private boolean status = true;

	@Override
	public boolean isStatus() {
		return status;
	}
	
	@Override
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Override
	public Long getId() {
		return id; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity other = (AbstractEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractEntity [id=" + id + "]";
	}
	
}
