package infoLibrary.model.entity;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Bora SAYINER
 * @since 3:27:16 PM Jan 28, 2017
 * @version 1.0.0
 */
public final class HibernateUtil {

	private static final SessionFactory SESSION_FACTORY;

	static {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SESSION_FACTORY = configuration.buildSessionFactory(registry);
	}

	private HibernateUtil() {
	}

	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}
}
