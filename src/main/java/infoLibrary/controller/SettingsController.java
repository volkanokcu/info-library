package infoLibrary.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import infoLibrary.model.DBContext;
import infoLibrary.model.entity.Settings;
import infoLibrary.view.RandomQuestionStage;
import infoLibrary.view.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Volkan Okçu
 *	
 * @date 2017-Mar-23 23:43:38 
 *
 */
public class SettingsController extends AbstractController {
	
	private Settings settings;
	
	@FXML
	private CheckBox transtlationCheckBox;
	
	@FXML
	private CheckBox abbreviationCheckBox;
	
	@FXML
	private Button setDefaultBTN;
	
	@FXML
	private Button saveBTN;
	
	@FXML
	private CheckBox autoStartCheckBox;
	
	@FXML
	private CheckBox autoQuestionCheckBox;
	
	@FXML
	private TextField autoQuestionHourTF;
	
	@FXML
	private TextField autoQuestionMinuteTF;
	
	@FXML
	private CheckBox turkishToEnglishCheckBox;
	
	@FXML
	private CheckBox englishToTurkishCheckBox;
	
	@FXML
	private TextField autoQuestionCount;
	
	@FXML
	private CheckBox questionTransitionCheckBox;
	
	@FXML
	private TextField durationOfTransitionTF;
	
	@FXML
	private Button translationBTN;
	
	@FXML
	private Button abbreviationBTN;
	
	@FXML
	private Label informationLBL;
	
	@FXML
	void actions(ActionEvent e) {
		informationLBL.setVisible(false);
		if(e.getSource() == abbreviationBTN) {
			try {
				Parent root = new FXMLLoader(getClass().getResource(View.ABBREVIATION_SETTINGS)).load();
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setTitle("Kısaltma Listesi");
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} 
		else if (e.getSource() == translationBTN) {
			try {
				Parent root = new FXMLLoader(getClass().getResource(View.TRANSLATION_SETTINGS)).load();
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setTitle("Çeviri Listesi");
				stage.setScene(scene);
				stage.show();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		else if (e.getSource() == saveBTN) {
			if (validation()) {
				setEntityFromView();
				boolean result = DBContext.getInstance().settings().updateEntity(settings);
				if (result) {
					informationLBL.setVisible(true);
				} else getAlert("Kaydedilemedi", "Baglantı Hatası", AlertType.ERROR);
			}
		}
		
		else if (e.getSource() == setDefaultBTN) {
			settings.setDefaultValue();
			showSettings();
		}
		
		else if (e.getSource() == transtlationCheckBox) {
			if (!transtlationCheckBox.isSelected()) {
				turkishToEnglishCheckBox.setSelected(false);
				englishToTurkishCheckBox.setSelected(false);
			} else {
				turkishToEnglishCheckBox.setSelected(true);
				englishToTurkishCheckBox.setSelected(true);
			}
		}
		
		else if (e.getSource() == turkishToEnglishCheckBox || e.getSource() == englishToTurkishCheckBox) {
			if(turkishToEnglishCheckBox.isSelected() == false && englishToTurkishCheckBox.isSelected() == false) {
				transtlationCheckBox.setSelected(false);
			} else transtlationCheckBox.setSelected(true);
			
		}
		
		else if (e.getSource() == autoQuestionCheckBox) {
			if (autoQuestionCheckBox.isSelected() == false) {
				autoQuestionHourTF.setEditable(false);
				autoQuestionMinuteTF.setEditable(false);
				autoQuestionCount.setEditable(false);
				autoQuestionHourTF.setText(String.valueOf(settings.getAutoQuestionTime() / 60));
				autoQuestionMinuteTF.setText(String.valueOf(settings.getAutoQuestionTime() % 60));
				autoQuestionCount.setText(String.valueOf(settings.getAutoQuestionCount()));
			} else {
				autoQuestionHourTF.setEditable(true);
				autoQuestionMinuteTF.setEditable(true);
				autoQuestionCount.setEditable(true);
			}
		}
		
		
	
	}
	
	
	@FXML
	public void initialize() {
		settings = DBContext.getInstance().settings().getSettings();
		showSettings();
		RandomQuestionStage timerRandomQuestion = new RandomQuestionStage();
		timerRandomQuestion.start();
	}

	private void showSettings() {
		transtlationCheckBox.setSelected(settings.isTranslation());
		abbreviationCheckBox.setSelected(settings.isAbbreviation());
		turkishToEnglishCheckBox.setSelected(settings.isTurkishToEnglish());
		englishToTurkishCheckBox.setSelected(settings.isEnglishToTurkish());
		autoQuestionCheckBox.setSelected(settings.isAutoQuestion());
		autoStartCheckBox.setSelected(settings.isAutoStart());
		questionTransitionCheckBox.setSelected(settings.isQuestionTransition());
		autoQuestionHourTF.setText(String.valueOf(settings.getAutoQuestionTime() / 60));
		autoQuestionMinuteTF.setText(String.valueOf(settings.getAutoQuestionTime() % 60));
		autoQuestionCount.setText(String.valueOf(settings.getAutoQuestionCount()));
		durationOfTransitionTF.setText(String.valueOf(settings.getDurationOfTransition()));
		
	}
	
	private void setEntityFromView() {
		settings.setTranslation(transtlationCheckBox.isSelected());
		settings.setAbbreviation(abbreviationCheckBox.isSelected());
		settings.setTurkishToEnglish(turkishToEnglishCheckBox.isSelected());
		settings.setEnglishToTurkish(englishToTurkishCheckBox.isSelected());
		settings.setAutoQuestion(autoQuestionCheckBox.isSelected());
		settings.setAutoStart(autoStartCheckBox.isSelected());
		settings.setQuestionTransition(questionTransitionCheckBox.isSelected());
		settings.setAutoQuestionTime((Integer.parseInt(autoQuestionHourTF.getText())* 60) + (Integer.parseInt(autoQuestionMinuteTF.getText())));
		settings.setAutoQuestionCount(Integer.parseInt(autoQuestionCount.getText()));
		settings.setDurationOfTransition(Integer.parseInt(durationOfTransitionTF.getText()));
	}
	
	private boolean validation() {
		boolean result = false;
		if (autoQuestionCheckBox.isSelected() && (autoQuestionHourTF.getText().isEmpty() || autoQuestionMinuteTF.getText().isEmpty())) {
			getAlert("Lütfen Saat ve Dakika Belirleyiniz.", "Otomatik Soru Zamanlama Ayarı", AlertType.WARNING);
		} 
		else if (!isNumeric(autoQuestionCount.getText()) ||  !isNumeric(autoQuestionHourTF.getText()) || !isNumeric(autoQuestionMinuteTF.getText()) || !isNumeric(durationOfTransitionTF.getText())) {
			getAlert("Lütfen Rakam Giriniz", "Rakam Hatası", AlertType.WARNING);
		} else result = true;
		return result;
	}
	
	
	public void getAlert(String contentText, String title, AlertType alertType) {
		Alert alert =  new Alert(alertType);
		alert.setContentText(contentText);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.showAndWait();
	}
}
