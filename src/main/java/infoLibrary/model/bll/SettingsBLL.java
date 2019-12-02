package infoLibrary.model.bll;

import infoLibrary.model.dal.SettingsDAL;
import infoLibrary.model.entity.Settings;

/**
 * @author Volkan Okçu
 *	
 * @date 2017-Mar-23 23:57:34 
 *
 */
public class SettingsBLL extends AbstractBLL<Settings> {

	public SettingsBLL() {
		super(new SettingsDAL());
	}

	@Override
	public boolean validateEntity(Settings entity) {
		return true;
	}
}
