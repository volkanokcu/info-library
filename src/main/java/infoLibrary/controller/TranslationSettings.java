package infoLibrary.controller;

import org.apache.commons.lang3.text.WordUtils;

import infoLibrary.model.DBContext;
import infoLibrary.model.entity.Translation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * @author Volkan Okçu
 *	
 * @date 2017-Nis-04 01:57:59 
 *
 */
public class TranslationSettings extends AbstractController {
	
	Translation entity;

	@FXML
	private TextField turkishTF;
	
	@FXML
	private Label resultLBL;
	
	@FXML
	private Button addBTN;
	
	@FXML
	private Button clearBTN;
	
	@FXML
	private TextField englishTF;
	
	@FXML
	private TableView<?> listTV;

	
	@FXML
	void buttonHandler(ActionEvent e) {
		
		if (e.getSource() == addBTN) {
			System.out.println(trimAll(turkishTF.getText()));
			setEntity();
    		if (validation()) {
    			boolean result = DBContext.getInstance().translation().insertEntity(entity);
    			if (result) {
    				fadeInFadeOut(2, resultLBL);
    			}
			}	
		}
		else if (e.getSource() == clearBTN) {
			
		}

	}
	
	@FXML
	public void initialize() {
		entity = new Translation();
	}
	
	private void setEntity() {
		entity.setTurkishWord(WordUtils.capitalizeFully(trimAll(turkishTF.getText())));
		entity.setEnglishWord(WordUtils.capitalizeFully(trimAll(englishTF.getText())));
	}
	
	private boolean validation() {
    	boolean result = false;
    	if (turkishTF.getText().trim().isEmpty()) {
			getAlert(turkishTF.getPromptText() + " Boş Bırakılamaz", "Eksik Form", AlertType.WARNING);
		}
    	else if (trimAll(englishTF.getText()).isEmpty()) {
			getAlert(englishTF.getPromptText() + " Boş Bırakılamaz", "Eksik Form", AlertType.WARNING);
		} 
    	
    	else if (!isAlphabetic(trimAll(turkishTF.getText()))) {
    		getAlert(turkishTF.getPromptText() + " Sacede Alfabetik Karakterlerden Oluşmalıdır", "Alfabetik Form", AlertType.WARNING);
		}
    	else if (!isAlphabetic(trimAll(englishTF.getText()))) {
    		getAlert(englishTF.getPromptText() + " Sacede Alfabetik Karakterlerden Oluşmalıdır", "Alfabetik Form", AlertType.WARNING);
		}
    	else result = true;
    	return result;
	}
	    
}
