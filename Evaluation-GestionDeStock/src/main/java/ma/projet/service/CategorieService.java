package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.classes.Categorie;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import java.util.List;

public class CategorieService implements IDao<Categorie> {
    @Override
    public void create(Categorie c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(c);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Categorie getById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Categorie c = session.get(Categorie.class, id);
        session.close();
        return c;
    }

    @Override
    public List<Categorie> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Categorie> categories = session.createQuery("from Categorie").list();
        session.close();
        return categories;
    }

    @Override
    public void update(Categorie c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(c);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Categorie c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(c);
        session.getTransaction().commit();
        session.close();
    }
}
