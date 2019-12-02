package infoLibrary.model;

import infoLibrary.model.bll.AbbreviationBLL;
import infoLibrary.model.bll.SettingsBLL;
import infoLibrary.model.bll.TranslationBLL;
import infoLibrary.model.entity.Settings;

/**
 * @author Volkan Okçu
 *	
 * @date 2017-Mar-23 23:52:43 
 *
 */
public class DBContext {
	
	public static Settings settings;
	private final AbbreviationBLL abbreviationBLL;
	private final TranslationBLL translationBLL;
	private final SettingsBLL settingsBLL;
	private static DBContext instance;
	
	private DBContext() {
		this.abbreviationBLL = new AbbreviationBLL();
		this.translationBLL = new TranslationBLL();
		this.settingsBLL = new SettingsBLL();
	}
	
	public static DBContext getInstance() {
		if(instance==null) {
			instance = new DBContext();
		}
		return instance;
	}
	
	public AbbreviationBLL abbreviation() {
		return abbreviationBLL;
	}
	
	public TranslationBLL translation() {
		return translationBLL;
	}
	
	public SettingsBLL settings() {
		return settingsBLL;
	}

}
