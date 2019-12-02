package infoLibrary.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import infoLibrary.model.DBContext;
import javafx.animation.FadeTransition;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Volkan Okçu
 *	
 * @date 2017-Mar-29 03:46:21 
 *
 */
public abstract class AbstractController {
	
	public Service<Void> transition; 
	
	String trimAll(String string) {
		return string.replaceAll("\\s","");
	}
	
	boolean isNumeric(String text) {
		Pattern pattern = Pattern.compile("\\D");
		Matcher matcher = pattern.matcher(text);
		return !matcher.find();
	}
	
	boolean isAlphabetic(String text) {
		Pattern pattern = Pattern.compile("[^a-zA-Z_İığĞşŞöÖüÜçÇ]+");
		Matcher matcher = pattern.matcher(text);
		return !matcher.find();
	}
	
	void getAlert(String contentText, String title, AlertType alertType) {
		Alert alert =  new Alert(alertType);
		alert.setContentText(contentText);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.showAndWait();
	}
	
	void fadeInFadeOut(int duration, Node node) {
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(duration), node);
		fadeTransition.setFromValue(0.0);
		fadeTransition.setToValue(1.0);
		fadeTransition.play();
		fadeTransition.setOnFinished((e)->{
			fadeOut(duration, node);
		});
		
	}
	
	void fadeIn(int duration, Node node) {
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(duration), node);
		fadeTransition.setFromValue(0.0);
		fadeTransition.setToValue(1.0);
		fadeTransition.play();
	}
	
	void fadeOut(int duration, Node node) {
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(duration), node);
		fadeTransition.setFromValue(1.0);
		fadeTransition.setToValue(0.0);
		fadeTransition.play();
	}
	
	protected Service<Void> startTransition(Label label, Stage stage) {
		transition = new Service<Void>() {

			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {

					@Override
					protected Void call() throws Exception {
						
						for (int i = DBContext.settings.getDurationOfTransition(); i > 0; i--) {
							updateMessage(String.valueOf(i));
							Thread.sleep(1000);
						}
						
						return null;
					}
				};
			}
			
		};
		transition.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			
			@Override
			public void handle(WorkerStateEvent event) {
				label.textProperty().unbind();
				stage.close();
			}
		});
		label.textProperty().bind(transition.messageProperty());
		return transition;
	}


}
