package ma.projet.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.InputStream;
import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Charger application.properties
            Properties props = new Properties();
            try (InputStream input = HibernateUtil.class.getClassLoader()
                    .getResourceAsStream("application.properties")) {
                if (input == null) {
                    throw new RuntimeException("Impossible de trouver application.properties");
                }
                props.load(input);
            }

            // Config Hibernate
            Configuration configuration = new Configuration();
            configuration.setProperties(props);

            // Enregistrer les entités
            configuration.addAnnotatedClass(ma.projet.classes.Produit.class);
            configuration.addAnnotatedClass(ma.projet.classes.Categorie.class);
            configuration.addAnnotatedClass(ma.projet.classes.Commande.class);
            configuration.addAnnotatedClass(ma.projet.classes.LigneCommandeProduit.class);

            StandardServiceRegistryBuilder builder =
                    new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

            sessionFactory = configuration.buildSessionFactory(builder.build());

        } catch (Throwable ex) {
            System.err.println("Erreur création SessionFactory : " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
