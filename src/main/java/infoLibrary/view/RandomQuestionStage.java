package infoLibrary.view;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import infoLibrary.model.DBContext;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Volkan Okçu
 *	
 * @date 2017-Nis-01 01:05:52 
 *
 */
public class RandomQuestionStage {

	private String fxml;
	private String title;
	private Timer timer;
	private int questionCount;
	
	public RandomQuestionStage() {
		timer = new Timer();
		questionCount = DBContext.settings.getAutoQuestionCount();
	}
	
	
	public void start() {
		timer.schedule(createTimerTask(), DBContext.settings.getAutoQuestionTime() * 5000);
	}
	
	private TimerTask createTimerTask() {
		TimerTask timerTask = new TimerTask() {
			Stage stage = new Stage();
			@Override
			public void run() {
				Platform.runLater(()->{
					questionCount = DBContext.settings.getAutoQuestionCount();
					for (int i = 0; i < questionCount; i++) {
						randomFxml();
						Parent root = null;
						try {
							root = new FXMLLoader(getClass().getResource(fxml)).load();
						} catch (IOException e) {
							e.printStackTrace();
						}
						stage.setScene(new Scene(root));
						stage.setTitle(title);
						stage.setResizable(false);
						stage.showAndWait();
					}
					start();
				});
			}
		};
		return timerTask;
	}
	
	private void randomFxml() {
		int start = 0;
		if (DBContext.settings.isTranslation() && DBContext.settings.isAbbreviation()) {
			start = new Random().nextInt(2);
		} else if (DBContext.settings.isTranslation()) {
			start = 1;
		}
		
		switch (start) {
		case 0:
			fxml = View.ABBREVIATION;
			
			break;
		case 1:
			fxml = View.TRANSLATION;
			break;

		default:
			break;
		}
		if (start == 0) {
			title = "KISALTMA SORUSU";
		} else title = "ÇEVİRİ SORUSU";
	}
}
