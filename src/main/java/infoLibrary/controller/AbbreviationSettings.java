package infoLibrary.controller;


import org.apache.commons.lang3.text.WordUtils;

import infoLibrary.model.DBContext;
import infoLibrary.model.entity.Abbreviation;
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
 * @date 2017-Mar-28 21:26:49 
 *
 */
public class AbbreviationSettings extends AbstractController{

	Abbreviation entity;
    @FXML
    private TextField nameTF;

    @FXML
    private Label resultLBL;

    @FXML
    private Button addBTN;

    @FXML
    private Button clearBTN;

    @FXML
    private TextField expansionTF;

    @FXML
    private TextField detailsTF;

    @FXML
    private TableView<?> listTV;

    @FXML
    void buttonHandler(ActionEvent e) {
    	System.out.println();
    	if (e.getSource() == addBTN) {
    		
    		setEntity();
    		if (validation()) {
    			boolean result = DBContext.getInstance().abbreviation().insertEntity(entity);
    			if (result) {
    				fadeInFadeOut(2, resultLBL);
    			}
			}	
		}
    }
    
    @FXML
    public void initialize() {
    	
    }
    
    private void setEntity() {
    	entity = new Abbreviation();
		entity.setAbbName(trimAll(nameTF.getText()).toUpperCase());
		entity.setAbbExpansionName(WordUtils.capitalizeFully(expansionTF.getText()));
		entity.setAbbDetail(detailsTF.getText());
	}
    
    private boolean validation() {
    	boolean result = false;
    	if (trimAll(nameTF.getText()).isEmpty()) {
			getAlert(nameTF.getPromptText() + " Boş Bırakılamaz", "Eksik Form", AlertType.WARNING);
		}
    	else if (trimAll(expansionTF.getText()).isEmpty()) {
			getAlert(expansionTF.getPromptText() + " Boş Bırakılamaz", "Eksik Form", AlertType.WARNING);
		} 
    	else if (!isAlphabetic(trimAll(nameTF.getText()))) {
    		getAlert(nameTF.getPromptText() + " Sacede Alfabetik Karakterlerden Oluşmalıdır", "Alfabetik Form", AlertType.WARNING);
		}
    	else if (!isAlphabetic(trimAll(expansionTF.getText()))) {
    		getAlert(expansionTF.getPromptText() + " Sacede Alfabetik Karakterlerden Oluşmalıdır", "Alfabetik Form", AlertType.WARNING);
		}
    	else result = true;
    	return result;
    }

}
