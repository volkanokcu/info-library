package infoLibrary.model.dal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import infoLibrary.model.DBContext;
import infoLibrary.model.entity.Settings;

/**
 * @author Volkan Okçu
 *	
 * @date 2017-Mar-22 01:39:43 
 *
 */
public class SettingsDAL extends AbstractDAL<Settings>{

	private static final long serialVersionUID = -377346438408784285L;
	private final String file = "app.config.bin";
	
	@Override
	public boolean updateEntity(Settings entity) {
		boolean result = false;
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))){
			os.writeObject(entity);
			os.close();
			result = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DBContext.settings = entity;
		return result;
	}
	
	@Override
	public Settings getSettings() {
		Settings settings = null;
		try(ObjectInputStream io = new ObjectInputStream(new FileInputStream(file))) {
			settings = (Settings) io.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return settings;
	}

}
