package infoLibrary.model.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

/**
 * @author Volkan Okçu
 *	
 * @date 2017-Mar-21 15:15:56 
 *
 */
@Embeddable
public class HistoryCount {
	
	@Transient
	private static final long serialVersionUID = -6032979284102816646L;
	@Column(name = "COUNT")
	private Integer count = 0;
	@Column(name = "RIGHT_ANSWER_COUNT")
	private Integer rightAnswerCount = 0;
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getRightAnswerCount() {
		return rightAnswerCount;
	}
	public void setRightAnswerCount(Integer rightAnswerCount) {
		this.rightAnswerCount = rightAnswerCount;
	}
	
	public float rightAnswerRate() {
		float rate = 0.99f;
		if (this.count != 0) {
			rate = this.rightAnswerCount / this.count;
		}
		return rate;
	}
}
