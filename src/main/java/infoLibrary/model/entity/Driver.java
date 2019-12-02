package infoLibrary.model.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * @author Volkan
 *
 */
public class Driver {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
//		Abbreviation abbreviation = (Abbreviation) session.get(Abbreviation.class, (long) 1);
//		System.out.println(abbreviation.getAbbDetails());
		Transaction transaction = session.beginTransaction();
		
		try {
			{
			Abbreviation abbreviation = new Abbreviation();
			abbreviation.setAbbName("OOP");
			abbreviation.setAbbExpansionName("Object Oriented Programing");
			abbreviation.setAbbDetail("Yaygın inancın tersine, Lorem Ipsum rastgele sözcüklerden oluşmaz."
					+ " Kökleri M.Ö. 45 tarihinden bu yana klasik Latin edebiyatına kadar uzanan 2000 yıllık "
					+ "bir geçmişi vardır. Virginia'daki Hampden-Sydney College'dan Latince profesörü Richard "
					+ "McClintock, bir Lorem Ipsum pasajında geçen ve anlaşılması en güç sözcüklerden biri olan "
					+ "'consectetur' sözcüğünün klasik edebiyattaki örneklerini incelediğinde kesin bir kaynağa ulaşmıştır. ");
			HistoryCount historyCount = new HistoryCount();
			historyCount.setCount(0);
			historyCount.setRightAnswerCount(0);
			abbreviation.setHistoryCount(historyCount);
			session.save(abbreviation);
			}
			{
			Translation translation = new Translation();
			translation.setEnglishWord("Hello");
			translation.setTurkishWord("Merhaba");
			HistoryCount historyCount = new HistoryCount();
			historyCount.setCount(0);
			historyCount.setRightAnswerCount(0);
			translation.setHistoryCount(historyCount);
			session.save(translation);
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		session.close();
		sessionFactory.close();
	
	}

}
