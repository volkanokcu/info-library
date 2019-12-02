package infoLibrary;

import infoLibrary.model.DBContext;
import infoLibrary.model.dal.HibernateUtil;
import infoLibrary.view.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Volkan
 *
 */
public class Driver extends Application {
	
	public static Stage MAIN_STAGE;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(View.SETTINGS));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		MAIN_STAGE = primaryStage;
		MAIN_STAGE.setScene(scene);
		MAIN_STAGE.setTitle("Info Library");
		MAIN_STAGE.setResizable(false);
		MAIN_STAGE.show();
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		DBContext.settings = DBContext.getInstance().settings().getSettings();

		launch(args);
		HibernateUtil.getSessionFactory().close();
	}
}