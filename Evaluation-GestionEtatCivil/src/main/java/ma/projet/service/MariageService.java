package ma.projet.service;

import ma.projet.beans.Mariage;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MariageService implements IDao<Mariage> {
    @Override
    public void create(Mariage o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.persist(o);
        tx.commit();
        s.close();
    }

    @Override
    public Mariage getById(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Mariage m = s.get(Mariage.class, id);
        s.close();
        return m;
    }

    @Override
    public List<Mariage> getAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Mariage> list = s.createQuery("from Mariage", Mariage.class).list();
        s.close();
        return list;
    }

    @Override
    public void update(Mariage o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.merge(o);
        tx.commit();
        s.close();
    }

    @Override
    public void delete(Mariage o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.remove(o);
        tx.commit();
        s.close();
    }
}
