package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.classes.Commande;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import java.util.List;

public class CommandeService implements IDao<Commande> {
    @Override
    public void create(Commande c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(c);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Commande getById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Commande c = session.get(Commande.class, id);
        session.close();
        return c;
    }

    @Override
    public List<Commande> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Commande> commandes = session.createQuery("from Commande").list();
        session.close();
        return commandes;
    }

    @Override
    public void update(Commande c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(c);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Commande c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(c);
        session.getTransaction().commit();
        session.close();
    }
}
