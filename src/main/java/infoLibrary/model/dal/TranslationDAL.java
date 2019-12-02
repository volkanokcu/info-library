package infoLibrary.model.dal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import infoLibrary.model.entity.Translation;

/**
 * @author Volkan Okçu
 *	
 * @date 2017-Mar-21 23:05:48 
 *
 */
public class TranslationDAL extends AbstractDAL<Translation>{

	private static final long serialVersionUID = -756372752524873209L;
	
	@Override
	public boolean insertEntity(Translation entity) {
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
		}
		session.close();
		return result;
	}
	
	@Override
	public boolean updateEntity(Translation entity) {
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
	
	public boolean deleteEntity(Translation entity) {
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
	public List<Translation> getRandomEntities() {
		Session session = SESSION_FACTORY.openSession();
		Query query = session.createQuery("From Translation Where status = 1 Order By Rand()");
		query.setMaxResults(20);
		@SuppressWarnings("unchecked")
		List<Translation> list = query.list();
		Translation translation = randomEntity(list);
		list.remove(translation);
		List<Translation> returnList = new ArrayList<>();
		returnList.add(0, translation);
		for (int i = 0; i<3; i++) {
			int randomIndex = new Random().nextInt(list.size());
			returnList.add(list.get(randomIndex));
			list.remove(list.get(randomIndex));
		}
		session.close();
		return returnList;
	}
	
	private Translation randomEntity(List<Translation> list) {
		Translation entity = null;
		List<Translation> listA = new ArrayList<>();
		List<Translation> listB = new ArrayList<>();
		List<Translation> listC = new ArrayList<>();
		List<Translation> listD = new ArrayList<>();
		int i = 0;
		for (Translation translation : list) {
			i++;
			if (translation.getHistoryCount().rightAnswerRate() >= 0.6) {
				listD.add(translation);
				System.out.println("list d-i= " + i);
			}
			else if (translation.getHistoryCount().getRightAnswerCount() >= 0.4) {
				listC.add(translation);
				System.out.println("list c-i= " + i);
			}
			else if (translation.getHistoryCount().getRightAnswerCount() >= 0.2) {
				listB.add(translation);
				System.out.println("list b-i= " + i);
			}
			else listA.add(translation);
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
		return entity;
	}

}
