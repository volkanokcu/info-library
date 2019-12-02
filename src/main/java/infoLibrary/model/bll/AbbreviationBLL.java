package infoLibrary.model.bll;

import infoLibrary.model.dal.AbbreviationDAL;
import infoLibrary.model.entity.Abbreviation;

/**
 * @author Volkan Okçu
 *	
 * @date 2017-Mar-23 23:57:17 
 *
 */
public class AbbreviationBLL extends AbstractBLL<Abbreviation> {

	public AbbreviationBLL() {
		super(new AbbreviationDAL());
	}

	@Override
	public boolean validateEntity(Abbreviation entity) {
		return true;
	}
}
