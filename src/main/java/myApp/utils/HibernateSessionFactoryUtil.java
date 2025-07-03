package myApp.utils;

import myApp.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
        public static final SessionFactory sessionFactory = getSessionFactory();

        public static SessionFactory getSessionFactory() {
                try {
                        Configuration configuration = new Configuration().configure();
                        configuration.addAnnotatedClass(User.class);
                        StandardServiceRegistryBuilder builder =
                                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                        return configuration.buildSessionFactory(builder.build());
                } catch (Exception ex) {
                        ex.printStackTrace();
                }
                return null;
        }
}
