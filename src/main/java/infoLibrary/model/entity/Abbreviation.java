package infoLibrary.model.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Volkan Okçu
 * @since 16 Mar 2017
 * @version 1.0.0
 */

@Entity
@Table(name="ABBREVIATION")
@Inheritance(strategy = InheritanceType.JOINED)
@AttributeOverride(name = "id", column = @Column(name = "ABBREVIATION_ID"))
public class Abbreviation extends AbstractEntity {
	
	public Abbreviation() {
		this.historyCount = new HistoryCount();
	}
	
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Column(name = "ABBREVIATION_NAME",  length = 50, nullable = false)
	private String abbName;
	
	@Column(name = "ABBREVIATION_EXPANSION_NAME", length = 150, nullable = false )
	private String abbExpansionName;
	
	@Column(name = "ABBREVIATION_DETAIL", length = 3000, nullable = true)
	private String abbDetail;
	
	@Embedded
	private HistoryCount historyCount;

	public String getAbbName() {
		return abbName;
	}

	public void setAbbName(String abbName) {
		this.abbName = abbName;
	}

	public String getAbbExpansionName() {
		return abbExpansionName;
	}

	public void setAbbExpansionName(String abbExpansionName) {
		this.abbExpansionName = abbExpansionName;
	}

	public String getAbbDetail() {
		return abbDetail;
	}

	public void setAbbDetail(String abbDetail) {
		this.abbDetail = abbDetail;
	}

	public HistoryCount getHistoryCount() {
		return historyCount;
	}

	public void setHistoryCount(HistoryCount historyCount) {
		this.historyCount = historyCount;
	}
	
	@Override
	public String toString() {
		return "Abbreviation [abbName=" + abbName + ", abbExpansionName=" + abbExpansionName + ", abbDetail="
				+ abbDetail + ", historyCount=" + historyCount + "]";
	}

}
