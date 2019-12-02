package infoLibrary.model.entity;


/**
 * @author Volkan Okçu
 *	
 * @date 2017-Mar-21 23:06:28 
 *
 */
public class Settings extends AbstractEntity {

	private static final long serialVersionUID = -3235611787723458982L;

	private boolean turkishToEnglish;
	private boolean englishToTurkish;
	private boolean translation;
	private boolean abbreviation;
	private boolean autoStart;
	private boolean autoQuestion;
	private boolean questionTransition;
	private int autoQuestionTime;
	private int autoQuestionCount;
	private int durationOfTransition;
	
	
	public boolean isTurkishToEnglish() {
		return turkishToEnglish;
	}
	public void setTurkishToEnglish(boolean turkishToEnglish) {
		this.turkishToEnglish = turkishToEnglish;
	}
	public boolean isEnglishToTurkish() {
		return englishToTurkish;
	}
	public void setEnglishToTurkish(boolean englishToTurkish) {
		this.englishToTurkish = englishToTurkish;
	}
	public boolean isTranslation() {
		return translation;
	}
	public void setTranslation(boolean translation) {
		this.translation = translation;
	}
	public boolean isAutoStart() {
		return autoStart;
	}
	public void setAutoStart(boolean autoStart) {
		this.autoStart = autoStart;
	}
	public boolean isAutoQuestion() {
		return autoQuestion;
	}
	public void setAutoQuestion(boolean autoQuestion) {
		this.autoQuestion = autoQuestion;
	}
	public int getAutoQuestionTime() {
		return autoQuestionTime;
	}
	public void setAutoQuestionTime(int autoQuestionTime) {
		this.autoQuestionTime = autoQuestionTime;
	}
	public int getAutoQuestionCount() {
		return autoQuestionCount;
	}
	public void setAutoQuestionCount(int autoQuestionCount) {
		this.autoQuestionCount = autoQuestionCount;
	}
	public int getDurationOfTransition() {
		return durationOfTransition;
	}
	public void setDurationOfTransition(int durationOfTransition) {
		this.durationOfTransition = durationOfTransition;
	}
	public boolean isAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(boolean abbreviation) {
		this.abbreviation = abbreviation;
	}
	
	public boolean isQuestionTransition() {
		return questionTransition;
	}
	public void setQuestionTransition(boolean questionTransition) {
		this.questionTransition = questionTransition;
	}
	
	public void setDefaultValue() {
		this.turkishToEnglish = true;
		this.englishToTurkish = true;
		this.translation = true;
		this.autoStart = true;
		this.autoQuestion = true;
		this.abbreviation = true;
		this.questionTransition = true;
		this.autoQuestionTime = 40;
		this.autoQuestionCount = 5;
		this.durationOfTransition = 5;
	}
	@Override
	public String toString() {
		return "Settings [turkishToEnglish=" + turkishToEnglish + ", englishToTurkish=" + englishToTurkish
				+ ", translation=" + translation + ", abbreviation=" + abbreviation + ", autoStart=" + autoStart
				+ ", autoQuestion=" + autoQuestion + ", questionTransition=" + questionTransition
				+ ", autoQuestionTime=" + autoQuestionTime + ", autoQuestionCount=" + autoQuestionCount
				+ ", durationOfTransition=" + durationOfTransition + "]";
	}

}
