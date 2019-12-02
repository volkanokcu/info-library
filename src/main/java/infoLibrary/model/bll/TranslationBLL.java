package infoLibrary.model.bll;

import infoLibrary.model.dal.AbstractDAL;
import infoLibrary.model.dal.TranslationDAL;
import infoLibrary.model.entity.Translation;

/**
 * @author Volkan Okçu
 *	
 * @date 2017-Mar-23 23:57:24 
 *
 */
public class TranslationBLL extends AbstractBLL<Translation>{

	public TranslationBLL() {
		super(new TranslationDAL());
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validateEntity(Translation entity) {
		// TODO Auto-generated method stub
		return true;
	}

}
