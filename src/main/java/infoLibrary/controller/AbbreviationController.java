package infoLibrary.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import infoLibrary.model.DBContext;
import infoLibrary.model.entity.Abbreviation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Volkan
 *
 */
public class AbbreviationController extends AbstractController {
	
	private Abbreviation entity;
	
	private final String questionString = " Açılımı Nedir?";
	
	@FXML
    private Label questionLBL;

    @FXML
    private TextField answerTF;

    @FXML
    private Label resultLBL;

    @FXML
    private Button answerBTN;

    @FXML
    private Button nextBTN;

    @FXML
    private Label nextQuestionCountLBL;


    @FXML
    private Label nextQuestionLBL;
    
    @FXML
    private Button detailsBTN;

	@FXML
	void buttonHandler(ActionEvent e) throws IOException {
		if (e.getSource() == answerBTN) {
			if (trimAll(answerTF.getText()).equalsIgnoreCase(trimAll(entity.getAbbExpansionName()))) {
				rightAnswer();
			} else wrongAnswer();
			updateEnity();
			Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			startTransition(nextQuestionCountLBL, stage).start();
			nextQuestionCountLBL.setVisible(true);
			nextQuestionLBL.setVisible(true);
		}
		
		else if (e.getSource() == detailsBTN) {
			 try {
				Desktop.getDesktop().browse(new URI(entity.getAbbDetail()));
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		}
		
		else if (e.getSource() == nextBTN) {
			Node source = (Node) e.getSource();
			Stage stage = (Stage) source.getScene().getWindow();
			if (!(super.transition == null)) {
				super.transition.cancel();
			}
			stage.close();
		}
	}

	private void wrongAnswer() {
		resultLBL.setText("CEVAP: " + entity.getAbbExpansionName().toUpperCase());
		resultLBL.setTextFill(Color.web("#e80909"));
		fadeIn(1, resultLBL);
		answerBTN.setDisable(true);
	}

	private void rightAnswer() {
		resultLBL.setText("TEBRİKLER");
		resultLBL.setTextFill(Color.web("#1e9044"));
		fadeIn(1, resultLBL);
		answerBTN.setDisable(true);
		entity.getHistoryCount().setRightAnswerCount(entity.getHistoryCount().getRightAnswerCount() + 1);
	}

	private void updateEnity() {
		entity.getHistoryCount().setCount(entity.getHistoryCount().getCount() + 1);
		DBContext.getInstance().abbreviation().updateEntity(entity);

	}
	

	@FXML
	public void initialize() {
		entity = DBContext.getInstance().abbreviation().getRandomEntity();
		showEntity();
	}
	
	private void showEntity() {
		questionLBL.setText(entity.getAbbName() + questionString);
	}
	
	
	
}
