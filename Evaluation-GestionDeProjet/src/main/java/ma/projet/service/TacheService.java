package ma.projet.service;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;

public class TacheService implements IDao<Tache> {

    @Override
    public boolean create(Tache t) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.save(t);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Tache t) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.update(t);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Tache t) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.delete(t);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Tache getById(int id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(Tache.class, id);
        }
    }

    @Override
    public List<Tache> getAll() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Tache> query = s.createQuery("from Tache", Tache.class);
            return query.list();
        }
    }

    // ✅ Méthode personnalisée pour ton TestApplication
    public List<Tache> getTachesPrixSup1000() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Tache> query = s.createQuery(
                    "from Tache t where t.prix > 1000",
                    Tache.class
            );
            return query.list();
        }
    }
}
