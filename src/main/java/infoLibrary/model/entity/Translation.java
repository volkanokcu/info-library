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
@Table(name = "TRANSLATION")
@Inheritance (strategy = InheritanceType.JOINED)
@AttributeOverride(name = "id", column = @Column(name = "TRANSLATION_ID"))
public class Translation extends AbstractEntity {
	
	public Translation() {
		this.historyCount = new HistoryCount();
	}
	@Transient
	private static final long serialVersionUID = 1L;
	@Column(name = "TURKISH_WORD")
	private String turkishWord;
	@Column(name = "ENGLISH_WORD")
	private String englishWord;
	
	@Embedded
	private HistoryCount historyCount;
	
	public String getTurkishWord() {
		return turkishWord;
	}
	public void setTurkishWord(String turkishWord) {
		this.turkishWord = turkishWord;
	}
	public String getEnglishWord() {
		return englishWord;
	}
	public void setEnglishWord(String englishWord) {
		this.englishWord = englishWord;
	}
	public HistoryCount getHistoryCount() {
		return historyCount;
	}
	public void setHistoryCount(HistoryCount historyCount) {
		this.historyCount = historyCount;
	}

}
