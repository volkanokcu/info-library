package infoLibrary.model.dal;

import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.hql.internal.ast.ASTQueryTranslatorFactory;
import org.hibernate.hql.spi.QueryTranslator;
import org.hibernate.hql.spi.QueryTranslatorFactory;

import infoLibrary.model.entity.Abbreviation;

/**
 * @author Volkan
 *
 */
public class AbbreviationDAL extends AbstractDAL<Abbreviation>{

	private static final long serialVersionUID = -6186292626521561424L;
	
	@Override
	public boolean insertEntity(Abbreviation entity) {
		boolean result = false;
		Session session = SESSION_FACTORY.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(entity);
			transaction.commit();
			result = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
			        "The Server is not accessible or it may be down because of Network Issue",
			        "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		session.close();
		return result;
	}

	@Override
	public boolean updateEntity(Abbreviation entity) {
		boolean result = false;
		Session session = SESSION_FACTORY.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(entity);
			transaction.commit();
			result = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
	@Override
	public boolean deleteEntity(Abbreviation entity) {
		boolean result = false;
		Session session = SESSION_FACTORY.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(entity);
			transaction.commit();
			result = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
	@Override
	public Abbreviation getRandomEntity() {
		Session session = SESSION_FACTORY.openSession();
		Query query = session.createQuery("From Abbreviation Where status = 1 Order By Rand()");
		query.setMaxResults(20);
		String aql = toSql(query.getQueryString());
		System.out.println(aql);
		@SuppressWarnings("unchecked")
		List<Abbreviation> list = query.list();
		Abbreviation abbreviation  = randomEntity(list);
		session.close();
		return abbreviation;
	}
	
	private Abbreviation randomEntity(List<Abbreviation> list) {
		Abbreviation entity = null;
		List<Abbreviation> listA = new ArrayList<>();
		List<Abbreviation> listB = new ArrayList<>();
		List<Abbreviation> listC = new ArrayList<>();
		List<Abbreviation> listD = new ArrayList<>();
		for (Abbreviation abbreviation : list) {
			if (abbreviation.getHistoryCount().rightAnswerRate() >= 0.6) {
				listD.add(abbreviation);
			}
			else if (abbreviation.getHistoryCount().getRightAnswerCount() >= 0.4) {
				listC.add(abbreviation);
			}
			else if (abbreviation.getHistoryCount().getRightAnswerCount() >= 0.2) {
				listB.add(abbreviation);
			}
			else listA.add(abbreviation);
		}
		int randomCase = new Random().nextInt(13);
		switch (randomCase) {
		case 0:

		case 1:

		case 2:

		case 3:
			
		case 4:
			
		case 5:
			if (listA.size() != 0) {
				entity = listA.get(new Random().nextInt(listA.size()));
				break;
			}
		case 6:
			
		case 7:

		case 8:

		case 9:
			if (listB.size() != 0) {
				entity = listB.get(new Random().nextInt(listB.size()));
				break;
			}
		case 10:
			
		case 11:
			if (listC.size() != 0) {
				entity = listC.get(new Random().nextInt(listC.size()));
				break;
			}
		case 12:
			if (listD.size() !=0) {
				entity = listD.get(new Random().nextInt(listD.size()));
				break;
			}
			else if (listA.size() != 0) {
				entity = listA.get(new Random().nextInt(listA.size()));
				break;
			}
			else if (listB.size() != 0) {
				entity = listB.get(new Random().nextInt(listB.size()));
				break;
			}
			else entity = listC.get(new Random().nextInt(listC.size()));
			break;
		default:
			break;
		}
		System.out.println(entity);
		return entity;
	}
	
	public String toSql(String hqlQueryText){
	    if (hqlQueryText!=null && hqlQueryText.trim().length()>0){
	      final QueryTranslatorFactory translatorFactory = new ASTQueryTranslatorFactory();
	      final SessionFactoryImplementor factory = 
	        (SessionFactoryImplementor) HibernateUtil.getSessionFactory();
	      final QueryTranslator translator = translatorFactory.
	        createQueryTranslator(
	          hqlQueryText, 
	          hqlQueryText, 
	          Collections.EMPTY_MAP, factory, null
	        );
	      translator.compile(Collections.EMPTY_MAP, false);
	      return translator.getSQLString(); 
	    }
	    return null;
	  }

}
