package ma.projet.util;

import ma.projet.beans.Homme;
import ma.projet.beans.Femme;
import ma.projet.beans.Mariage;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Charger la configuration par code au lieu de hibernate.cfg.xml
            Properties settings = new Properties();

            settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3307/etatcivil?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC");
            settings.put("hibernate.connection.username", "root");
            settings.put("hibernate.connection.password", "root");

            settings.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
            settings.put("hibernate.hbm2ddl.auto", "update"); // create, create-drop, update, validate
            settings.put("hibernate.show_sql", "true");
            settings.put("hibernate.format_sql", "true");

            Configuration configuration = new Configuration();
            configuration.setProperties(settings);

            // Déclarer les entités
            configuration.addAnnotatedClass(Homme.class);
            configuration.addAnnotatedClass(Femme.class);
            configuration.addAnnotatedClass(Mariage.class);

            StandardServiceRegistryBuilder serviceRegistryBuilder =
                    new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

            sessionFactory = configuration.buildSessionFactory(serviceRegistryBuilder.build());

        } catch (Throwable ex) {
            System.err.println("Erreur lors de l'initialisation Hibernate : " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
