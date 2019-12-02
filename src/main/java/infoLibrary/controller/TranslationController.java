package infoLibrary.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import infoLibrary.model.DBContext;
import infoLibrary.model.entity.Translation;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Volkan
 *
 */
public class TranslationController extends AbstractController {
	
	List<Translation> entities;
	
	Translation entity;
	
	int rightAnswerValue = new Random().nextInt(3);
	
	@FXML
	private Label questionLBL;
	
	@FXML
	private Label nextQuestionCountLBL;
	
	@FXML
	private RadioButton caseA;
	
	@FXML
	private ToggleGroup cases;
	
	@FXML
	private RadioButton caseB;
	
	@FXML
	private RadioButton caseC;
	
	@FXML
	private RadioButton caseD;
	
	private RadioButton rightAnswerCase;
	private RadioButton wrongAnswerCase;
	
	@FXML Button nextBTN;
	
	ArrayList<RadioButton> radiooButtons;
	
	@FXML
	void buttonHandler(ActionEvent e) throws IOException {
		if (e.getSource() == nextBTN) {
			Node source = (Node) e.getSource();
			Stage stage = (Stage) source.getScene().getWindow();
			if (!(super.transition == null)) {
				super.transition.cancel();
			}
			stage.close();
		}
	
	}
	
	@FXML
	void caseActions(ActionEvent e2) {
		
		if (e2.getSource() == rightAnswerCase) {
			rightAnswer();
			System.out.println("rightAnswer()");
		} else {
			wrongAnswerCase = ((RadioButton) e2.getSource());
			wrongAnswer();
		}
		updateEnity();
		disableCases();
		Stage stage = (Stage) ((Node)e2.getSource()).getScene().getWindow();
		startTransition(nextQuestionCountLBL, stage).start();
	}

	
	private void disableCases() {
		for (RadioButton radioButton : radiooButtons) {
			radioButton.setDisable(true);
		}
	}

	private void wrongAnswer() {
		wrongAnswerCase.setTextFill(Color.web("#e80909"));
		rightAnswerCase.setTextFill(Color.web("#1e9044"));
	}

	private void rightAnswer() {
		rightAnswerCase.setTextFill(Color.web("#1e9044"));
		entity.getHistoryCount().setRightAnswerCount(entity.getHistoryCount().getCount() + 1);
	}
	
	private void updateEnity() {
		entity.getHistoryCount().setCount(entity.getHistoryCount().getCount() + 1);
		DBContext.getInstance().translation().updateEntity(entity);
	}

	@FXML
	public void initialize() {
		entities = DBContext.getInstance().translation().getRandomEntities();
		entity = entities.get(0);
		radiooButtons = new ArrayList<>();
		radiooButtons.add(caseA);
		radiooButtons.add(caseB);
		radiooButtons.add(caseC);
		radiooButtons.add(caseD);
		
		int transtlationType = 0;
		if (DBContext.settings.isEnglishToTurkish() && DBContext.settings.isTurkishToEnglish()) {
			transtlationType = new Random().nextInt(2);
		}
		else if (DBContext.settings.isEnglishToTurkish()) {
			transtlationType = 1;
		}
		
		switch (transtlationType) {
		case 0:
			showTurkishToEnglishQuestion();
			break;
		case 1:
			showEnglishToTurkishQuestion();
			break;
		}
		
	}
	

	
	private void showTurkishToEnglishQuestion() {
		long seed = System.nanoTime();
		Collections.shuffle(radiooButtons, new Random(seed));
		rightAnswerCase = radiooButtons.get(0);
		for (int i = 0; i < radiooButtons.size(); i++) {
			radiooButtons.get(i).setText(entities.get(i).getEnglishWord());
		}
		questionLBL.setText(entities.get(0).getTurkishWord() + " İngilizce Karşılığı Nedir?");

	}
	
	private void showEnglishToTurkishQuestion() {
		long seed = System.nanoTime();
		Collections.shuffle(radiooButtons, new Random(seed));
		rightAnswerCase = radiooButtons.get(0);
		for (int i = 0; i < radiooButtons.size(); i++) {
			radiooButtons.get(i).setText(entities.get(i).getTurkishWord());
		}
		questionLBL.setText(entities.get(0).getEnglishWord() + " Türkçe Karşılığı Nedir?");
	}

}
